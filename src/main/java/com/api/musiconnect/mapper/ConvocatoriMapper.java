package com.api.musiconnect.mapper;

import com.api.musiconnect.dto.response.ConvocatoriDTO;
import com.api.musiconnect.model.entity.Convocatori;

public class ConvocatoriMapper {
    public static ConvocatoriDTO toDTO(Convocatori c, int favoritos) {
        ConvocatoriDTO dto = new ConvocatoriDTO();
        dto.setId(c.getId());
        dto.setTitulo(c.getTitulo());
        dto.setDescripcion(c.getDescripcion());
        dto.setFecha(c.getFecha());
        dto.setCreadorNombre(c.getCreador().getUsername());
        dto.setCantidadFavoritos(favoritos);
        return dto;
    }
}
