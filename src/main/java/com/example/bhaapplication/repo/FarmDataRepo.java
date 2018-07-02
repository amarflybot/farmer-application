package com.example.bhaapplication.repo;

import com.example.bhaapplication.model.FarmData;
import com.example.bhaapplication.model.FarmerData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface FarmDataRepo extends JpaRepository<FarmData, Long>{

    List<FarmData> findAllByCropGrown(@Param("cropGrown") String cropGrown);

}
