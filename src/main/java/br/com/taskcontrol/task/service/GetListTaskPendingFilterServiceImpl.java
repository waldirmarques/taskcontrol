package br.com.taskcontrol.task.service;

import br.com.taskcontrol.task.TaskRepository;
import br.com.taskcontrol.task.dto.TaskListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetListTaskPendingFilterServiceImpl implements GetListTaskPendingFilterService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskListDTO> listTaskPendingFilter(Long day, Long week, Long month) {
        LocalDateTime date = null;

        if (day != null) {
            date = LocalDateTime.now().plusDays(day);
        } else if (week != null) {
            date = LocalDateTime.now().plusWeeks(week);
        } else if (month != null) {
            date = LocalDateTime.now().plusMonths(month);
        } else {
            log.info("Não foi informado paramentos");
        }

        return taskRepository.findAllByTaskStatusAndCreateDate(date)
                .stream().map(TaskListDTO::from)
                .collect(Collectors.toList());
    }
}
