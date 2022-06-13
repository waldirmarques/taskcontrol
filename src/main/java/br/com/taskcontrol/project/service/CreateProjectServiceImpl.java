package br.com.taskcontrol.project.service;

import br.com.taskcontrol.project.Project;
import br.com.taskcontrol.project.ProjectRepository;
import br.com.taskcontrol.project.dto.ProjectCreateDTO;
import br.com.taskcontrol.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProjectServiceImpl implements CreateProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    @Override
    public void createProject(ProjectCreateDTO projectCreateDTO) {
        var project = projectRepository.save(Project.from(projectCreateDTO));

        projectCreateDTO.getTaskTitles()
                .forEach(title -> {
                    var optionalTask = taskRepository.findByTitle(title);
                    optionalTask.ifPresent(task -> {
                        task.setProject(project);
                        taskRepository.save(task);
                    });
                });
    }
}
