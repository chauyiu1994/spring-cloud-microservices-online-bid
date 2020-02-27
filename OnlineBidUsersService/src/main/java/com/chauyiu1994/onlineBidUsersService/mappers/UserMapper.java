package com.chauyiu1994.onlineBidUsersService.mappers;

import com.chauyiu1994.onlineBidUsersService.domain.User;
import com.chauyiu1994.onlineBidUsersService.models.user.SignUpAuthRequest;
import com.chauyiu1994.onlineBidUsersService.models.user.GeneralUserResponse;
import com.chauyiu1994.onlineBidUsersService.shared.UserDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        injectionStrategy =  InjectionStrategy.CONSTRUCTOR,
        imports = com.chauyiu1994.onlineBidUsersService.mappers.formatters.UserFormatter.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", expression = "java(UserFormatter.toRoleList(request.getRoles()))")
    UserDto signUpAuthRequestToUserDto(SignUpAuthRequest request);

    @Mapping(target = "enabled", defaultValue = "true")
    User userDtoToUser(UserDto userDto);

    //@Mapping(target = "id", expression = "java(user.getId().toString())")
    UserDto userToUserDto(User user);

    GeneralUserResponse userDtoToGeneralUserResponse(UserDto userDto);
}
