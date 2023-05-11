package com.lab.database.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "catalog")
public class Catalog {

	public Catalog() {
		
	}
	
    public Catalog(String author, String title, String publisher, Integer year, String type, Integer numberOfPages,
			String theme, Integer price) {
		this.author = author;
		this.title = title;
		this.publisher = publisher;
		this.year = year;
		this.type = type;
		this.numberOfPages = numberOfPages;
		this.theme = theme;
		this.price = price;
	}


	public int getLibraryCode() {
		return libraryCode;
	}

	public void setLibraryCode(int libraryCode) {
		this.libraryCode = libraryCode;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "library_code")
    private int libraryCode;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "publisher")
    private String publisher;

    @Column(name = "year")
    private Integer year;

    @Column(name = "type")
    private String type;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @Column(name = "theme")
    private String theme;

    @Column(name = "price")
    private Integer price;
}