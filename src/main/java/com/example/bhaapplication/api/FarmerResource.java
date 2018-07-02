package com.example.bhaapplication.api;

import com.example.bhaapplication.model.FarmData;
import com.example.bhaapplication.model.FarmerData;
import com.example.bhaapplication.repo.FarmDataRepo;
import com.example.bhaapplication.repo.FarmerDataRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/farmer")
public class FarmerResource {

    private final FarmerDataRepo farmerDataRepo;

    private final FarmDataRepo farmDataRepo;

    public FarmerResource(FarmerDataRepo farmerDataRepo, FarmDataRepo farmDataRepo) {
        this.farmerDataRepo = farmerDataRepo;
        this.farmDataRepo = farmDataRepo;
    }

    @GetMapping("/crops")
    ResponseEntity<Set<String>> findAllCrops() {
        Set<String> crops = this.farmDataRepo.findAll().stream().map(FarmData::getCropGrown).collect(Collectors.toSet());
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/byCrop/{crop}")
    ResponseEntity<List<FarmerData>> findAllFarmersByCrop(@PathVariable("crop") String crop) {

        /*List<FarmerData> dataList = this.farmerDataRepo.findAll().stream()
                .filter(farmerData ->
                        farmerData.getFarmData().stream()
                                .allMatch(farmData ->
                                        farmData.getCropGrown().equals(crop)
                                )
                ).collect(Collectors.toList());*/
        List<FarmerData> collect = this.farmDataRepo.findAllByCropGrown(crop)
                .stream()
                .map(farmData -> farmData.getFarmerData())
                .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }
}
