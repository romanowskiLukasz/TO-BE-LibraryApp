package pk.group.cinemasbapp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginModel {
    private String email;
    private String password;
}
