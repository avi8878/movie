package com.avi.movie.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avi.movie.domain.Movie;
import com.avi.movie.repository.MovieRepository;
import com.avi.movie.service.MovieService;

/**
*<h1>The aim of this class is to write business logics.</h1>
*@author avinashsingh
*/   

@Service
public class MovieServiceImpl implements MovieService {

@Autowired
MovieRepository movieRepository ;

/**
* The aim of this method is to retrieve Movie for given movie Id.
* @param id this is the identifier of Movie 
* @return Movie 
*/
@Override
public Movie getMovieById(Integer id) throws Exception {
	return movieRepository.findByMovieId(id);
}


/**
* The aim of this method is to create Movie for given MovieDTO.
* @param movieDTO this object holds the value of Movie fields. 
* @return Integer  created movie identifier 
*/
@Override
public Integer createMovie(Movie movie) throws Exception {

	return movieRepository.save(movie).getMovieId();
}

/**
* The aim of this method is to delete Movie for given movie id.
* @param id this is the identifier of Movie 
* @return boolean  whether given id exist or not 
*/
@Override
public boolean deleteMovieById(Integer id) throws Exception {
     if(!Optional.ofNullable(movieRepository.findByMovieId(id)).isPresent())
	 return false;
       
    movieRepository.deleteById(id);
    return true;
}

/**
* The aim of this method is to update Movie for given MovieDTO and movie id
* @param movieDTO this object holds the updated value of Movie fields 
*  @param movieId this is the identifier of Movie 
* @return Movie  Updated movie object 
*/
@Override
public Movie updateMovie(Movie movieUpdated,Integer movieId) throws Exception {
	 Movie movie = movieRepository.findByMovieId(movieId);
	 if (!Optional.ofNullable(movie).isPresent())
			return  null;
	 
	 movieUpdated.setMovieId(movieId);
	 return movieRepository.save(movieUpdated);
} 


}
