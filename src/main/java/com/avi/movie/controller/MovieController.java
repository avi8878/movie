package com.avi.movie.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.avi.movie.domain.Movie;
import com.avi.movie.dto.MovieDTO;
import com.avi.movie.exception.MovieNotFoundException;
import com.avi.movie.service.MovieService;

/**
*<h1>The aim of this Controller class is to handler HTTP request from clients.</h1>
*@author avinashsingh
*/
@RestController
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	MovieService movieService ;
	
	/**
	* The aim of this method is to retrieve Movie Resource for requested Id.
	* @param id this is the identifier of Movie Resource
	* @return ResponseEntity this is the ResponseEntity that holds Movie Resource
	* @exception Exception this exception will be manage globally in Controller Advice
	*/
	@GetMapping("/{id}")
	public ResponseEntity<Movie> getMovie(@PathVariable Integer id) throws Exception {
		Movie movie = movieService.getMovieById(id);
		
		if (movie==null)
			throw  new MovieNotFoundException("movie does not exist for given Id : "+id);
		
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}
	
	/**
	* The aim of this method is to create Movie Resource.
	* @param movieDTO this is the MovieDTO object to map input movie json data to DTO.
	* @return ResponseEntity this is the ResponseEntity that holds path of resource and created status code
	*  @exception Exception this exception will be manage globally in Controller Advice
	*/
	@PostMapping("/")
	public ResponseEntity<Object> createMovie(@RequestBody MovieDTO movieDTO) throws Exception {
		Integer movie = movieService.createMovie(movieDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				       .buildAndExpand(movie).toUri();
		return ResponseEntity.created(location).build();
	}
	
	/**
	* The aim  of this method is to delete Movie Resource for requested Id.
	* @param id this is the identifier of Movie Resource
	* @exception Exception this exception will be manage globally in Controller Advice
	* @return ResponseEntity this is the ResponseEntity that holds status of processed request if movie does not exist for given id then statu would be 404 otherwise 202
	*/
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteStudent(@PathVariable Integer id) throws Exception {
		if(movieService.deleteMovieById(id)) {
		return ResponseEntity.accepted().build();
	       	}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	* The aim of this method is to update Movie Resource.
	* @param movieDTO this is the MovieDTO object to map input movie json data to DTO.
	* @param id this is the identifier of Movie Resource
	* @return ResponseEntity this is the ResponseEntity that holds updated movie resource
	* @exception MovieNotFoundException  if movie identifier does not exist
	* @exception Exception this exception will be manage globally in Controller Advice
	*/
   @PutMapping("/{id}")
   public ResponseEntity<Movie> updateMovie(@RequestBody MovieDTO movieDTO,@PathVariable Integer id) throws Exception {
	Movie movie = movieService.updateMovie(movieDTO, id);
	
	if (movie==null)
		throw  new MovieNotFoundException("movie does not exist for given Id : "+id);
	     
	return new ResponseEntity<Movie>(movie, HttpStatus.OK);

  }
	
}
