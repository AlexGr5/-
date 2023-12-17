package com.example.web.controllers;

import com.example.web.dao.OrganizationDAO;
import com.example.web.dao.QuestionDAO;
import com.example.web.models.InputFields;
import com.example.web.models.Organization;
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
@RequestMapping("/query")
public class QueryController {

    private final QuestionDAO questionDAO;

    private InputFields params = new InputFields();

    @Autowired
    public QueryController(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("query", questionDAO.query(params));
        params = new InputFields();
        return "query/indexquery";
    }


    /*
    @PostMapping()
    public String work(@ModelAttribute("params") InputFields params) {
        this.params = params;
        return "redirect:/question";
    }
     */

    @PostMapping()
    public String create(@ModelAttribute("params") @Valid InputFields params,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "query/newquery";

        this.params = params;
        return "redirect:/query";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("params") InputFields params) {
        return "query/newquery";
    }

}