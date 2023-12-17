package com.example.web.controllers;

import com.example.web.dao.ContactDAO;
import com.example.web.dao.SubdivisionDAO;
import com.example.web.models.Contact;
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
@RequestMapping("/contact")
public class ContactController {

    private final ContactDAO contactDAO;

    @Autowired
    public ContactController(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("contact", contactDAO.index());
        return "contact/indexcontact";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("contact", contactDAO.show(id));
        return "contact/showcontact";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("contact") Contact contact) {
        return "contact/newcontact";
    }

    @PostMapping()
    public String create(@ModelAttribute("contact") @Valid Contact contact,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "contact/newcontact";

        if (contact.getValueContact().length() < 1 || contact.getHumanContact().getPK_Human() < 1
            || contact.getStateOfContact().getPK_StateOfContact() < 1
            || contact.getTypeOfContact().getPK_TypeOfContact() < 1)
            return "contact/newcontact";

        contactDAO.save(contact);
        return "redirect:/contact";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("contact", contactDAO.show(id));
        return "contact/editcontact";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("contact") @Valid Contact contact,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "contact/editcontact";

        if (contact.getValueContact().length() < 1 || contact.getHumanContact().getPK_Human() < 1
                || contact.getStateOfContact().getPK_StateOfContact() < 1
                || contact.getTypeOfContact().getPK_TypeOfContact() < 1)
            return "contact/editcontact";

        try {
            contactDAO.update(id, contact);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/contact";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        contactDAO.delete(id);
        return "redirect:/contact";
    }
}