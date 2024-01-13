package com.TuornamentOrganizer.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.TuornamentOrganizer.Model.Round;

@Repository
public interface RoundRepo extends JpaRepository<Round, Long>{

	@Query("select t from Round t where t.roundnumber = :roundnumber")
	Round findByRoundNumber(@Param("roundnumber") int roundNumber);

}
