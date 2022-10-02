package at.rotable.terminplanungaufgabe.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")    // User is a reserved table in H2
@SQLDelete(sql = "update app_user set deleted = true where id=?")
@Where(clause = "deleted=false")
@Data
@SequenceGenerator(initialValue = 1, name = "userIdSeq", sequenceName = "userIdSeq")
public class User implements Serializable  {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "userIdSeq")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @ManyToMany()
    @JoinTable(
            name = "user_appointment",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id"))
    List<Appointment> appointments = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;

    public User() { }

    public User(long id) {
        this.id = id;
    }
}
