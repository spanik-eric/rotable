package at.rotable.terminplanungaufgabe.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
@SequenceGenerator(initialValue = 1, name = "appointmentIdSeq", sequenceName = "appointmentIdSeq")
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "appointmentIdSeq")
    private Long id;

    private String title;

    private String description;

    private Date fromDate;

    private Date toDate;

    @ManyToMany(mappedBy = "appointments", fetch = FetchType.EAGER)
    List<User> users = new ArrayList<>();

    @Override
    public String toString() {
        return "Appointment {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", users=" + users.stream().map(user -> user.getId() + ": " + user.getEmail()).collect(Collectors.toList()) +
                '}';
    }
}
