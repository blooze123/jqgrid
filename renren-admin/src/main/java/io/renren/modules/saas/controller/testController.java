package io.renren.modules.saas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {
    @RequestMapping("toselect")
    public String toselect(){
        return "testRequestBody";
    }
}
