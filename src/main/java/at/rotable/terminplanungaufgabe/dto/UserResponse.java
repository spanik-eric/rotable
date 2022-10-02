package at.rotable.terminplanungaufgabe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class UserResponse {
    @JsonProperty()
    private Long id;

    @JsonProperty()
    private String firstName;

    @JsonProperty()
    private String lastName;

    @JsonProperty()
    private String email;
}
