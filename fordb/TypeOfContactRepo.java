package com.example.web.fordb;

import com.example.web.models.Subdivision;
import com.example.web.models.TypeOfContact;
import org.springframework.data.repository.CrudRepository;

public interface TypeOfContactRepo extends CrudRepository<TypeOfContact, Integer> {
}
