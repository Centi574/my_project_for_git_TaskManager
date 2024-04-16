package com.example.project_hex_one.controller;


import com.example.project_hex_one.dto.LabelDto;
import com.example.project_hex_one.model.Label;
import com.example.project_hex_one.service.LabelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/labels")
public class LabelController {

    private LabelService labelService;

    @Operation(summary = "Create new label")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The label has been successfully created")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Label createNewLabel(@RequestBody @Valid LabelDto labelDto) {
        return labelService.createLabel(labelDto);
    }

    @Operation(summary = "Get all labels")
    @ApiResponse(responseCode = "200", description = "All labels are found",
            content = @Content(schema = @Schema(implementation = Label.class)))
    @GetMapping()
    public List<Label> getAllLabel() {
        return labelService.getAllLabel();
    }

    @Operation(summary = "Get label by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The label is found"),
            @ApiResponse(responseCode = "404", description = "No such label found", content = @Content)})
    @GetMapping("/{id}")
    public Label getLabelById(@PathVariable(name = "id") Long id) {
        return labelService.getLabelById(id);
    }

    @Operation(summary = "Update the label by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The label is updated"),
            @ApiResponse(responseCode = "422", description = "Invalid request", content = @Content)})
    @PutMapping("/{id}")
    public Label updateLabelById(@PathVariable(name = "id") Long id, @RequestBody @Valid LabelDto labelDto) {
        return labelService.updateLabelById(id, labelDto);
    }

    @Operation(summary = "Delete the label by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Label has been successfully deleted"),
            @ApiResponse(responseCode = "404", description = "No such label found")})
    @DeleteMapping("/{id}")
    public void deleteLabelById(@PathVariable(name = "id") Long id) {
        labelService.deleteLabelById(id);
    }
}

