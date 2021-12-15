package com.github.kuzznya.bs.userservice.controller;

import com.github.kuzznya.bs.userservice.entity.AppUserEntity;
import com.github.kuzznya.bs.userservice.model.AppUser;
import com.github.kuzznya.bs.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public AppUser register(@RequestBody AppUserEntity user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public AppUser getUser(@PathVariable("id") long id) {
        return userService.getUser(id);
    }
}
