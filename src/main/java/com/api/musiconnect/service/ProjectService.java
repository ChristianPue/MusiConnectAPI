package com.api.musiconnect.service;

import com.api.musiconnect.dto.request.ProjectRequestDTO;
import com.api.musiconnect.dto.response.ProjectResponseDTO;
import com.api.musiconnect.model.entity.Project;
import com.api.musiconnect.model.entity.User;
import com.api.musiconnect.repository.ProjectRepository;
import com.api.musiconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    // Método para crear un proyecto
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO) {
        User creator = userRepository.findById(projectRequestDTO.getCreatorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Project project = Project.builder()
                .name(projectRequestDTO.getName())
                .description(projectRequestDTO.getDescription())
                .creator(creator)
                .startDate(projectRequestDTO.getStartDate())
                .status(projectRequestDTO.getStatus())
                .createdAt(LocalDateTime.now())
                .build();

        project = projectRepository.save(project);

        return mapToProjectResponseDTO(project);
    }

    // Método para obtener los proyectos de un usuario
    public List<ProjectResponseDTO> getProjectsByCreator(Long creatorId) {
        List<Project> projects = projectRepository.findByCreatorId(creatorId);
        return projects.stream().map(this::mapToProjectResponseDTO).collect(Collectors.toList());
    }

    // Mapea la entidad Project a un DTO de respuesta
    private ProjectResponseDTO mapToProjectResponseDTO(Project project) {
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        projectResponseDTO.setId(project.getId());
        projectResponseDTO.setName(project.getName());
        projectResponseDTO.setDescription(project.getDescription());
        projectResponseDTO.setCreatorId(project.getCreator().getId());
        projectResponseDTO.setCreatedAt(project.getCreatedAt());
        projectResponseDTO.setStartDate(project.getStartDate());
        projectResponseDTO.setStatus(project.getStatus());
        return projectResponseDTO;
    }
}
