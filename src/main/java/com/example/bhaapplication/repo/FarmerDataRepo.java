package com.example.bhaapplication.repo;

import com.example.bhaapplication.model.FarmerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FarmerDataRepo extends JpaRepository<FarmerData, Long>{
}
