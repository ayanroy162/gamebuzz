package com.TuornamentOrganizer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.TuornamentOrganizer.Model.Tournament;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament, Long>{

	@Query("select t from Tournament t where t.tournamentName = :tournamentName and t.tournamentCreatedate = :tournamentCreatedate")
	Tournament getTournamentByTournamentName(@Param("tournamentName") String tournamentName, @Param("tournamentCreatedate") String tournamentCreatedate);

}
