package pk.group.cinemasbapp.model;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterModel {
    private String name;
    private String surname;
    private String email;
    private String password;
}
