package com.ceniuch.bluediamondbackend.users;

import com.ceniuch.bluediamondbackend.users.dtos.CreateUserDto;
import com.ceniuch.bluediamondbackend.users.dtos.GetUserDtoId;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "User Controller",
    description = "Controller for CRUD operations on the user"
)
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Create a new User according to a provided CreateUserDto. A username can only exist once."
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    GetUserDtoId createUser(@RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }

}
