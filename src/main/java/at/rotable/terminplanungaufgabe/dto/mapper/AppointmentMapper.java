package at.rotable.terminplanungaufgabe.dto.mapper;

import at.rotable.terminplanungaufgabe.dto.*;
import at.rotable.terminplanungaufgabe.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    List<AppointmentResponse> appointmentListToAppointmentResponseList(List<Appointment> appointments);

    @Mapping(target = "participants", expression = "java(userMapper.userListToUserResponseList(appointment.getUsers().stream().toList()))")
    AppointmentResponse appointmentToAppointmentResponse(Appointment appointment);

    Appointment createAppointmentPayloadToAppointment(CreateAppointmentPayload appointment);
}
