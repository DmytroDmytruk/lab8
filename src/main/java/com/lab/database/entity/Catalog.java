package com.lab.database.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "catalog")
public class Catalog {

    @Id
    @Column(name = "library_code")
    private String libraryCode;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

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