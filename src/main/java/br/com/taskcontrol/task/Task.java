package br.com.taskcontrol.task;

import br.com.taskcontrol.project.Project;
import br.com.taskcontrol.task.dto.TaskCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder(builderClassName = "Builder")
@Entity(name = "task")
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_generator")
    @SequenceGenerator(name = "task_generator", sequenceName = "task_sequence", allocationSize = 1)
    private Long id;
    private String title;
    private Long frequency;
    private Integer priorityLevel;

    @Enumerated(EnumType.ORDINAL)
    private TaskStatus taskStatus;
    private Long duration;
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public static Task from(TaskCreateDTO taskCreateDTO) {
        return Task.builder()
                .title(taskCreateDTO.getTitle())
                .frequency(taskCreateDTO.getFrequency())
                .priorityLevel(taskCreateDTO.getPriorityLevel())
                .taskStatus(TaskStatus.PENDING)
                .duration(taskCreateDTO.getDuration())
                .createDate(LocalDateTime.now())
                .build();
    }
}
