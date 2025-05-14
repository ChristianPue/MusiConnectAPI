package com.api.musiconnect.dto.response;

import com.api.musiconnect.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSearchResponse {
    private Long id;
    private String username;
    private String location;
    private UserType userType;
}
