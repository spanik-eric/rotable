package at.rotable.terminplanungaufgabe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
public class CreateAppointmentPayload {
    @JsonProperty
    private String title;

    @JsonProperty
    private String description;

    @JsonProperty
    @FutureOrPresent
    private Date fromDate;

    @JsonProperty
    @Future
    private Date toDate;

    @JsonProperty
    @NotEmpty
    private List<Long> participants;
}
