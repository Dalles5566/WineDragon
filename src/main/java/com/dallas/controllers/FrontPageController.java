package com.dallas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontPageController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping(value="/winelist", method = RequestMethod.GET)
    public String gotoWineList() {
        return "winelist";
    }

    @RequestMapping(value="/winecrud", method = RequestMethod.GET)
    public String gotoWineCRUD() {
        return "winecrud";
    }

}