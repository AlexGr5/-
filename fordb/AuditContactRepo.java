package com.example.web.fordb;

import com.example.web.models.AuditContact;
import org.springframework.data.repository.CrudRepository;

public interface AuditContactRepo extends CrudRepository<AuditContact, Integer> {
}
