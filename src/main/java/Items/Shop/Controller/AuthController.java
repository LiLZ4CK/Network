package Items.Shop.Controller;

import Items.Shop.Entity.Users;
import Items.Shop.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;

@Controller
public class AuthController {

    @Autowired
    private UsersService UService;

    @GetMapping("/register")
    public String _new_user(Model model){
        model.addAttribute("user", new Users());
        return "newUser";
    }

    @PostMapping("/saveUser")
    public ResponseEntity<String> _add_user(@ModelAttribute Users user, RedirectAttributes attr) {
        try {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (UService.if_exist(user.getUsername())) {
                return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
            }
            attr.addFlashAttribute("user", user);
            Users newUser = Users.builder()
                    .Username(user.getUsername())
                    .Password(passwordEncoder.encode(user.getPassword()))
                    .build();
            System.out.println("username = " + newUser.getUsername() + " password = " + newUser.getPassword());
            UService.save(newUser);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("/login"))
                    .build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred while saving the user");
        }
    }

  /*  @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new Users());
        return "login";
    }*/

    @PostMapping("/_login_check")
    public ResponseEntity<String> check_login(@ModelAttribute Users user, RedirectAttributes attr){
       //try{
           PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
           if (!UService.if_exist(user.getUsername())){
               return new ResponseEntity<>("Username or password is incorrect!", HttpStatus.BAD_REQUEST);
           }
           attr.addFlashAttribute("user", user);
           Users usr = Users.builder()
                   .Username(user.getUsername())
                   .Password(passwordEncoder.encode(user.getPassword()))
                   .build();
       //}
        return new ResponseEntity<>("x", HttpStatus.FOUND);
    }
}
