package com.example.web.dao;

import com.example.web.fordb.AuditContactRepo;
import com.example.web.models.AuditContact;
import com.example.web.models.Subdivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class AuditContactDAO {

    @Autowired
    private AuditContactRepo auditContactRepo;


    @Transactional(readOnly = true)
    public List<AuditContact> index() {

        List<AuditContact> auditContactList = (List<AuditContact>) auditContactRepo.findAll();

        return auditContactList;
    }

    @Transactional(readOnly = true)
    public AuditContact show(int id) {
        Optional<AuditContact> auditContact = auditContactRepo.findById(id);

        if (auditContact.isPresent()) {
            return auditContact.get();
        } else return new AuditContact();
    }

    @Transactional
    public void save(AuditContact auditContact) {
        auditContactRepo.save(auditContact);
    }

    @Transactional
    public void update(int id, AuditContact updatedExcursion) throws ChangeSetPersister.NotFoundException {
        if (!auditContactRepo.findById(id).isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        AuditContact auditContact = new AuditContact();
        auditContact.setPK_AuditContact(id);
        auditContact.setUser(updatedExcursion.getUser());
        auditContact.setDateEdit(updatedExcursion.getDateEdit());
        auditContact.setTypeReqest(updatedExcursion.getTypeReqest());
        auditContact.setDateOfConnect(updatedExcursion.getDateOfConnect());
        auditContact.setValueContact(updatedExcursion.getValueContact());
        auditContact.setContactAudit(updatedExcursion.getContactAudit());
        auditContactRepo.save(auditContact);
    }

    @Transactional
    public void delete(int id) {
        auditContactRepo.deleteById(id);
    }
}