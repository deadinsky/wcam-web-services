package com.thomasdedinsky.fydp.fydpweb.auth;

import com.thomasdedinsky.fydp.fydpweb.Utilities;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/")
    public String getIndex(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal != null) {
            Utilities.addModelAttributes(model, userPrincipal.getUser());
        }
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal != null) {
            Utilities.addModelAttributes(model, userPrincipal.getUser());
        }
        return "login";
    }

    @GetMapping("/signup")
    public String signUp(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal != null) {
            Utilities.addModelAttributes(model, userPrincipal.getUser());
        }
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(User user) {
        userService.signUpUser(user);
        return "redirect:/";
    }

    @GetMapping("/confirm")
    public String confirmMail(@RequestParam("token") String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);
        if (confirmationToken != null) {
            userService.confirmUser(confirmationToken);
        }
        return "redirect:/";
    }
}
