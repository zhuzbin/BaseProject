package com.zhuzb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Desc：
 * User：ZhuZhiBin
 * Date：2018/5/23
 * Time：23:19
 */
@Controller
public class TestController {

    @RequestMapping("/validata")
    public ModelAndView validata(Model model){
        return new ModelAndView("validata/validata");
    }
}
