package com.lab.database.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;


@Entity
@Table(name = "lending_plans")
public class LendingPlans {

    @EmbeddedId
    private LendingPlanId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("inventoryNumber")
    @JoinColumn(name = "inventory_number")
    private BookLendingJournal bookLendingJournal;

    @Column(name = "planned_return_date")
    private Date plannedReturnDate;

    // getters and setters
}