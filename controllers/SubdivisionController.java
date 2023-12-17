package com.example.web.controllers;

import com.example.web.dao.OrganizationDAO;
import com.example.web.dao.SubdivisionDAO;
import com.example.web.models.Organization;
import com.example.web.models.Subdivision;
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
@RequestMapping("/subdivision")
public class SubdivisionController {

    private final SubdivisionDAO organizationDAO;

    @Autowired
    public SubdivisionController(SubdivisionDAO subdivisionDAO) {
        this.organizationDAO = subdivisionDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("subdivision", organizationDAO.index());
        return "subdivision/indexsubdivision";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("subdivision", organizationDAO.show(id));
        return "subdivision/showsubdivision";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("subdivision") Subdivision subdivision) {
        return "subdivision/newsubdivision";
    }

    @PostMapping()
    public String create(@ModelAttribute("subdivision") @Valid Subdivision subdivision,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "subdivision/newsubdivision";

        if (subdivision.getNameSubdivision().length() < 1 || subdivision.getOrganizationSub().getPK_Organization() == 0)
            return "subdivision/newsubdivision";

        organizationDAO.save(subdivision);
        return "redirect:/subdivision";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("subdivision", organizationDAO.show(id));
        return "subdivision/editsubdivision";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("subdivision") @Valid Subdivision subdivision,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "subdivision/editsubdivision";

        if (subdivision.getNameSubdivision().length() < 1 || subdivision.getOrganizationSub().getPK_Organization() == 0)
            return "subdivision/editsubdivision";

        try {
            organizationDAO.update(id, subdivision);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/subdivision";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        organizationDAO.delete(id);
        return "redirect:/subdivision";
    }
}