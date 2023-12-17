package com.example.web.fordb;

import com.example.web.models.StateOfContact;
import com.example.web.models.Subdivision;
import org.springframework.data.repository.CrudRepository;

public interface StateOfContactRepo extends CrudRepository<StateOfContact, Integer> {
}
