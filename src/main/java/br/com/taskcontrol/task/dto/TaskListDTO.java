package br.com.taskcontrol.task.dto;

import br.com.taskcontrol.task.Task;
import br.com.taskcontrol.task.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(builderClassName = "Builder")
@AllArgsConstructor
@NoArgsConstructor
public class TaskListDTO {
    private Long id;
    private String title;
    private Long frequency;
    private Integer priorityLevel;
    private TaskStatus taskStatus;
    private Long duration;

    public static TaskListDTO from(Task task) {
        return TaskListDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .frequency(task.getFrequency())
                .priorityLevel(task.getPriorityLevel())
                .taskStatus(task.getTaskStatus())
                .duration(task.getDuration())
                .build();
    }
}
