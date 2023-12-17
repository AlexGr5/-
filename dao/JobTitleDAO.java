package com.example.web.dao;

import com.example.web.fordb.JobTitleRepo;
import com.example.web.models.JobTitle;
import com.example.web.models.Subdivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JobTitleDAO {

    @Autowired
    private JobTitleRepo jobTitleRepo;


    @Transactional(readOnly = true)
    public List<JobTitle> index() {

        List<JobTitle> jobTitleList = (List<JobTitle>) jobTitleRepo.findAll();

        return jobTitleList;
    }

    @Transactional(readOnly = true)
    public JobTitle show(int id) {
        Optional<JobTitle> jobTitle = jobTitleRepo.findById(id);

        if (jobTitle.isPresent()) {
            return jobTitle.get();
        } else return new JobTitle();
    }

    @Transactional
    public void save(JobTitle jobTitle) {
        jobTitleRepo.save(jobTitle);
    }

    @Transactional
    public void update(int id, JobTitle updatedExcursion) throws ChangeSetPersister.NotFoundException {
        if (!jobTitleRepo.findById(id).isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        JobTitle jobTitle = new JobTitle();
        jobTitle.setPK_JobTitle(id);
        jobTitle.setNameOfJobTitle(updatedExcursion.getNameOfJobTitle());
        jobTitleRepo.save(jobTitle);
    }

    @Transactional
    public void delete(int id) {
        jobTitleRepo.deleteById(id);
    }
}
