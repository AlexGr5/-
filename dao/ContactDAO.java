package com.example.web.dao;

import com.example.web.fordb.ContactRepo;
import com.example.web.models.Contact;
import com.example.web.models.Subdivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class ContactDAO {

    @Autowired
    private ContactRepo contactRepo;


    @Transactional(readOnly = true)
    public List<Contact> index() {

        List<Contact> contactList = (List<Contact>) contactRepo.findAll();

        return contactList;
    }

    @Transactional(readOnly = true)
    public Contact show(int id) {
        Optional<Contact> contact = contactRepo.findById(id);

        if (contact.isPresent()) {
            return contact.get();
        } else return new Contact();
    }

    @Transactional
    public void save(Contact contact) {
        contactRepo.save(contact);
    }

    @Transactional
    public void update(int id, Contact updatedExcursion) throws ChangeSetPersister.NotFoundException {
        if (!contactRepo.findById(id).isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Contact contact = new Contact();
        contact.setPK_Contact(id);
        contact.setValueContact(updatedExcursion.getValueContact());
        contact.setDateOfConnect(updatedExcursion.getDateOfConnect());
        contact.setHumanContact(updatedExcursion.getHumanContact());
        contact.setTypeOfContact(updatedExcursion.getTypeOfContact());
        contact.setStateOfContact(updatedExcursion.getStateOfContact());
        contactRepo.save(contact);
    }

    @Transactional
    public void delete(int id) {
        contactRepo.deleteById(id);
    }
}
