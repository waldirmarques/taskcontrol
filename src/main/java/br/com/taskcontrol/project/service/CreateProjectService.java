package br.com.taskcontrol.project.service;

import br.com.taskcontrol.project.dto.ProjectCreateDTO;

@FunctionalInterface
public interface CreateProjectService {
    void createProject(ProjectCreateDTO projectCreateDTO);
}
