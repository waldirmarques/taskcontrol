package br.com.taskcontrol.task.service;

import br.com.taskcontrol.task.dto.TaskCreateDTO;

@FunctionalInterface
public interface CreateTaskService {
    void createTask(TaskCreateDTO taskCreateDTO);
}
