package com.example.bhaapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Schedule_Data")
@Data
public class ScheduleData {

    @Id
    @GeneratedValue
    private Long id;

    private Integer daysAfterSowing;

    private String fertiliser;

    private Integer quantity;

    private String quantityUnit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "farmData_id")
    @JsonIgnore
    private FarmData farmData;
}
