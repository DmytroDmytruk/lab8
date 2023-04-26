package com.lab.database.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "book_lending_journal")
public class BookLendingJournal {

    @Id
    @Column(name = "inventory_number")
    private Long inventoryNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_card_number")
    private ReaderArchive readerArchive;

    @Column(name = "lending_date")
    private Date lendingDate;
}
