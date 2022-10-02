package at.rotable.terminplanungaufgabe.persistance;

import at.rotable.terminplanungaufgabe.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select distinct a from Appointment a left join a.users ua where " +
            "(:userId is null or :userId = ua.id) and " +
            "(:fromDate is null or a.toDate >= :fromDate) and " +
            "(:toDate is null or a.fromDate <= :toDate)")
    List<Appointment> findAllAppointmentsByUserIdAndDateFromAndDateTo(Long userId, Date fromDate, Date toDate);

    @Query("select distinct a from Appointment a join a.users ua where ua.id in :users and a.fromDate >= :fromDate and a.toDate <= :toDate")
    List<Appointment> findAppointmentsWithUsers(Date fromDate, Date toDate, List<Long> users);

    @Modifying
    @Query("delete from Appointment a where a.users.size = 0")
    void deleteAppointmentsWithoutUser();
}
