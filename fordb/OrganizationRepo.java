package com.example.web.fordb;

import com.example.web.models.Excursion;
import com.example.web.models.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepo extends CrudRepository<Organization, Integer> {
    // Использование @Query с нативным запросом
    //@Query(value = " { ? = call my_function (:empName) }", nativeQuery = true)
    //public Double countRate(Integer id);

    // Использование @Query с нативным запросом
    @Query(value = "SELECT CountRateHum3(?1)", nativeQuery = true)
    public Double countRate(Integer id);
}
