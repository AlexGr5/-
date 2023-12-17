package com.example.web.controllers;

import com.example.web.dao.JobTitleDAO;
import com.example.web.dao.SubdivisionDAO;
import com.example.web.models.JobTitle;
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
@RequestMapping("/jobtitle")
public class JobTitleController {

    private final JobTitleDAO jobTitleDAO;

    @Autowired
    public JobTitleController(JobTitleDAO jobTitleDAO) {
        this.jobTitleDAO = jobTitleDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("jobtitle", jobTitleDAO.index());
        return "jobtitle/indexjobtitle";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("jobtitle", jobTitleDAO.show(id));
        return "jobtitle/showjobtitle";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("jobtitle") JobTitle jobTitle) {
        return "jobtitle/newjobtitle";
    }

    @PostMapping()
    public String create(@ModelAttribute("jobtitle") @Valid JobTitle jobTitle,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "jobtitle/newjobtitle";

        if (jobTitle.getNameOfJobTitle().length() < 1)
            return "jobtitle/newjobtitle";

        jobTitleDAO.save(jobTitle);
        return "redirect:/jobtitle";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("jobtitle", jobTitleDAO.show(id));
        return "jobtitle/editjobtitle";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("jobtitle") @Valid JobTitle jobTitle,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "jobtitle/editjobtitle";

        if (jobTitle.getNameOfJobTitle().length() < 1)
            return "jobtitle/editjobtitle";

        try {
            jobTitleDAO.update(id, jobTitle);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/jobtitle";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        jobTitleDAO.delete(id);
        return "redirect:/jobtitle";
    }
}