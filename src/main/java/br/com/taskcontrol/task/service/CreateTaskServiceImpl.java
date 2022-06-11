package br.com.taskcontrol.task.service;

import br.com.taskcontrol.task.Task;
import br.com.taskcontrol.task.TaskRepository;
import br.com.taskcontrol.task.dto.TaskCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTaskServiceImpl implements CreateTaskService {

    private final TaskRepository taskRepository;
    @Override
    public void createTask(TaskCreateDTO taskCreateDTO) {
        taskRepository.save(Task.from(taskCreateDTO));
    }
}
