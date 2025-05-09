package com.api.musiconnect.service;

import com.api.musiconnect.dto.request.CreateBandRequest;
import com.api.musiconnect.dto.request.InviteToBandRequest;
import com.api.musiconnect.dto.response.BandResponse;
import com.api.musiconnect.dto.response.InviteToBandResponse;
import com.api.musiconnect.exception.BadRequestException;
import com.api.musiconnect.exception.DuplicateResourceException;
import com.api.musiconnect.exception.ResourceNotFoundException;
import com.api.musiconnect.mapper.BandMapper;
import com.api.musiconnect.model.entity.Band;
import com.api.musiconnect.model.entity.User;
import com.api.musiconnect.model.enums.BandStatus;
import com.api.musiconnect.repository.BandRepository;
import com.api.musiconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BandService {
    private final BandRepository bandRepository;
    private final BandMapper bandMapper;
    private final UserRepository userRepository;

    @Transactional
    public BandResponse registerBand(CreateBandRequest request) {

        if (bandRepository.existsBandByNombre(request.nombre())) {
            String errorMessage = "Ese nombre de banda ya está registrado.";
            throw new DuplicateResourceException(errorMessage);
        }

        Band newBand = bandMapper.toBandEntity(request);

        newBand.setEstado(BandStatus.ACTIVE);
        newBand.setCreatedAt(LocalDateTime.now());
        Band savedBand=bandRepository.save(newBand);

        BandResponse response = bandMapper.BandtoBandResponse(savedBand);

        return response;
    }

    @Transactional
    public BandResponse getBandById(Integer bandId)
    {
        Band band = bandRepository.findBandByBandId(bandId)
                .orElseThrow(() -> new ResourceNotFoundException("La banda con id " + bandId + " no fue encontrada."));
        return bandMapper.BandtoBandResponse(band);
    }

    @Transactional
    public BandResponse updateBandById(Integer bandId, CreateBandRequest request){
        Band band = bandRepository.findBandByBandId(bandId)
                .orElseThrow(() -> new ResourceNotFoundException("La banda con ese id no existe."));

        Band update = bandMapper.toBandEntity(request);

        band.setNombre(update.getNombre());
        band.setDescripcion(update.getDescripcion());
        band.setUpdatedAt(LocalDateTime.now());

        bandRepository.save(band);

        return bandMapper.BandtoBandResponse(band);
    }

    @Transactional
    public String deleteBandById(Integer bandId) {
        Band band = bandRepository.findBandByBandId(bandId)
                .orElseThrow(() -> new ResourceNotFoundException("La banda con ese id no existe."));

        bandRepository.delete(band);
        return "La banda se elimino exitosamente";
    }

    @Transactional
    public InviteToBandResponse inviteToBand(InviteToBandRequest request) {
        Band band = bandRepository.findById(request.bandId())
                .orElseThrow(() -> new ResourceNotFoundException("La banda no existe"));

        User user = userRepository.findUserById(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));

        if (band.getEstado() == BandStatus.BLOCKED) {
            throw new BadRequestException("No se puede agregar a una banda bloqueada");
        }

        if(bandRepository.existsUserInBand(request.userId())){
            throw new DuplicateResourceException("Este usuario ya es miembro de la banda.");
        }

        band.setUser(user);
        band.setCreatedAt(LocalDateTime.now());
        band.setCreatedBy(request.createdBy());

        Band updated = bandRepository.save(band);
        return BandMapper.ToInviteResponse(updated);
    }
}
