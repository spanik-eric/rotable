package at.rotable.terminplanungaufgabe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UpdateUserPayload {
    @JsonProperty()
    @NotBlank()
    @Size(min = 1, max = 255)
    private String firstName;

    @JsonProperty()
    @NotBlank()
    @Size(min = 1, max = 255)
    private String lastName;

    @JsonProperty()
    @NotBlank()
    @Email()
    private String email;
}
