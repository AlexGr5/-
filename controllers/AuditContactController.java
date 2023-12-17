package com.example.web.controllers;

import com.example.web.dao.AuditContactDAO;
import com.example.web.dao.SubdivisionDAO;
import com.example.web.models.AuditContact;
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
@RequestMapping("/auditcontact")
public class AuditContactController {

    private final AuditContactDAO auditContactDAO;

    @Autowired
    public AuditContactController(AuditContactDAO auditContactDAO) {
        this.auditContactDAO = auditContactDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("auditcontact", auditContactDAO.index());
        return "auditcontact/indexauditcontact";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("auditcontact", auditContactDAO.show(id));
        return "auditcontact/showauditcontact";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("auditcontact") AuditContact auditContact) {
        return "auditcontact/newauditcontact";
    }

    @PostMapping()
    public String create(@ModelAttribute("auditcontact") @Valid AuditContact auditContact,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "auditcontact/newauditcontact";

        auditContactDAO.save(auditContact);
        return "redirect:/auditcontact";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("auditcontact", auditContactDAO.show(id));
        return "auditcontact/editauditcontact";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("auditcontact") @Valid AuditContact auditContact,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "auditcontact/editauditcontact";

        try {
            auditContactDAO.update(id, auditContact);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/auditcontact";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        auditContactDAO.delete(id);
        return "redirect:/auditcontact";
    }
}