package com.example.web.fordb;

import com.example.web.models.Human;
import com.example.web.models.Subdivision;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface HumanRepo extends CrudRepository<Human, Integer> {
    // Использование @Query с нативным запросом
    @Query(value = "CALL dismissal_human(?1);", nativeQuery = true)
    public int DismissalHum(Integer id);
}
