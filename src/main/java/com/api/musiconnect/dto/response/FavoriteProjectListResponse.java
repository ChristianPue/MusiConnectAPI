package com.api.musiconnect.dto.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteProjectListResponse {

    private Long userId;
    private String username;
    private List<FavoriteProjectResponse> favoriteProjects;

}
