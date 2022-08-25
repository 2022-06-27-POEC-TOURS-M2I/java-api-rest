package fr.m2i.javaapirest.tpuser.dao;

import fr.m2i.javaapirest.tpuser.exception.NotFoundException;
import fr.m2i.javaapirest.tpuser.model.User;
import fr.m2i.javaapirest.tpuser.util.SessionHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class UserDAO {

    private final EntityManager entityManager;

    public UserDAO() {
        this.entityManager = SessionHelper.getEntityManager();
    }

    public List<User> findAll() {
        Query query = entityManager.createQuery("select u from User u");
        return query.getResultList();
    }

    public User findById(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("findById|Param id cannot be null");
            // Si l'exception est levé la méthode s'arrête ici
        }

        User founded = entityManager.find(User.class, id);

        if (founded == null) {
            throw new NotFoundException("findById|User with id :" + id + " was not found");
            // Si l'exception est levé la méthode s'arrête ici
        }

        return founded;
    }

    public List<User> search(String query, Integer count) throws Exception {

        if (query == null) {
            throw new Exception("search|Param query is mandatory");
        }

        Query searchQuery = entityManager.createQuery("select u from User u where u.lastname like :query or u.email like :query");
        searchQuery.setParameter("query", "%" + query + "%");

        if (count != null && count != 0) {
            searchQuery.setMaxResults(count);
        }

        List<User> users = searchQuery.getResultList();

        if (users == null || users.isEmpty()) {
            throw new NotFoundException("search|No user founded for query :" + query);
        }

        return users;
    }

    public void create(User userToCreate) throws Exception {

        if (userToCreate == null) {
            throw new Exception("create|Param user cannot be null");
        }

        if (userToCreate.hasAFieldEmpty()) {
            throw new Exception("create|All fields in user must be filled");
        }

        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.persist(userToCreate);

            tx.commit();
        } catch (Exception e) {
            System.out.println("create|A error occured during persist");
            System.out.println("Exception message : " + e.getMessage());

            if (tx != null) {
                tx.rollback();
            }

            throw e;
        }
    }

    public void update(Integer id, User userData) throws Exception {

        // La gestion d'erreur est faite dans le find by id
        // La bonne exception sera levée si l'id passé param n'est pas valide ou si l'utilisateur n'est pas trouvé
        User userToUpdate = findById(id);

        if (userData == null) {
            throw new Exception("update|Param user cannot be null");
        }

        userToUpdate.copy(userData);

        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.merge(userToUpdate);

            tx.commit();
        } catch (Exception e) {
            System.out.println("update|A error occured during merge");
            System.out.println("Exception message : " + e.getMessage());

            if (tx != null) {
                tx.rollback();
            }

            throw e;
        }
    }

    public void delete(Integer id) throws Exception {

        // La gestion d'erreur est faite dans le find by id
        // La bonne exception sera levée si l'id passé param n'est pas valide ou si l'utilisateur n'est pas trouvé
        User toDelete = findById(id);

        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.remove(toDelete);

            tx.commit();
        } catch (Exception e) {
            System.out.println("delete|A error occured during delete");
            System.out.println("Exception message : " + e.getMessage());

            if (tx != null) {
                tx.rollback();
            }

            throw e;
        }
    }

}
