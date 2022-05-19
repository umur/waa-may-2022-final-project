package com.project.accessdenied.controller;

import com.project.accessdenied.entity.State;
import com.project.accessdenied.service.StateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/states")
public class StateController {
    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping
    public List<State> getAll() {
        return stateService.getAll();
    }

    @GetMapping("/{id}")
    public State getById(@PathVariable long id) {
        return stateService.getById(id);
    }
}
