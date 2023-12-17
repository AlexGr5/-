package com.example.web.controllers;

import com.example.web.dao.SubdivisionDAO;
import com.example.web.dao.TypeOfContactDAO;
import com.example.web.models.Subdivision;
import com.example.web.models.TypeOfContact;
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
@RequestMapping("/typeofcontact")
public class TypeOfContactController {

    private final TypeOfContactDAO organizationDAO;

    @Autowired
    public TypeOfContactController(TypeOfContactDAO typeOfContactDAO) {
        this.organizationDAO = typeOfContactDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("typeofcontact", organizationDAO.index());
        return "typeofcontact/indextypeofcontact";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("typeofcontact", organizationDAO.show(id));
        return "typeofcontact/showtypeofcontact";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("typeofcontact") TypeOfContact typeOfContact) {
        return "typeofcontact/newtypeofcontact";
    }

    @PostMapping()
    public String create(@ModelAttribute("typeofcontact") @Valid TypeOfContact typeOfContact,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "typeofcontact/newtypeofcontact";

        if (typeOfContact.getValueTupeContact().length() < 1)
            return "typeofcontact/newtypeofcontact";

        organizationDAO.save(typeOfContact);
        return "redirect:/typeofcontact";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("typeofcontact", organizationDAO.show(id));
        return "typeofcontact/edittypeofcontact";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("typeofcontact") @Valid TypeOfContact typeOfContact,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "typeofcontact/edittypeofcontact";

        if (typeOfContact.getValueTupeContact().length() < 1)
            return "typeofcontact/edittypeofcontact";

        try {
            organizationDAO.update(id, typeOfContact);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/typeofcontact";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        organizationDAO.delete(id);
        return "redirect:/typeofcontact";
    }
}