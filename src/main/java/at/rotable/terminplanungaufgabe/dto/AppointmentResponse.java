package at.rotable.terminplanungaufgabe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.Date;
import java.util.List;

@Data
public class AppointmentResponse {
    @JsonProperty()
    private Long id;

    @JsonProperty()
    private String title;

    @JsonProperty()
    private String description;

    @JsonProperty()
    private Date fromDate;

    @JsonProperty()
    private Date toDate;

    @JsonProperty()
    private List<UserResponse> participants;
}
