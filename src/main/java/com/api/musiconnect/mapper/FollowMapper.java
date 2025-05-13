package com.api.musiconnect.mapper;

import com.api.musiconnect.dto.response.FollowResponse;
import com.api.musiconnect.model.entity.Follow;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FollowMapper {

    // Convertir Follow a FollowResponse
    public FollowResponse FollowToFollowResponse(Follow follow) {
        if (follow == null) {
            return null;
        }

        return new FollowResponse(
                follow.getId(),
                follow.getFollower().getId(),    // ID del usuario que sigue
                follow.getFollowed().getId(),   // ID del usuario seguido
                follow.getCreatedAt()            // Fecha de creación
        );
    }

    // Convertir List<Follow> a List<FollowResponse>
    public List<FollowResponse> FollowsToFollowsResponse(List<Follow> follows) {
        if (follows == null) {
            return null;
        }

        return follows.stream()
                .map(this::FollowToFollowResponse)
                .collect(Collectors.toList());
    }
}