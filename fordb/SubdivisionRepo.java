package com.example.web.fordb;

import com.example.web.models.Organization;
import com.example.web.models.Subdivision;
import org.springframework.data.repository.CrudRepository;

public interface SubdivisionRepo extends CrudRepository<Subdivision, Integer> {
}
