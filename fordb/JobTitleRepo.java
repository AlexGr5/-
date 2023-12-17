package com.example.web.fordb;

import com.example.web.models.JobTitle;
import com.example.web.models.Subdivision;
import org.springframework.data.repository.CrudRepository;

public interface JobTitleRepo extends CrudRepository<JobTitle, Integer> {
}
