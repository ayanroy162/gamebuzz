package com.TuornamentOrganizer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TuornamentOrganizer.Model.PlayingPlayer;

@Repository
public interface PlayingPlayerRepo extends JpaRepository<PlayingPlayer, Long>{

}
