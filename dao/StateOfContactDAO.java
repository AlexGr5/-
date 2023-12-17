package com.example.web.dao;

import com.example.web.fordb.StateOfContactRepo;
import com.example.web.models.StateOfContact;
import com.example.web.models.Subdivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class StateOfContactDAO {

    @Autowired
    private StateOfContactRepo stateOfContactRepo;


    @Transactional(readOnly = true)
    public List<StateOfContact> index() {

        List<StateOfContact> stateOfContactList = (List<StateOfContact>) stateOfContactRepo.findAll();

        return stateOfContactList;
    }

    @Transactional(readOnly = true)
    public StateOfContact show(int id) {
        Optional<StateOfContact> stateOfContact = stateOfContactRepo.findById(id);

        if (stateOfContact.isPresent()) {
            return stateOfContact.get();
        } else return new StateOfContact();
    }

    @Transactional
    public void save(StateOfContact stateOfContact) {
        stateOfContactRepo.save(stateOfContact);
    }

    @Transactional
    public void update(int id, StateOfContact updatedExcursion) throws ChangeSetPersister.NotFoundException {
        if (!stateOfContactRepo.findById(id).isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        StateOfContact stateOfContact = new StateOfContact();
        stateOfContact.setPK_StateOfContact(id);
        stateOfContact.setValueContactState(updatedExcursion.getValueContactState());
        stateOfContactRepo.save(stateOfContact);
    }

    @Transactional
    public void delete(int id) {
        stateOfContactRepo.deleteById(id);
    }
}