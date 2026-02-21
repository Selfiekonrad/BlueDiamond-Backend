package com.ceniuch.bluediamondbackend.users.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
        @NotBlank String UID,
        @NotBlank String username
) {
}
