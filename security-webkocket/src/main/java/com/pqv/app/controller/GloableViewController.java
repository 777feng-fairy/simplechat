package com.pqv.app.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GloableViewController {
    
    @RequestMapping(value = {"/views/","/views/**"},method = RequestMethod.GET)
    public String index() {
        return "forward://index.html";
    }
    
}
