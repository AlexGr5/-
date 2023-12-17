package com.example.web.controllers;

import com.example.web.dao.BranchDAO;
import com.example.web.dao.OrganizationDAO;
import com.example.web.models.Branch;
import com.example.web.models.Organization;
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
@RequestMapping("/branch")
public class BranchController {

    private final BranchDAO branchDAO;

    @Autowired
    public BranchController(BranchDAO branchDAO) {
        this.branchDAO = branchDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("branch", branchDAO.index());
        return "branch/indexbranch";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("branch", branchDAO.show(id));
        return "branch/showbranch";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("branch") Branch branch) {
        return "branch/newbranch";
    }

    @PostMapping()
    public String create(@ModelAttribute("branch") @Valid Branch branch,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "branch/newbranch";

        if (branch.getOrganizationBran().getPK_Organization() == 0)
            return "branch/newbranch";

        branchDAO.save(branch);
        return "redirect:/branch";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("branch", branchDAO.show(id));
        return "branch/editbranch";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("branch") @Valid Branch branch,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "branch/editbranch";

        if (branch.getOrganizationBran().getPK_Organization() == 0)
            return "branch/editbranch";

        try {
            branchDAO.update(id, branch);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/branch";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        branchDAO.delete(id);
        return "redirect:/branch";
    }
}