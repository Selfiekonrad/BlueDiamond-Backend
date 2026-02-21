package com.ceniuch.bluediamondbackend.users;

import com.ceniuch.bluediamondbackend.users.dtos.CreateUserDto;
import com.ceniuch.bluediamondbackend.users.dtos.GetUserDto;
import com.ceniuch.bluediamondbackend.users.exceptions.UserAlreadyExistsException;
import com.ceniuch.bluediamondbackend.users.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;
import static com.ceniuch.bluediamondbackend.users.mappers.CreateUserDtoMapper.fromCreateUserDto;
import static com.ceniuch.bluediamondbackend.users.mappers.GetUserDtoIudMapper.toGetUserDtoId;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    GetUserDto createUser(CreateUserDto createUserDto) {
        if (userRepository.existsUserByUID(createUserDto.UID())) {
            throw new UserAlreadyExistsException("User mit UID existiert bereits");
        }

        User newUser = fromCreateUserDto(createUserDto);
        User savedUser = userRepository.save(newUser);
        return toGetUserDtoId(savedUser);
    }

    GetUserDto getUser(String uid) {
        User targetUser = userRepository.findUserByUID(uid).orElseThrow(
                () -> new UserNotFoundException("User with UID " + uid + " not found.")
        );
        return toGetUserDtoId(targetUser);
    }
}
