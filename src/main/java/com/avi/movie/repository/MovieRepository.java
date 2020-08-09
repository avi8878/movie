package com.avi.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.avi.movie.domain.Movie;

/**
*<h1>This is Repository for Movie Entity and will provide basic methods to manage database operations </h1>
*@author avinashsingh
*/   

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
	
	Movie findByMovieId(Integer id);
	
}

