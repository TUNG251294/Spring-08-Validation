package com.example.controller;

import com.example.model.UserAccount;
import com.example.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    IAccountService accountService;
    @GetMapping
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<UserAccount> accounts = accountService.findAll();
        modelAndView.addObject("accounts", accounts);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("userAccount",new UserAccount());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView createAccount(@Valid UserAccount userAccount,BindingResult bindingResult){
        new UserAccount().validate(userAccount,bindingResult);
        if (bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        } else {
            accountService.save(userAccount);
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        }
    }
//    public String save(@Valid @ModelAttribute("useraccount")UserAccount userAccount, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()){
//            return "error";
//        }
//        return "success";
//    }

}
