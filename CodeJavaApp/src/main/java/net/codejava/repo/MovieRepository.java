package net.codejava.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.codejava.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	    @Query(value = "select m from Movie m WHERE m.title LIKE %?1% or m.plot LIKE %?1% or m.director LIKE %?1% or m.genre LIKE %?1%")
		public List<Movie> findByKeyword(String keyword);
	}

