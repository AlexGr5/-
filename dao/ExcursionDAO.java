package com.example.web.dao;

import com.example.web.fordb.ExcursionRepo;
import com.example.web.models.Excursion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/*
@Component
public class ExcursionDAO {

    @Autowired
    private ExcursionRepo excursionRepo;


    @Transactional(readOnly = true)
    public List<Excursion> index() {

        List<Excursion> excursions = (List<Excursion>) excursionRepo.findAll();

        return excursions;
    }

    @Transactional(readOnly = true)
    public Excursion show(int id) {
        Optional<Excursion> excursion = excursionRepo.findById(id);

        if (excursion.isPresent()) {
            return excursion.get();
        } else return new Excursion();
    }

    @Transactional
    public void save(Excursion excursion) {
        excursionRepo.save(excursion);
    }

    @Transactional
    public void update(int id, Excursion updatedExcursion) throws ChangeSetPersister.NotFoundException {
        if (!excursionRepo.findById(id).isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Excursion excursion = new Excursion();
        //excursion.setPK_Excursion(id);
        //excursion.setNameExcursion(updatedExcursion.getNameExcursion());
        //excursion.setDescription(updatedExcursion.getDescription());
        //excursion.setPlace(updatedExcursion.getPlace());
        excursionRepo.save(excursion);
    }

    @Transactional
    public void delete(int id) {
        excursionRepo.deleteById(id);
    }
}

 */

