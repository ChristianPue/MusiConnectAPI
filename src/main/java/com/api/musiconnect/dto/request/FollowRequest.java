package com.api.musiconnect.dto.request;

import jakarta.validation.constraints.NotNull;

public record FollowRequest(
        @NotNull(message = "El usuario que sigue no puede ser nulo.")
        Long followerId,

        @NotNull(message = "El usuario seguido no puede ser nulo.")
        Long followedId
) {}