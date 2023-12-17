package com.example.web.controllers;

//import com.example.web.dao.ExcursionDAO;
import com.example.web.models.Excursion;
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


/*
@Controller
@RequestMapping("/excursion")
public class ExcursionController {

    private final ExcursionDAO excursionDAO;

    @Autowired
    public ExcursionController(ExcursionDAO excursionDAO) {
        this.excursionDAO = excursionDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("excursion", excursionDAO.index());
        return "excursion/indexexcursion";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("excursion", excursionDAO.show(id));
        return "excursion/showexcursion";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("excursion") Excursion excursion) {
        return "excursion/newexcursion";
    }

    @PostMapping()
    public String create(@ModelAttribute("excursion") @Valid Excursion excursion,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "excursion/newexcursion";

        excursionDAO.save(excursion);
        return "redirect:/excursion";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("excursion", excursionDAO.show(id));
        return "excursion/editexcursion";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("excursion") @Valid Excursion excursion, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "excursion/editexcursion";

        try {
            excursionDAO.update(id, excursion);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/excursion";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        excursionDAO.delete(id);
        return "redirect:/excursion";
    }
}
*/