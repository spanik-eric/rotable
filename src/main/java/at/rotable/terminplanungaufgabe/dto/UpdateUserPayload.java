package at.rotable.terminplanungaufgabe.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateUserRequest {
    @NotBlank
    @Size(min = 0, max = 255)
    private String firstName;

    @NotBlank
    @Size(min = 0, max = 255)
    private String lastName;
}
