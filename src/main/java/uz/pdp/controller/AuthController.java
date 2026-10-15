package uz.pdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.RegisterDTO;
import uz.pdp.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String registerPage(){
           return "auth/register";
       }

       @PostMapping("/register")
    public String register( @ModelAttribute RegisterDTO dto ){

            authService.register(dto);

           return "redirect:/auth/login";
       }


    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logoutPage(){
        return "auth/logout";
    }

}
