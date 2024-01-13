package com.TuornamentOrganizer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TuornamentOrganizer.Model.IndividualGame;

@Repository
public interface IndividualGameRepo extends JpaRepository<IndividualGame, Long>{

}
