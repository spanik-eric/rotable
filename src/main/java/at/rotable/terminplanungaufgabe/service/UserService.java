package at.rotable.terminplanungaufgabe.service;

import at.rotable.terminplanungaufgabe.dto.CreateUserPayload;
import at.rotable.terminplanungaufgabe.dto.UpdateUserPayload;
import at.rotable.terminplanungaufgabe.dto.UserResponse;
import at.rotable.terminplanungaufgabe.dto.mapper.UserMapper;
import at.rotable.terminplanungaufgabe.model.User;
import at.rotable.terminplanungaufgabe.persistance.AppointmentRepository;
import at.rotable.terminplanungaufgabe.persistance.UserRepository;
import at.rotable.terminplanungaufgabe.service.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private AppointmentRepository appointmentRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(
            UserRepository userRepository,
            AppointmentRepository appointmentRepository,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.appointmentRepository = appointmentRepository;
    }

    public List<UserResponse> getAllUsers() {
        this.LOGGER.debug("Getting all users");
        List<User> users = this.userRepository.findAll();
        return this.userMapper.userListToUserResponseList(users);
    }

    public UserResponse getUserById(long id) throws NotFoundException {
        this.LOGGER.debug("Getting user " + id);
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent())
            return this.userMapper.userToUserResponse(user.get());
        throw new NotFoundException("Could not find user with ID " + id);
    }

    public UserResponse createUser(CreateUserPayload userPayload) {
        this.LOGGER.debug("Creating new user");
        User user = this.userMapper.createUserPayloadToUser(userPayload);
        User createdUser = this.userRepository.save(user);
        return this.userMapper.userToUserResponse(createdUser);
    }

    public UserResponse updateUser(long id, UpdateUserPayload userPayload) throws NotFoundException {
        this.LOGGER.debug("Updating existing user " + id);

        // checking if user exists
        boolean userExists = this.userRepository.existsById(id);
        if (!userExists) {
            this.LOGGER.error("User tried to update non existing user " + id);
            throw new NotFoundException("Could not find user with ID " + id);
        }

        User user = this.userMapper.updateUserPayloadToUser(userPayload);
        user.setId(id);
        User updatedUser = this.userRepository.save(user);
        return this.userMapper.userToUserResponse(updatedUser);
    }

    @Transactional
    public void deleteUser(long id) throws NotFoundException {
        Optional<User> user = this.userRepository.findById(id);
        if (!user.isPresent()) {
            this.LOGGER.error("User tried to delete non existing user " + id);
            throw new NotFoundException("Could not find user with ID " + id);
        }
        User u = user.get();
        this.userRepository.delete(u);
        // need to delete appointments that do not have an entry in the user_appointment table anymore
        this.appointmentRepository.deleteAppointmentsWithoutUser();
    }
}
