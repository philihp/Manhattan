package com.philihp.manhattan.mvc;

 import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.philihp.manhattan.domain.User;
import com.philihp.manhattan.repo.UserDao;

@Controller
@RequestMapping(value="/")
public class UserController
{
    @Autowired
    private UserDao userDao;

    @RequestMapping(method=RequestMethod.GET)
    public String displaySortedMembers(Model model)
    {
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userDao.findAllOrderedByName());
        return "index";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String registerNewMember(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model)
    {
        if (!result.hasErrors()) {
            userDao.register(newUser);
            return "redirect:/";
        }
        else {
            model.addAttribute("users", userDao.findAllOrderedByName());
            return "index";
        }
    }
}
