package com.example.bhaapplication.repo;

import com.example.bhaapplication.model.ScheduleData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ScheduleDataRepo extends JpaRepository<ScheduleData, Long>{

    Page<ScheduleData> findAllByDaysAfterSowing(@Param("daysAfterSowing") Integer daysAfterSowing, Pageable pageable);
}
