package com.lab.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_fund")
public class BookFund {

    @Id
    @Column(name = "inventory_number")
    private Long inventoryNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_code")
    private Catalog catalog;

    @Column(name = "status")
    private String status;
}