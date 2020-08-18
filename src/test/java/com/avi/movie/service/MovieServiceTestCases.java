package com.avi.movie.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.avi.movie.domain.Movie;
import com.avi.movie.repository.MovieRepository;
import com.avi.movie.service.impl.MovieServiceImpl;

public class MovieServiceTestCases {
 
	  @InjectMocks
	  MovieServiceImpl movieService;
	     
	  @Mock
	  MovieRepository movieRepository;
	 
	   @Before
	   public void init() {
	        MockitoAnnotations.initMocks(this);
	    }
 
    @Test
    public void callGetMovieByIdSuccess()
      throws Exception {
        // create mock data
        Movie movie = new Movie();
        movie.setMovieTitle("name_dummy");
        movie.setMovieId(1);
        movie.setMovieCategory("genre_dummy");
        movie.setMovieStarRating(0.0f);
     
        when(movieRepository.findByMovieId(1)).thenReturn(movie);
        Movie expected =movieService.getMovieById(1);
        
        assertEquals("name_dummy", expected.getMovieTitle());
        assertEquals(1, expected.getMovieId());
        assertEquals("genre_dummy", expected.getMovieCategory());
        assertEquals(0.0f, expected.getMovieStarRating(),0.01);
       
       
    }
    
    @Test
    public void callGetMovieButIdNotExist()
      throws Exception {
        // create mock data
        Movie movie = new Movie();
        when(movieRepository.findByMovieId(1)).thenReturn(null);
        Movie expected =movieService.getMovieById(1);
        assertEquals(null, expected);
    }
    
    @Test
    public void callCreateMovieSuccess()
      throws Exception {
        // create mock data
        Movie movie = new Movie();
        movie.setMovieTitle("name_dummy");
        movie.setMovieId(1);
        movie.setMovieCategory("genre_dummy");
        movie.setMovieStarRating(0.0f);
        
        when(movieRepository.save(movie)).thenReturn(movie);
        Integer expected =movieService.createMovie(movie);
      
        assertEquals(Integer.valueOf( movie.getMovieId()), expected);
    }
    
    @Test
    public void callDeleteMovieSuccess()
      throws Exception {
        // create mock data
        Movie movie = new Movie();
        when(movieRepository.findByMovieId(1)).thenReturn(movie);
        doNothing().when(movieRepository).deleteById(1);
        Boolean expected =movieService.deleteMovieById(1);
        assertEquals(true, expected);
    }
    
    @Test
    public void callDeleteMovieButIdNotExist()
      throws Exception {
        // create mock data
        when(movieRepository.findByMovieId(1)).thenReturn(null);
        doNothing().when(movieRepository).deleteById(1);
        Boolean expected =movieService.deleteMovieById(1);
        assertEquals(false, expected);
    }
    
    
    @Test
    public void callUpdateMovieSuccess()
      throws Exception {
        // create mock data
        Movie checkMovieInDB = new Movie();
        
        Movie movie = new Movie();
        movie.setMovieTitle("name_dummy_updated");
        movie.setMovieId(1);
        movie.setMovieCategory("genre_dummy_updated");
        movie.setMovieStarRating(0.0f);
        
        when(movieRepository.findByMovieId(1)).thenReturn(checkMovieInDB);
        when(movieRepository.save(movie)).thenReturn(movie);
        Movie expected =movieService.updateMovie(movie,1);
      
        assertEquals(movie.getMovieTitle(), expected.getMovieTitle());
        assertEquals(movie.getMovieCategory(), expected.getMovieCategory());
        assertEquals(movie.getMovieStarRating(), expected.getMovieStarRating(),0.01);
    }
    
    @Test
    public void callUpdateMovieButIdNotExist()
      throws Exception {
        // create mock data
        Movie movie = new Movie();
        movie.setMovieTitle("name_dummy_updated");
        movie.setMovieId(1);
        movie.setMovieCategory("genre_dummy_updated");
        movie.setMovieStarRating(0.0f);
     
        when(movieRepository.findByMovieId(1)).thenReturn(null);
		
        Movie expected =movieService.updateMovie(movie,1);
      
        assertEquals(null, expected);
       
    }
    
}