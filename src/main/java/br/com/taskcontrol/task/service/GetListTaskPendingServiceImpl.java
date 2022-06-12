package br.com.taskcontrol.task.service;

import br.com.taskcontrol.task.Task;
import br.com.taskcontrol.task.TaskRepository;
import br.com.taskcontrol.task.TaskStatus;
import br.com.taskcontrol.task.dto.TaskListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetListTaskPendingServiceImpl implements GetListTaskPendingService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskListDTO> listTaskPending() {
        var taskArrayList = new ArrayList<TaskListDTO>();
        var somaDuration = 0;

        for (Task task : taskRepository.findAllByTaskStatusOrderByPriorityLevelDesc(TaskStatus.PENDING)) {
            somaDuration += task.getDuration();
            if (somaDuration <= 120) {
                taskArrayList.add(TaskListDTO.from(task));
            }
        }

        return taskArrayList;
    }
}
