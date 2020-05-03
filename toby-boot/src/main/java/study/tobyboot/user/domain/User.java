package study.tobyboot.user.domain;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String password;
}
