package com.example.web.dao;

import com.example.web.fordb.SubdivisionRepo;
import com.example.web.models.Organization;
import com.example.web.models.Subdivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class SubdivisionDAO {

    @Autowired
    private SubdivisionRepo subdivisionRepo;


    @Transactional(readOnly = true)
    public List<Subdivision> index() {

        List<Subdivision> subdivisions = (List<Subdivision>) subdivisionRepo.findAll();

        return subdivisions;
    }

    @Transactional(readOnly = true)
    public Subdivision show(int id) {
        Optional<Subdivision> subdivision = subdivisionRepo.findById(id);

        if (subdivision.isPresent()) {
            return subdivision.get();
        } else return new Subdivision();
    }

    @Transactional
    public void save(Subdivision subdivision) {
        if (subdivision.getSubdivisionOne().getPK_Subdivision() != 0)
            subdivisionRepo.save(subdivision);
        else {
            subdivision.setSubdivisionOne(null);
            subdivisionRepo.save(subdivision);
        }
    }

    @Transactional
    public void update(int id, Subdivision updatedExcursion) throws ChangeSetPersister.NotFoundException {
        if (!subdivisionRepo.findById(id).isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Subdivision subdivision = new Subdivision();
        subdivision.setPK_Subdivision(id);
        subdivision.setNameSubdivision(updatedExcursion.getNameSubdivision());
        subdivision.setAbbreviation(updatedExcursion.getAbbreviation());
        subdivision.setKodSubdivision(updatedExcursion.getKodSubdivision());
        subdivision.setEmail(updatedExcursion.getEmail());
        subdivision.setOrganizationSub(updatedExcursion.getOrganizationSub());
        subdivision.setBranchSub(updatedExcursion.getBranchSub());
        if (updatedExcursion.getSubdivisionOne().getPK_Subdivision() != 0)
            subdivision.setSubdivisionOne(updatedExcursion.getSubdivisionOne());
        else
            subdivision.setSubdivisionOne(null);
        subdivisionRepo.save(subdivision);
    }

    @Transactional
    public void delete(int id) {
        subdivisionRepo.deleteById(id);
    }
}
