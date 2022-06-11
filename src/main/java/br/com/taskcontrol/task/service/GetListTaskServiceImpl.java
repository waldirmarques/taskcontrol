package br.com.taskcontrol.task.service;

import br.com.taskcontrol.task.TaskRepository;
import br.com.taskcontrol.task.dto.TaskListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetListTaskServiceImpl implements GetListTaskService {

    private final TaskRepository taskRepository;
    @Override
    public List<TaskListDTO> listTask() {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC, "taskStatus"))
                .stream()
                .map(TaskListDTO::from)
                .collect(Collectors.toList());
    }
}
