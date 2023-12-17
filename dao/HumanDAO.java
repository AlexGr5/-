package com.example.web.dao;

import com.example.web.fordb.HumanRepo;
import com.example.web.models.Human;
import com.example.web.models.Subdivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class HumanDAO {

    @Autowired
    private HumanRepo humanRepo;


    @Transactional(readOnly = true)
    public List<Human> index() {

        List<Human> humanList = (List<Human>) humanRepo.findAll();

        return humanList;
    }

    @Transactional(readOnly = true)
    public Human show(int id) {
        Optional<Human> human = humanRepo.findById(id);

        if (human.isPresent()) {
            return human.get();
        } else return new Human();
    }

    @Transactional
    public void save(Human human) {
        humanRepo.save(human);
    }

    @Transactional
    public void update(int id, Human updatedExcursion) throws ChangeSetPersister.NotFoundException {
        if (!humanRepo.findById(id).isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Human human = new Human();
        human.setPK_Human(id);
        human.setNameHuman(updatedExcursion.getNameHuman());
        human.setSurname(updatedExcursion.getSurname());
        human.setPatronymic(updatedExcursion.getPatronymic());
        human.setEmail(updatedExcursion.getEmail());
        human.setDateOfBirth(updatedExcursion.getDateOfBirth());
        humanRepo.save(human);
    }

    @Transactional
    public void delete(int id) {
        humanRepo.deleteById(id);
    }

    @Transactional
    public void dismissal(int id) {
        humanRepo.DismissalHum(id);
    }
}
