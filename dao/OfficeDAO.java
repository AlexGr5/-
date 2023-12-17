package com.example.web.dao;

import com.example.web.fordb.OfficeRepo;
import com.example.web.models.Office;
import com.example.web.models.Subdivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class OfficeDAO {

    @Autowired
    private OfficeRepo officeRepo;


    @Transactional(readOnly = true)
    public List<Office> index() {

        List<Office> officeList = (List<Office>) officeRepo.findAll();

        return officeList;
    }

    @Transactional(readOnly = true)
    public Office show(int id) {
        Optional<Office> office = officeRepo.findById(id);

        if (office.isPresent()) {
            return office.get();
        } else return new Office();
    }

    @Transactional
    public void save(Office office) {
        officeRepo.save(office);
    }

    @Transactional
    public void update(int id, Office updatedExcursion) throws ChangeSetPersister.NotFoundException {
        if (!officeRepo.findById(id).isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Office office = new Office();
        office.setPK_Office(id);
        office.setNumberOffice(updatedExcursion.getNumberOffice());
        office.setSubdivisionOffice(updatedExcursion.getSubdivisionOffice());
        office.setHumanOffice(updatedExcursion.getHumanOffice());
        officeRepo.save(office);
    }

    @Transactional
    public void delete(int id) {
        officeRepo.deleteById(id);
    }
}
