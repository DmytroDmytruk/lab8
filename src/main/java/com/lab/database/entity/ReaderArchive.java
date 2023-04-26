package com.lab.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reader_archive")
public class ReaderArchive {

    @Id
    @Column(name = "reader_card_number")
    private Long readerCardNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "activity_type")
    private String activityType;
}