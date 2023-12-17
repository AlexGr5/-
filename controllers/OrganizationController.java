package com.example.web.controllers;

import com.example.web.dao.OrganizationDAO;
import com.example.web.models.Organization;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    private final OrganizationDAO organizationDAO;

    @Autowired
    public OrganizationController(OrganizationDAO organizationDAO) {
        this.organizationDAO = organizationDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("organization", organizationDAO.index());
        return "organization/indexorganization";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("organization", organizationDAO.show(id));
        return "organization/showorganization";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("organization") Organization organization) {
        return "organization/neworganization";
    }

    @PostMapping()
    public String create(@ModelAttribute("organization") @Valid Organization organization,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "organization/neworganization";

        if (organization.getNameOrganization().length() < 1)
            return "organization/neworganization";

        organizationDAO.save(organization);
        return "redirect:/organization";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("organization", organizationDAO.show(id));
        return "organization/editorganization";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("organization") @Valid Organization organization,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "organization/editorganization";

        if (organization.getNameOrganization().length() < 1)
            return "organization/editorganization";

        try {
            organizationDAO.update(id, organization);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/organization";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        organizationDAO.delete(id);
        return "redirect:/organization";
    }
}