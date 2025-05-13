package com.api.musiconnect.service;

import com.api.musiconnect.model.entity.Convocatori;
import com.api.musiconnect.model.entity.FavoriteConvocatori;
import com.api.musiconnect.model.entity.User;
import com.api.musiconnect.dto.response.ConvocatoriDTO;
import com.api.musiconnect.mapper.ConvocatoriMapper;
import com.api.musiconnect.repository.ConvocatoriRepository;
import com.api.musiconnect.repository.FavoriteConvocatoriRepository;
import com.api.musiconnect.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConvocatoriService {

    private final ConvocatoriRepository convocatoriRepository;
    private final FavoriteConvocatoriRepository favoriteRepo;
    private final UserRepository userRepository;

    public ConvocatoriService(ConvocatoriRepository convocatoriRepository,
                               FavoriteConvocatoriRepository favoriteRepo,
                               UserRepository userRepository) {
        this.convocatoriRepository = convocatoriRepository;
        this.favoriteRepo = favoriteRepo;
        this.userRepository = userRepository;
    }

    public ConvocatoriDTO crearConvocatori(Convocatori convocatori) {
        return ConvocatoriMapper.toDTO(convocatoriRepository.save(convocatori), 0);
    }

    public Optional<ConvocatoriDTO> editarConvocatori(Long id, Convocatori updated) {
        return convocatoriRepository.findById(id).map(c -> {
            c.setTitulo(updated.getTitulo());
            c.setDescripcion(updated.getDescripcion());
            c.setFecha(updated.getFecha());
            return ConvocatoriMapper.toDTO(convocatoriRepository.save(c), 0);
        });
    }

    public List<ConvocatoriDTO> listarConvocatoris() {
        return convocatoriRepository.findAll().stream()
            .map(c -> ConvocatoriMapper.toDTO(c, 0))
            .collect(Collectors.toList());
    }

    public Optional<ConvocatoriDTO> obtenerConvocatoriPorId(Long id) {
        return convocatoriRepository.findById(id)
            .map(c -> ConvocatoriMapper.toDTO(c, 0));
    }

    public void marcarFavorito(Long userId, Long convocatoriId) {
        User user = userRepository.findById(userId).orElseThrow();
        Convocatori convocatori = convocatoriRepository.findById(convocatoriId).orElseThrow();
        FavoriteConvocatori favorito = new FavoriteConvocatori();
        favorito.setUser(user);
        favorito.setConvocatori(convocatori);
        favoriteRepo.save(favorito);
    }

    public List<ConvocatoriDTO> obtenerFavoritos(Long userId) {
        return favoriteRepo.findByUserId(userId).stream()
            .map(f -> ConvocatoriMapper.toDTO(f.getConvocatori(), 0))
            .collect(Collectors.toList());
    }

    public List<ConvocatoriDTO> listarConMinimoFavoritos(int minimo, String orden) {
        return convocatoriRepository.findAll().stream()
            .map(c -> {
                int favoritos = (int) favoriteRepo.findAll().stream()
                    .filter(f -> f.getConvocatori().getId().equals(c.getId()))
                    .count();
                return new Object[]{c, favoritos};
            })
            .filter(arr -> (int) arr[1] >= minimo)
            .sorted((a, b) -> {
                int f1 = (int) a[1];
                int f2 = (int) b[1];
                return "asc".equalsIgnoreCase(orden) ? Integer.compare(f1, f2) : Integer.compare(f2, f1);
            })
            .map(arr -> ConvocatoriMapper.toDTO((Convocatori) arr[0], (int) arr[1]))
            .collect(Collectors.toList());
    }
}