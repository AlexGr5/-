package com.example.web.fordb;

import com.example.web.models.Rate;
import com.example.web.models.Subdivision;
import org.springframework.data.repository.CrudRepository;

public interface RateRepo extends CrudRepository<Rate, Integer> {
}
