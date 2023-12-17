package com.example.web.dao;

import com.example.web.fordb.RateRepo;
import com.example.web.models.Rate;
import com.example.web.models.Subdivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class RateDAO {

    @Autowired
    private RateRepo rateRepo;


    @Transactional(readOnly = true)
    public List<Rate> index() {

        List<Rate> rateList = (List<Rate>) rateRepo.findAll();

        return rateList;
    }

    @Transactional(readOnly = true)
    public Rate show(int id) {
        Optional<Rate> rate = rateRepo.findById(id);

        if (rate.isPresent()) {
            return rate.get();
        } else return new Rate();
    }

    @Transactional
    public void save(Rate rate) {
        rateRepo.save(rate);
    }

    @Transactional
    public void update(int id, Rate updatedExcursion) throws ChangeSetPersister.NotFoundException {
        if (!rateRepo.findById(id).isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Rate rate = new Rate();
        rate.setPK_Rate(id);
        rate.setRateValue(updatedExcursion.getRateValue());
        rate.setDateOfEmployment(updatedExcursion.getDateOfEmployment());
        rate.setDateOfDismissal(updatedExcursion.getDateOfDismissal());
        rate.setSubdivisionRate(updatedExcursion.getSubdivisionRate());
        rate.setHumanRate(updatedExcursion.getHumanRate());
        rate.setJobTitleRate(updatedExcursion.getJobTitleRate());
        rateRepo.save(rate);
    }

    @Transactional
    public void delete(int id) {
        rateRepo.deleteById(id);
    }
}
