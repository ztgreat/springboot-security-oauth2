package com.springboot.security.controller;

import com.springboot.security.base.ResponseEntity;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
@SessionAttributes("authorizationRequest")
public class OauthCol {


    @RequestMapping("/oauth/my_confirm_access")
    @ResponseBody
    public ResponseEntity<Object> getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {

        ResponseEntity<Object>res=new ResponseEntity<>();
        res.setData(model);
        return res;
    }

}
