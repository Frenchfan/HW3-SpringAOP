package com.sumkin.hw3.controllers;

import com.sumkin.hw3.model.User;
import com.sumkin.hw3.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Создать пользователя")
    public User createUser(@RequestBody User user) {
        log.info("trying to create user with user = " + user);
        return userService.createUser(user);
    }

    @GetMapping
    @Operation(summary = "Получить список пользователей")
    public List<User> getUsers() {
        log.info("trying to get all users");
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить пользователя")
    public void deleteUser(@PathVariable Long id) {
        log.info("trying to delete user with id = " + id);
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить пользователя")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User user) {
        log.info("trying to update user with id = " + id);
        return userService.updateUser(user, id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя")
    public User getUserById(@PathVariable Long id) {
        log.info("trying to get user with id = " + id);
        return userService.getUserById(id);
    }


}
