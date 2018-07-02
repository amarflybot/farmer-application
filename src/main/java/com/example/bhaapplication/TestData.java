package com.example.bhaapplication;

import com.example.bhaapplication.model.FarmData;
import com.example.bhaapplication.model.FarmerData;
import com.example.bhaapplication.model.ScheduleData;
import com.example.bhaapplication.repo.FarmDataRepo;
import com.example.bhaapplication.repo.FarmerDataRepo;
import com.example.bhaapplication.repo.ScheduleDataRepo;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.Clock;
import java.time.Instant;
import java.util.*;

@Component
public class TestData implements ApplicationRunner {

    private final FarmDataRepo farmDataRepo;

    private final FarmerDataRepo farmerDataRepo;

    private final ScheduleDataRepo scheduleDataRepo;

    private final DataFactory dataFactory;

    public TestData(FarmDataRepo farmDataRepo, FarmerDataRepo farmerDataRepo, ScheduleDataRepo scheduleDataRepo) {
        this.farmDataRepo = farmDataRepo;
        this.farmerDataRepo = farmerDataRepo;
        this.scheduleDataRepo = scheduleDataRepo;
        this.dataFactory = new DataFactory();
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        List<String> crops = Arrays.asList("Rice","Wheat","Daal","Moong","Chana");
        List<String> languages = Arrays.asList("Hindi","English","Maithali","Urdu","Punjabi");
        List<String> quatity = Arrays.asList("kg","ton");

        for (int i = 0; i < 100; i++) {
            ScheduleData scheduleData = new ScheduleData();
            scheduleData.setDaysAfterSowing(dataFactory.getNumberBetween(0,5));
            scheduleData.setFertiliser("Fertiliser"+dataFactory.getSuffix(10));
            scheduleData.setQuantity(dataFactory.getNumberBetween(0,10));
            scheduleData.setQuantityUnit(dataFactory.getItem(quatity));
            ScheduleData scheduleData1 = this.scheduleDataRepo.save(scheduleData);

            ScheduleData scheduleData2 = new ScheduleData();
            scheduleData2.setDaysAfterSowing(dataFactory.getNumberBetween(0,1));
            scheduleData2.setFertiliser("SomeNewFertilizer"+ dataFactory.getRandomText(2));
            scheduleData2.setQuantity(dataFactory.getNumberBetween(1,10));
            scheduleData2.setQuantityUnit(dataFactory.getItem(quatity));
            ScheduleData scheduleData3 = this.scheduleDataRepo.save(scheduleData2);

            FarmData farmData = new FarmData();
            farmData.setArea(BigInteger.valueOf(123123));
            farmData.setCropGrown(dataFactory.getItem(crops));
            Calendar calendar = new Calendar.Builder().setDate(2018, 6, 1).build();
            Date date = calendar.getTime();
            farmData.setSowingDate(date);
            farmData.setVillage(dataFactory.getCity());
            List<ScheduleData> scheduleDataList = new ArrayList<>();
            scheduleDataList.add(scheduleData1);
            scheduleDataList.add(scheduleData3);
            farmData.setScheduleData(scheduleDataList);
            FarmData farmData1 = this.farmDataRepo.save(farmData);

            scheduleData1.setFarmData(farmData1);
            scheduleData3.setFarmData(farmData1);
            this.scheduleDataRepo.save(scheduleData1);
            this.scheduleDataRepo.save(scheduleData3);

            FarmerData farmerData = new FarmerData();
            farmerData.setName(dataFactory.getName());
            farmerData.setLanguage(dataFactory.getItem(languages));
            farmerData.setFarmData(Arrays.asList(farmData1));
            farmerData.setPhoneNumber(new BigInteger(String.valueOf(dataFactory.getNumberBetween(0, 1000))));
            FarmerData farmerData1 = this.farmerDataRepo.save(farmerData);

            farmData1.setFarmerData(farmerData1);
            this.farmDataRepo.save(farmData1);
        }

    }
}
