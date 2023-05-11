package com.lab.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_fund")
public class BookFund {

	public BookFund() {
		
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "inventory_number")
    private Long inventoryNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_code")
    private Catalog catalog;

    @Column(name = "status")
    private String status;

	public BookFund(Catalog catalog, String status) {
		super();

		this.catalog = catalog;
		this.status = status;
	}

	public Long getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(Long inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}