package com.example.bhaapplication.api;

import com.example.bhaapplication.model.FarmData;
import com.example.bhaapplication.model.ScheduleData;
import com.example.bhaapplication.repo.FarmDataRepo;
import com.example.bhaapplication.repo.ScheduleDataRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/due")
public class ScheduleDueResource {

    private final ScheduleDataRepo scheduleDataRepo;

    private final FarmDataRepo farmDataRepo;

    public ScheduleDueResource(ScheduleDataRepo scheduleDataRepo, FarmDataRepo farmDataRepo) {
        this.scheduleDataRepo = scheduleDataRepo;
        this.farmDataRepo = farmDataRepo;
    }


    @GetMapping("/today")
    ResponseEntity<List<ScheduleData>> findScheduleDueForToday() {
        List<ScheduleData> scheduleDataList = new ArrayList<>();
        this.farmDataRepo.findAll()
                .forEach((FarmData farmData) -> {
                    Date sowingDate = farmData.getSowingDate();
                    ZoneId defaultZoneId = ZoneId.systemDefault();
                    Instant instant = sowingDate.toInstant();
                    LocalDate sowingLocalDate = instant.atZone(defaultZoneId).toLocalDate();
                    LocalDate now = LocalDate.now();
                    Period between = Period.between(sowingLocalDate, now);
                    int days = between.getDays();
                    farmData.getScheduleData().forEach(scheduleData -> {
                                Integer daysAfterSowing = scheduleData.getDaysAfterSowing();
                                if (days >= daysAfterSowing) {
                                    scheduleDataList.add(scheduleData);
                                }
                            }
                    );
                });

        return ResponseEntity.ok(scheduleDataList);
    }

    @GetMapping("/tomorrow")
    ResponseEntity<List<ScheduleData>> findScheduleDueForTomorrow() {
        List<ScheduleData> scheduleDataList = new ArrayList<>();
        this.farmDataRepo.findAll()
                .forEach(farmData -> {
                    Date sowingDate = farmData.getSowingDate();
                    ZoneId defaultZoneId = ZoneId.systemDefault();
                    Instant instant = sowingDate.toInstant();
                    LocalDate sowingLocalDate = instant.atZone(defaultZoneId).toLocalDate();
                    LocalDate now = LocalDate.now();
                    Period between = Period.between(sowingLocalDate, now);
                    int days = between.getDays();
                    farmData.getScheduleData().forEach(scheduleData -> {
                                Integer daysAfterSowing = scheduleData.getDaysAfterSowing();
                                if ((days+1) >= daysAfterSowing) {
                                    scheduleDataList.add(scheduleData);
                                }
                            }
                    );
                });

        return ResponseEntity.ok(scheduleDataList);
    }
}
