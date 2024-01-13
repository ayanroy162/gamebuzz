package com.TuornamentOrganizer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.TuornamentOrganizer.Model.UserTable;

@Repository
public interface UserRepo extends JpaRepository<UserTable, Long>{
	
	@Query("select t FROM UserTable t WHERE t.name  = :name AND t.password = :password")
	UserTable getUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

}
