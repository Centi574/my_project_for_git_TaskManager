package com.example.project_hex_one.controller;

import com.example.project_hex_one.dto.UserDto;
import com.example.project_hex_one.model.User;
import com.example.project_hex_one.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping()
    public User createNewUser(@RequestBody UserDto userDto) {
        return userService.createNewUser(userDto);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(name = "id") long id) {
        return userService.getUserById(id);
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable(name = "id") long id, @RequestBody @Valid UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") long id) {
        userService.deleteById(id);
    }

}
