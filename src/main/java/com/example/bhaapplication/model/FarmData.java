package com.example.bhaapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Farm_data")
@Data
public class FarmData {

    @Id
    @GeneratedValue
    private Long id;

    private BigInteger area;

    private String village;

    private String cropGrown;

    private Date sowingDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "farmerData_id")
    @JsonIgnore
    private FarmerData farmerData;

    @OneToMany(mappedBy = "farmData", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<ScheduleData> scheduleData;
}
