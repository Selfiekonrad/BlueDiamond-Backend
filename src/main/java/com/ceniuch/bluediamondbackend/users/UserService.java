package com.ceniuch.bluediamondbackend.users;

import com.ceniuch.bluediamondbackend.users.dtos.CreateUserDto;
import com.ceniuch.bluediamondbackend.users.dtos.GetUserDtoId;
import org.springframework.stereotype.Service;
import static com.ceniuch.bluediamondbackend.users.mappers.CreateUserDtoMapper.fromCreateUserDto;
import static com.ceniuch.bluediamondbackend.users.mappers.GetUserDtoIdMapper.toGetUserDtoId;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    GetUserDtoId createUser(CreateUserDto createUserDto) {
        User newUser = fromCreateUserDto(createUserDto);
        User savedUser = userRepository.save(newUser);
        return toGetUserDtoId(savedUser);
    }
}
