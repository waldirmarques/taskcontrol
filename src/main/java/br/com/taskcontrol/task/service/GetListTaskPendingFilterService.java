package br.com.taskcontrol.task.service;

import br.com.taskcontrol.task.dto.TaskListDTO;

import java.util.List;

@FunctionalInterface
public interface GetListTaskPendingFilterService {
    List<TaskListDTO> listTaskPendingFilter(Long day, Long week, Long month);
}
