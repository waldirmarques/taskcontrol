package br.com.taskcontrol.task.service;

import br.com.taskcontrol.task.TaskRepository;
import br.com.taskcontrol.task.dto.TaskListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetListTaskServiceImpl implements GetListTaskService {

    private final TaskRepository taskRepository;
    @Override
    public List<TaskListDTO> listTask() {
        return taskRepository.findAllByOrderByTaskStatusAsc()
                .stream()
                .map(TaskListDTO::from)
                .collect(Collectors.toList());
    }
}
