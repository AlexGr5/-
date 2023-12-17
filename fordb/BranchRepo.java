package com.example.web.fordb;

import com.example.web.models.Branch;
import com.example.web.models.Excursion;
import org.springframework.data.repository.CrudRepository;

public interface BranchRepo extends CrudRepository<Branch, Integer> {
}
