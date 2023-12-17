package com.example.web.controllers;

import com.example.web.dao.HumanDAO;
import com.example.web.dao.SubdivisionDAO;
import com.example.web.models.Human;
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
@RequestMapping("/human")
public class HumanController {

    private final HumanDAO humanDAO;

    @Autowired
    public HumanController(HumanDAO humanDAO) {
        this.humanDAO = humanDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("human", humanDAO.index());
        return "human/indexhuman";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("human", humanDAO.show(id));
        return "human/showhuman";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("human") Human human) {
        return "human/newhuman";
    }

    @PostMapping()
    public String create(@ModelAttribute("human") @Valid Human human,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "human/newhuman";

        if (human.getNameHuman().length() < 1 || human.getSurname().length() < 1)
            return "human/newhuman";

        humanDAO.save(human);
        return "redirect:/human";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("human", humanDAO.show(id));
        return "human/edithuman";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("human") @Valid Human human,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "human/edithuman";

        if (human.getNameHuman().length() < 1 || human.getSurname().length() < 1)
            return "human/edithuman";

        try {
            humanDAO.update(id, human);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/human";
    }

    @PatchMapping("/{id}/dismissal")
    public String dismissal(@PathVariable("id") int id) {

        humanDAO.dismissal(id);
        return "redirect:/human";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        humanDAO.delete(id);
        return "redirect:/human";
    }
}