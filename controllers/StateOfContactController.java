package com.example.web.controllers;

import com.example.web.dao.StateOfContactDAO;
import com.example.web.dao.SubdivisionDAO;
import com.example.web.models.StateOfContact;
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
@RequestMapping("/stateofcontact")
public class StateOfContactController {

    private final StateOfContactDAO stateOfContactDAO;

    @Autowired
    public StateOfContactController(StateOfContactDAO stateOfContactDAO) {
        this.stateOfContactDAO = stateOfContactDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("stateofcontact", stateOfContactDAO.index());
        return "stateofcontact/indexstateofcontact";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("stateofcontact", stateOfContactDAO.show(id));
        return "stateofcontact/showstateofcontact";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("stateofcontact") StateOfContact stateOfContact) {
        return "stateofcontact/newstateofcontact";
    }

    @PostMapping()
    public String create(@ModelAttribute("stateofcontact") @Valid StateOfContact stateOfContact,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "stateofcontact/newstateofcontact";

        if (stateOfContact.getValueContactState().length() < 1)
            return "stateofcontact/newstateofcontact";

        stateOfContactDAO.save(stateOfContact);
        return "redirect:/stateofcontact";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("stateofcontact", stateOfContactDAO.show(id));
        return "stateofcontact/editstateofcontact";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("stateofcontact") @Valid StateOfContact stateOfContact,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "stateofcontact/editstateofcontact";

        if (stateOfContact.getValueContactState().length() < 1)
            return "stateofcontact/editstateofcontact";

        try {
            stateOfContactDAO.update(id, stateOfContact);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/stateofcontact";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        stateOfContactDAO.delete(id);
        return "redirect:/stateofcontact";
    }
}