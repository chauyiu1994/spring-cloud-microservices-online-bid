package com.chauyiu1994.onlineBidUsersService.services;

import com.chauyiu1994.onlineBidUsersService.domain.User;
import com.chauyiu1994.onlineBidUsersService.mappers.UserMapper;
import com.chauyiu1994.onlineBidUsersService.repositories.UserRepository;
import com.chauyiu1994.onlineBidUsersService.security.PBKDF2Encoder;
import com.chauyiu1994.onlineBidUsersService.shared.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PBKDF2Encoder encoder;

    private User encryptPassword(UserDto userDto) {

        userDto.setPassword(encoder.encode(userDto.getPassword()));
        return userMapper.userDtoToUser(userDto);
    }

    public Mono<UserDto> findByUsername(String username) {

        return userRepository.findByUsername(username)
                .map(userMapper::userToUserDto);
    }

    public Mono<UserDto> save(UserDto userDto) {

        LocalDateTime now = LocalDateTime.now();
        userDto.setCreateTime(now);
        userDto.setLastModifiedTime(now);
        return userRepository.save(encryptPassword(userDto))
                .map(userMapper::userToUserDto);
    }

    public Mono<UserDto> update(UserDto userDto) {

        LocalDateTime now = LocalDateTime.now();
        userDto.setLastModifiedTime(now);
        return userRepository.save(encryptPassword(userDto))
                .map(userMapper::userToUserDto);
    }

    public Mono<Long> count() {

        return userRepository.count();
    }
}
