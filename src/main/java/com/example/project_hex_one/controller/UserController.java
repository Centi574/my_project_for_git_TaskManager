package com.example.project_hex_one.controller;

import com.example.project_hex_one.dto.UserDto;
import com.example.project_hex_one.model.User;
import com.example.project_hex_one.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private static final String OWNER = "@userRepository.findById(#id).get().getEmail() == authentication.getName()";

    @Operation(summary = "Create new user")
    @ApiResponse(responseCode = "201", description = "User successfully created")
    @PostMapping()
    public ResponseEntity<UserDto> createNewUser(@RequestBody UserDto userDto) {
        userService.createNewUser(userDto);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User is found"),
            @ApiResponse(responseCode = "404", description = "No such user found")})
    @GetMapping("/{id}")
    public ResponseEntity<Long> getUserById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(id);
    }

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "All users are found")
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @Operation(summary = "Update the user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user is updated",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "The user is not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden to update"),
            @ApiResponse(responseCode = "422", description = "Invalid request")})
    @PreAuthorize(OWNER)
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Long id, @RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Delete the user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user has been successfully deleted"),
            @ApiResponse(responseCode = "404", description = "The user is not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden to delete"),
            @ApiResponse(responseCode = "422", description = "Data integrity violation")})
    @PreAuthorize(OWNER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(id);
    }
}
