package com.thomasdedinsky.fydp.fydpweb.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    public AuthenticationController(UserService userService, ConfirmationTokenService confirmationTokenService) {
        super();
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(User user) {
        userService.signUpUser(user);
        return "redirect:/";
    }

    @GetMapping("/confirm")
    String confirmMail(@RequestParam("token") String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);
        if (confirmationToken != null) {
            userService.confirmUser(confirmationToken);
        }
        return "redirect:/";
    }
}
