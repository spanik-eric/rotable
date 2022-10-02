package at.rotable.terminplanungaufgabe.controller;

import at.rotable.terminplanungaufgabe.dto.CreateUserPayload;
import at.rotable.terminplanungaufgabe.dto.UpdateUserPayload;
import at.rotable.terminplanungaufgabe.dto.UserResponse;
import at.rotable.terminplanungaufgabe.service.UserService;
import at.rotable.terminplanungaufgabe.service.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @Operation(summary = "Get all users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user details of a single user")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable("id") long id) throws NotFoundException {
        UserResponse userResponse = this.userService.getUserById(id);
        return userResponse;
    }

    @PostMapping()
    @Operation(summary = "Create a new user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(
            @Parameter(description="User data", required=true)
            @Valid @RequestBody CreateUserPayload user) {
        UserResponse createdUser = this.userService.createUser(user);
        return createdUser;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a single user")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@PathVariable("id") long id,
            @Parameter(description="User data", required=true)
            @Valid @RequestBody UpdateUserPayload user) throws NotFoundException {
        return this.userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a single user")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") long id) throws NotFoundException {
        this.userService.deleteUser(id);
    }
}
