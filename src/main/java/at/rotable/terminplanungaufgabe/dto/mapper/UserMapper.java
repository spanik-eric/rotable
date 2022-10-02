package at.rotable.terminplanungaufgabe.dto.mapper;

import at.rotable.terminplanungaufgabe.dto.CreateUserPayload;
import at.rotable.terminplanungaufgabe.dto.UpdateUserPayload;
import at.rotable.terminplanungaufgabe.dto.UserResponse;
import at.rotable.terminplanungaufgabe.model.User;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    List<UserResponse> userListToUserResponseList(List<User> users);

    UserResponse userToUserResponse(User user);

    User createUserPayloadToUser(CreateUserPayload user);

    User updateUserPayloadToUser(UpdateUserPayload user);
}
