package com.TuornamentOrganizer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TuornamentOrganizer.Model.Player;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long>{

}
