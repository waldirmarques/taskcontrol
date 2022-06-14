package br.com.taskcontrol.user.dto;

import br.com.taskcontrol.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
public class UserSuccessfulLoginDTO {

    private Long id;

    private String name;

    private String email;

    private String token;

    public static UserSuccessfulLoginDTO from(User loggedUser) {
        return UserSuccessfulLoginDTO.builder()
                .id(loggedUser.getId())
                .name(loggedUser.getName())
                .email(loggedUser.getEmail())
                .build();
    }
}
