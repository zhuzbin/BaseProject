package com.zhuzb.web.controller;

import com.zhuzb.entity.Resource;
import com.zhuzb.entity.User;
import com.zhuzb.service.ResourceService;
import com.zhuzb.service.UserService;
import com.zhuzb.util.ValidateCode;
import com.zhuzb.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
public class IndexController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(@CurrentUser User loginUser, Model model) {
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        /*return "index";*/
        return "indexH";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/imageCode")
    @ResponseBody
    public void ImageCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ValidateCode vCode = new ValidateCode(100,30,4,100);
        request.getSession().removeAttribute("validateCode");
        request.getSession().setAttribute("validateCode",ValidateCode.getCode());
        vCode.write(response.getOutputStream());
    }


}
