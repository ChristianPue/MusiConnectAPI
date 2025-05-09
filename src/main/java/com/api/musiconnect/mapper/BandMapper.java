package com.api.musiconnect.mapper;


import com.api.musiconnect.dto.request.ConfigUpdateUserRequest;
import com.api.musiconnect.dto.request.CreateBandRequest;
import com.api.musiconnect.dto.request.RegisterUserRequest;
import com.api.musiconnect.dto.response.BandResponse;
import com.api.musiconnect.dto.response.InviteToBandResponse;
import com.api.musiconnect.dto.response.UserResponse;
import com.api.musiconnect.model.entity.Band;
import com.api.musiconnect.model.entity.User;
import com.api.musiconnect.model.enums.*;
import com.api.musiconnect.repository.BandRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class BandMapper {


    public Band toBandEntity(CreateBandRequest request) {
        if (request == null) {
            return null;
        }

        Band band = new Band();
        band.setNombre(request.nombre());
        band.setDescripcion(request.descripcion());
        band.setMusicalGenre(request.musicalGenre());
        band.setCreatedBy(request.createdBy());
        return band;
    }
    public BandResponse BandtoBandResponse(Band band)
    {
        if (band == null)
        {
            return null;
        }

        return new BandResponse(
                "Banda creada exitosamente",
                band.getBandId(),
                band.getUser() != null ? band.getUser().getId() : null,
                band.getNombre(),
                band.getDescripcion(),
                band.getEstado(),
                band.getMusicalGenre().size(),
                band.getCreatedAt(),
                band.getCreatedBy(),
                band.getUpdatedAt()
        );
    }

    public static InviteToBandResponse ToInviteResponse(Band band) {
        return new InviteToBandResponse(
                "Integrante añadido a la banda.",
                band.getBandId(),
                band.getUser() != null ? band.getUser().getId() : null,
                band.getCreatedAt(),
                band.getCreatedBy()
        );
    }

    public Band UpdateBandToBand(CreateBandRequest request)
    {
        if (request == null)
        {
            return null;
        }

        return Band.builder()
                .nombre(request.nombre())
                .descripcion(request.descripcion())
                .updatedAt(LocalDateTime.now())
                .build();
    }

}
