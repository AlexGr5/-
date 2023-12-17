package com.example.web.controllers;

import com.example.web.dao.RateDAO;
import com.example.web.dao.SubdivisionDAO;
import com.example.web.models.Rate;
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

import java.util.Date;

@Controller
@RequestMapping("/rate")
public class RateController {

    private final RateDAO rateDAO;

    @Autowired
    public RateController(RateDAO rateDAO) {
        this.rateDAO = rateDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("rate", rateDAO.index());
        return "rate/indexrate";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("rate", rateDAO.show(id));
        return "rate/showrate";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("rate") Rate rate) {
        return "rate/newrate";
    }

    @PostMapping()
    public String create(@ModelAttribute("rate") @Valid Rate rate,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "rate/newrate";

        if (rate.getRateValue() <= 0 || rate.getDateOfEmployment().before(new Date("01.01.1900"))
            || rate.getSubdivisionRate().getPK_Subdivision() == 0 || rate.getJobTitleRate().getPK_JobTitle() == 0
            || rate.getHumanRate().getPK_Human() == 0)
            return "rate/newrate";

        rateDAO.save(rate);
        return "redirect:/rate";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("rate", rateDAO.show(id));
        return "rate/editrate";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("Rate") @Valid Rate rate,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "rate/editrate";

        if (rate.getRateValue() <= 0 || rate.getDateOfEmployment().before(new Date("01.01.1900"))
                || rate.getSubdivisionRate().getPK_Subdivision() == 0 || rate.getJobTitleRate().getPK_JobTitle() == 0
                || rate.getHumanRate().getPK_Human() == 0)
            return "rate/editrate";

        try {
            rateDAO.update(id, rate);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/rate";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        rateDAO.delete(id);
        return "redirect:/rate";
    }
}