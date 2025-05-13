package com.api.musiconnect.repository;

import com.api.musiconnect.model.entity.Follow;
import com.api.musiconnect.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    // Verifica si un usuario ya sigue a otro
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Follow f WHERE f.follower = :follower AND f.followed = :followed")
    boolean existsByFollowerAndFollowed(@Param("follower") User follower, @Param("followed") User followed);

    // Obtener a quién sigue un usuario (seguidos)
    @Query("SELECT f FROM Follow f WHERE f.follower = :follower")
    List<Follow> findByFollower(@Param("follower") User follower);

    // Obtener quiénes siguen a un usuario (seguidores)
    @Query("SELECT f FROM Follow f WHERE f.followed = :followed")
    List<Follow> findByFollowed(@Param("followed") User followed);

    // Eliminar un follow (dejar de seguir)
    void deleteByFollowerAndFollowed(User follower, User followed);

    // Contar a cuántos sigue un usuario
    long countByFollower(User follower);

    // Contar cuántos siguen a un usuario
    long countByFollowed(User followed);
}
