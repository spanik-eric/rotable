package at.rotable.terminplanungaufgabe.persistance;

import at.rotable.terminplanungaufgabe.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
