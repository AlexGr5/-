package com.example.web.dao;

import com.example.web.fordb.TypeOfContactRepo;
import com.example.web.models.Subdivision;
import com.example.web.models.TypeOfContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class TypeOfContactDAO {

    @Autowired
    private TypeOfContactRepo typeOfContactRepo;


    @Transactional(readOnly = true)
    public List<TypeOfContact> index() {

        List<TypeOfContact> typeOfContactList = (List<TypeOfContact>) typeOfContactRepo.findAll();

        return typeOfContactList;
    }

    @Transactional(readOnly = true)
    public TypeOfContact show(int id) {
        Optional<TypeOfContact> typeOfContact = typeOfContactRepo.findById(id);

        if (typeOfContact.isPresent()) {
            return typeOfContact.get();
        } else return new TypeOfContact();
    }

    @Transactional
    public void save(TypeOfContact typeOfContact) {
        typeOfContactRepo.save(typeOfContact);
    }

    @Transactional
    public void update(int id, TypeOfContact updatedExcursion) throws ChangeSetPersister.NotFoundException {
        if (!typeOfContactRepo.findById(id).isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        TypeOfContact typeOfContact = new TypeOfContact();
        typeOfContact.setPK_TypeOfContact(id);
        typeOfContact.setValueTupeContact(updatedExcursion.getValueTupeContact());
        typeOfContactRepo.save(typeOfContact);
    }

    @Transactional
    public void delete(int id) {
        typeOfContactRepo.deleteById(id);
    }
}