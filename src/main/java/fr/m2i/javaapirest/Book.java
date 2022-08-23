package fr.m2i.javaapirest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.xml.bind.annotation.XmlRootElement;

@JsonPropertyOrder({"isbn", "name"})
@XmlRootElement(name = "book")
public class Book {
    
    @JsonProperty("book_name")
    private String name;

    @JsonProperty("book_isbn")
    private String isbn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
