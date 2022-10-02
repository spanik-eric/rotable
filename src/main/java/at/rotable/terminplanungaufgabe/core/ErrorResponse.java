package at.rotable.terminplanungaufgabe.core;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ErrorResponse {
    @NotBlank
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
