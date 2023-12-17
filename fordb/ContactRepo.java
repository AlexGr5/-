package com.example.web.fordb;

import com.example.web.models.Contact;
import com.example.web.models.Subdivision;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepo extends CrudRepository<Contact, Integer> {
}
