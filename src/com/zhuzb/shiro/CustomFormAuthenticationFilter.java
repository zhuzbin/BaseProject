package com.zhuzb.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Desc：
 * User：ZhuZhiBin
 * Date：2018/5/17
 * Time：17:19
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpSession session = httpServletRequest.getSession();
        //页面输入的验证码
        Object randomCode = request.getParameter("randomcode");
        //从session中获取验证码
        Object validateCode = session.getAttribute("validateCode");

        if(randomCode!=null&&validateCode!=null&&!randomCode.toString().equalsIgnoreCase(validateCode.toString())){
            request.setAttribute("shiroLoginFailure","randomCodeError");
            return true;
        }
        return super.onAccessDenied(request, response, mappedValue);
    }
}
