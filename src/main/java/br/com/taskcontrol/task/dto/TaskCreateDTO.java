package br.com.taskcontrol.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateDTO {

    private String title;
    private Long frequency;
    private Integer priorityLevel;
    private Long duration;
}
