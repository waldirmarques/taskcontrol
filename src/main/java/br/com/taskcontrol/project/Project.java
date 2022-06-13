package br.com.taskcontrol.project;

import br.com.taskcontrol.project.dto.ProjectCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Getter
@Setter
@Builder(builderClassName = "Builder")
@Entity(name = "tb_project")
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_generator")
    @SequenceGenerator(name = "project_generator", sequenceName = "project_sequence", allocationSize = 1)
    private Long id;
    private String name;

    public static Project from(ProjectCreateDTO projectCreateDTO) {
        return Project.builder()
                .name(projectCreateDTO.getName())
                .build();
    }
}
