package com.api.musiconnect.repository;

import com.api.musiconnect.model.entity.Band;
import com.api.musiconnect.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BandRepository extends JpaRepository<Band, Integer> {
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Band b WHERE b.nombre = :nombre")
    boolean existsBandByNombre(@Param("nombre") String nombre);

    @Query("SELECT b FROM Band b WHERE b.bandId = :bandaId")
    Optional<Band> findBandByBandId(@Param("bandaId") Integer bandaId);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Band b WHERE b.user.id = :user")
    boolean existsUserInBand(@Param("user") Long user);
}
