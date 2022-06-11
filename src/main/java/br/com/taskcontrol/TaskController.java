package br.com.taskcontrol;

import br.com.taskcontrol.task.dto.TaskCreateDTO;
import br.com.taskcontrol.task.dto.TaskListDTO;
import br.com.taskcontrol.task.service.CreateTaskService;
import br.com.taskcontrol.task.service.GetListTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/task")
public class TaskController {

    private final CreateTaskService createTaskService;
    private final GetListTaskService getListTaskService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskCreateDTO taskCreateDTO) {
        createTaskService.createTask(taskCreateDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskListDTO> listTask() {
        return getListTaskService.listTask();
    }
}
