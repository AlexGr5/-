package com.example.web.controllers;

import com.example.web.dao.OfficeDAO;
import com.example.web.dao.SubdivisionDAO;
import com.example.web.models.Office;
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
@RequestMapping("/office")
public class OfficeController {

    private final OfficeDAO officeDAO;

    @Autowired
    public OfficeController(OfficeDAO officeDAO) {
        this.officeDAO = officeDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("office", officeDAO.index());
        return "office/indexoffice";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("office", officeDAO.show(id));
        return "office/showoffice";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("office") Office office) {
        return "office/newoffice";
    }

    @PostMapping()
    public String create(@ModelAttribute("office") @Valid Office office,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "office/newoffice";

        if (office.getNumberOffice() < 1 || office.getHumanOffice().getPK_Human() < 1
                || office.getSubdivisionOffice().getPK_Subdivision() < 1)
            return "office/newoffice";

        officeDAO.save(office);
        return "redirect:/office";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("office", officeDAO.show(id));
        return "office/editoffice";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("office") @Valid Office office,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "office/editoffice";

        if (office.getNumberOffice() < 1 || office.getHumanOffice().getPK_Human() < 1
                || office.getSubdivisionOffice().getPK_Subdivision() < 1)
            return "office/editoffice";

        try {
            officeDAO.update(id, office);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/office";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        officeDAO.delete(id);
        return "redirect:/office";
    }
}