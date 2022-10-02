package at.rotable.terminplanungaufgabe.service;

import at.rotable.terminplanungaufgabe.dto.AppointmentResponse;
import at.rotable.terminplanungaufgabe.dto.CreateAppointmentPayload;
import at.rotable.terminplanungaufgabe.dto.mapper.AppointmentMapper;
import at.rotable.terminplanungaufgabe.model.Appointment;
import at.rotable.terminplanungaufgabe.model.User;
import at.rotable.terminplanungaufgabe.persistance.AppointmentRepository;
import at.rotable.terminplanungaufgabe.persistance.UserRepository;
import at.rotable.terminplanungaufgabe.service.exception.BadRequestException;
import at.rotable.terminplanungaufgabe.service.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AppointmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentService.class);
    private AppointmentRepository appointmentRepository;
    private UserRepository userRepository;
    private AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentService(
            AppointmentRepository appointmentRepository,
            AppointmentMapper appointmentMapper,
            UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.userRepository = userRepository;
    }

    public List<AppointmentResponse> getAllAppointments(Long userId, Date dateFrom, Date dateTo) {
        this.LOGGER.debug("Getting all appointments: " +
            ("userId: " + (userId != null && userId > 0 ? userId.toString() : "null")) +
            (", dateFrom: " + (dateFrom != null ? dateFrom.toString() : "null")) +
            (", dateTo: " + (dateTo != null ? dateTo.toString() : "null")));
        List<Appointment> appointmentList = this.appointmentRepository.findAllAppointmentsByUserIdAndDateFromAndDateTo(userId, dateFrom, dateTo);
        return this.appointmentMapper.appointmentListToAppointmentResponseList(appointmentList);
    }

    public AppointmentResponse getAppointmentById(long id) throws NotFoundException {
        this.LOGGER.debug("Getting single appointment with ID " + id);
        Optional<Appointment> appointment = this.appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            return this.appointmentMapper.appointmentToAppointmentResponse(appointment.get());
        }
        throw new NotFoundException("Could not find appointment with ID " + id);
    }

    public AppointmentResponse createAppointment(CreateAppointmentPayload appointmentPayload) throws BadRequestException {
        this.LOGGER.debug("Creating new appointment with participants " + appointmentPayload.getParticipants());
        // check for overlapping appointments
        List<Appointment> appointments = this.appointmentRepository.findAppointmentsWithUsers(appointmentPayload.getFromDate(), appointmentPayload.getToDate(), appointmentPayload.getParticipants());
        if (appointments.size() > 0) {
            this.LOGGER.error("Overlapping appointment check failed. Overlapping appointments: " + appointments);
            throw new BadRequestException("Could not create appointment. There are some overlapping appointments: " + appointments);
        }

        // check for non existing users
        List<User> participants = this.userRepository.findAllByIdIn(appointmentPayload.getParticipants());
        for (long l : appointmentPayload.getParticipants()) {
            if (participants.stream().filter(user -> user.getId() == l).findFirst().isEmpty()) {
                this.LOGGER.error("User check failed. User provided userId that does not exist: " + l);
                throw new BadRequestException("Could not create appointments. No user found with ID " + l);
            }
        }

        // create appointment and associations
        this.LOGGER.debug("Persisting appointment ... ");
        Appointment appointment = this.appointmentMapper.createAppointmentPayloadToAppointment(appointmentPayload);
        Appointment savedAppointment = this.appointmentRepository.save(appointment);

        this.LOGGER.debug("Persisting appointment relation data for users ... ");
        participants.stream().forEach(user -> user.getAppointments().add(savedAppointment));
        this.userRepository.saveAll(new ArrayList<>(participants));
        savedAppointment.setUsers(participants);

        return this.appointmentMapper.appointmentToAppointmentResponse(savedAppointment);
    }

    public void deleteAppointment(long id) throws NotFoundException {
        Optional<Appointment> appointment = this.appointmentRepository.findById(id);
        if (appointment.isEmpty()) {
            this.LOGGER.error("User tried to delete non existing appointment with ID " + id);
            throw new NotFoundException("Could not find appointment with ID " + id);
        }

        Appointment a = appointment.get();

        // delete appointment association from users
        this.LOGGER.debug("Deleting appointment associations to users ...");
        a.getUsers().forEach(user -> user.getAppointments().remove(a));
        this.userRepository.saveAll(a.getUsers());

        // delete the appointment
        this.LOGGER.debug("Deleting appointment ...");
        this.appointmentRepository.deleteById(id);
    }
}
