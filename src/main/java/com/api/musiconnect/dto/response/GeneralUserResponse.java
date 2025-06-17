package com.api.musiconnect.dto.response;

import com.api.musiconnect.model.enums.Language;
import com.api.musiconnect.model.enums.UserStatus;

public record GeneralUserResponse(
        UserStatus status,
        Language language
) {
}
