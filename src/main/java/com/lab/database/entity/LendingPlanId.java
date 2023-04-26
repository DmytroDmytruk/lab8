package com.lab.database.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LendingPlanId implements Serializable {
    @Column(name = "inventory_number")
    private Long inventoryNumber;
}
