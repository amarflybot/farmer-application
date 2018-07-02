package com.example.bhaapplication.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Farmer_Data")
@Data
public class FarmerData {

    @Id
    @GeneratedValue
    private Long id;

    private BigInteger phoneNumber;

    private String name;

    private String language;

    @OneToMany(mappedBy = "farmerData", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<FarmData> farmData;
}
