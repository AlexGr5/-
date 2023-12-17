package com.example.web.fordb;

import com.example.web.models.Office;
import com.example.web.models.Subdivision;
import org.springframework.data.repository.CrudRepository;

public interface OfficeRepo extends CrudRepository<Office, Integer> {
}
