package at.rotable.terminplanungaufgabe.controller;

import at.rotable.terminplanungaufgabe.dto.AppointmentResponse;
import at.rotable.terminplanungaufgabe.dto.CreateAppointmentPayload;
import at.rotable.terminplanungaufgabe.service.AppointmentService;
import at.rotable.terminplanungaufgabe.service.exception.BadRequestException;
import at.rotable.terminplanungaufgabe.service.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> getAllAppointments(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dateTo) throws BadRequestException {
        if (dateTo != null && dateFrom != null && dateTo.before(dateFrom))
            throw new BadRequestException("From date must be before to date.");
        return this.appointmentService.getAllAppointments(userId, dateFrom, dateTo);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse getAppointment(@PathVariable("id") long id) throws NotFoundException {
        return this.appointmentService.getAppointmentById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse createAppointment(@Valid @RequestBody CreateAppointmentPayload appointment) throws BadRequestException {
        if (appointment.getToDate().equals(appointment.getFromDate()) || appointment.getToDate().before(appointment.getFromDate()))
            throw new BadRequestException("From date must be before to date.");
        return this.appointmentService.createAppointment(appointment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAppointment(@PathVariable("id") long id) throws NotFoundException {
        this.appointmentService.deleteAppointment(id);
    }
}
