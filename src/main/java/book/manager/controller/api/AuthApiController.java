package book.manager.controller.api;

import book.manager.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/auth")
public class AuthApiController {

    @Resource
    AuthService service;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestParam("username") String name,
                           @RequestParam("gender") String gender,
                           @RequestParam("grade") String grade,
                           @RequestParam("password") String password){
        service.register(name,gender,grade,password);
        return "redirect:/login";

    }

}
