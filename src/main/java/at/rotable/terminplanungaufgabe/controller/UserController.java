package at.rotable.terminplanungaufgabe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("users")
public class UsersController {

    @GetMapping("/")
    public String find() {

    }
}
