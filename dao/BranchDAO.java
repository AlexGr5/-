package com.example.web.dao;

import com.example.web.fordb.BranchRepo;
import com.example.web.models.Branch;
import com.example.web.models.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class BranchDAO {

    @Autowired
    private BranchRepo branchRepo;


    @Transactional(readOnly = true)
    public List<Branch> index() {

        List<Branch> branchList = (List<Branch>) branchRepo.findAll();

        return branchList;
    }

    @Transactional(readOnly = true)
    public Branch show(int id) {
        Optional<Branch> branch = branchRepo.findById(id);

        if (branch.isPresent()) {
            return branch.get();
        } else return new Branch();
    }

    @Transactional
    public void save(Branch branch) {
        branchRepo.save(branch);
    }

    @Transactional
    public void update(int id, Branch branch) throws ChangeSetPersister.NotFoundException {
        if (!branchRepo.findById(id).isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Branch branch1 = new Branch();
        branch1.setPK_Branch(id);
        branch1.setAddress(branch.getAddress());
        branch1.setOrganizationBran(branch.getOrganizationBran());
        branchRepo.save(branch1);
    }

    @Transactional
    public void delete(int id) {
        branchRepo.deleteById(id);
    }
}