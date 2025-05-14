package com.api.musiconnect.controller;

import com.api.musiconnect.dto.request.ProjectRequestDTO;
import com.api.musiconnect.dto.response.ProjectResponseDTO;
import com.api.musiconnect.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Crear un proyecto
    @PostMapping
    public ProjectResponseDTO createProject(@RequestBody ProjectRequestDTO projectRequestDTO) {
        return projectService.createProject(projectRequestDTO);
    }

    // Obtener proyectos por creador
    @GetMapping("/creator/{creatorId}")
    public List<ProjectResponseDTO> getProjectsByCreator(@PathVariable Long creatorId) {
        return projectService.getProjectsByCreator(creatorId);
    }
}
