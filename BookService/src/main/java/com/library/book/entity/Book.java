package com.library.book.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="books")
public class Book {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Title is required") @Size(max=200, message="Title must not exceed 200 characters")
    @Column(nullable=false, length=200) private String title;
    @NotBlank(message="Author is required") @Size(max=150, message="Author must not exceed 150 characters")
    @Column(nullable=false, length=150) private String author;
    @NotBlank(message="ISBN is required")
    @Pattern(regexp="^(?:\\d{9}[\\dXx]|(?:\\d[- ]?){13})$", message="ISBN must be a valid 10 or 13 digit ISBN")
    @Column(nullable=false, unique=true, length=20) private String isbn;
    public Book() {}
    public Book(String title,String author,String isbn){this.title=title;this.author=author;this.isbn=isbn;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getTitle(){return title;} public void setTitle(String title){this.title=title;}
    public String getAuthor(){return author;} public void setAuthor(String author){this.author=author;}
    public String getIsbn(){return isbn;} public void setIsbn(String isbn){this.isbn=isbn;}
}
