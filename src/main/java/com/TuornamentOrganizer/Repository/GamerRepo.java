package com.TuornamentOrganizer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TuornamentOrganizer.Model.Game;

@Repository
public interface GamerRepo extends JpaRepository<Game, Long>{

}
