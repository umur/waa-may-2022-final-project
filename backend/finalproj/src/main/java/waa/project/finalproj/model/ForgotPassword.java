package waa.project.finalproj.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForgotPassword {
    private String email;
    private String link;
}
