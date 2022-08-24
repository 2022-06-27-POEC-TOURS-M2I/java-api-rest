package fr.m2i.javaapirest.tpannuaire;

import java.util.ArrayList;
import java.util.List;

public class AnnuaireDAO {

    private List<Personne> personnes;
    private Long nextId;

    public AnnuaireDAO() {
        this.personnes = new ArrayList();
        this.nextId = 1L;
    }

    public Personne create(Personne personne) {
        personne.setId(nextId);
        personnes.add(personne);

        nextId++;

        return personne;
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }
}
