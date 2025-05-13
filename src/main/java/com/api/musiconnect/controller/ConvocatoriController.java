package com.api.musiconnect.controller;

import com.api.musiconnect.dto.response.ConvocatoriDTO;
import com.api.musiconnect.model.entity.Convocatori;
import com.api.musiconnect.service.ConvocatoriService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/convocatoris")
public class ConvocatoriController {

    private final ConvocatoriService convocatoriService;

    public ConvocatoriController(ConvocatoriService convocatoriService) {
        this.convocatoriService = convocatoriService;
    }

    @PostMapping
    public ResponseEntity<ConvocatoriDTO> crearConvocatori(@RequestBody Convocatori convocatori) {
        return ResponseEntity.ok(convocatoriService.crearConvocatori(convocatori));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConvocatoriDTO> editarConvocatori(@PathVariable Long id, @RequestBody Convocatori convocatori) {
        return convocatoriService.editarConvocatori(id, convocatori)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ConvocatoriDTO>> listarConvocatoris() {
        return ResponseEntity.ok(convocatoriService.listarConvocatoris());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConvocatoriDTO> obtenerConvocatori(@PathVariable Long id) {
        return convocatoriService.obtenerConvocatoriPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/favoritos")
    public ResponseEntity<Void> marcarFavorito(@RequestParam Long userId, @RequestParam Long convocatoriId) {
        convocatoriService.marcarFavorito(userId, convocatoriId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favoritos")
    public ResponseEntity<List<ConvocatoriDTO>> obtenerFavoritos(@RequestParam Long userId) {
        return ResponseEntity.ok(convocatoriService.obtenerFavoritos(userId));
    }

    @GetMapping("/favoritos/minimos")
    public ResponseEntity<List<ConvocatoriDTO>> listarConMinimoFavoritos(@RequestParam int minimo,
                                                                          @RequestParam(defaultValue = "desc") String orden) {
        return ResponseEntity.ok(convocatoriService.listarConMinimoFavoritos(minimo, orden));
    }
}