package Items.Shop.Controller;

import Items.Shop.Entity.Users;
import Items.Shop.Service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;


@Controller
public class UsersController {

    //@Autowired
    //private UsersService UService;

    @GetMapping("/home")
    public String home() {
        System.out.println("welcome home");
        return "home";
    }



}

