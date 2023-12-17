package com.example.web.dao;

import com.example.web.fordb.OrganizationRepo;
import com.example.web.models.Excursion;
import com.example.web.models.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class OrganizationDAO {

    @Autowired
    private OrganizationRepo organizationRepo;


    @Transactional(readOnly = true)
    public List<Organization> index() {

        List<Organization> organizations = (List<Organization>) organizationRepo.findAll();

        return organizations;
    }

    @Transactional(readOnly = true)
    public Organization show(int id) {
        Optional<Organization> organization = organizationRepo.findById(id);

        if (organization.isPresent()) {
            return organization.get();
        } else return new Organization();
    }

    @Transactional
    public void save(Organization organization) {
        organizationRepo.save(organization);
    }

    @Transactional
    public void update(int id, Organization updatedExcursion) throws ChangeSetPersister.NotFoundException {
        if (!organizationRepo.findById(id).isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Organization organization = new Organization();
        organization.setPK_Organization(id);
        organization.setNameOrganization(updatedExcursion.getNameOrganization());
        organization.setAddress(updatedExcursion.getAddress());
        organization.setEmail(updatedExcursion.getEmail());
        organizationRepo.save(organization);
    }

    @Transactional
    public void delete(int id) {
        organizationRepo.deleteById(id);
    }
}
