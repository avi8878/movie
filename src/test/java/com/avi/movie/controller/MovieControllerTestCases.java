package com.avi.movie.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.avi.movie.domain.Movie;
import com.avi.movie.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTestCases {
 
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private MovieService movieService;
    
    @Autowired
    private ObjectMapper objectMapper;
 
    @Test
    public void callGetMovieSuccess()
      throws Exception {
        // create mock data
        Movie movie = new Movie();
        movie.setMovieTitle("nameTest");
        movie.setMovieId(1);
        movie.setMovieCategory("genreTest");
        movie.setMovieStarRating(0f);
     
        when(movieService.getMovieById(1)).thenReturn(movie);
        mvc.perform(MockMvcRequestBuilders.get("/movie/1")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.movieTitle", is(movie.getMovieTitle())))
          .andExpect(jsonPath("$.movieId", is(movie.getMovieId())))
          .andExpect(jsonPath("$.movieCategory", is(movie.getMovieCategory())));
          
    }
    
    
    @Test
    public void callGetMovieButNotFound()
      throws Exception {
        when(movieService.getMovieById(1)).thenReturn(null);
        mvc.perform(MockMvcRequestBuilders.get("/movie/1")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isNotFound());
    }
    
    @Test
    public void callGetMovieThrowException()
      throws Exception {
        when(movieService.getMovieById(1)).thenThrow(new RuntimeException("Exception occured while feaching movie inforamtion."));
        mvc.perform(MockMvcRequestBuilders.get("/movie/1")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().is5xxServerError());;
    }
    
    @Test
    public void callCreateMovieSuccess()
      throws Exception {
        // create mock data
        Movie movie = new Movie();
        movie.setMovieTitle("nameTest");
        movie.setMovieCategory("genreTest");
        movie.setMovieStarRating(0.0f);
     
        when(movieService.createMovie(movie)).thenReturn(1);
        mvc.perform(MockMvcRequestBuilders.post("/movie/")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(movie)))
          .andExpect(status().isCreated());
          
    }
    
    
    @Test
    public void callCreateMovieButMovieNotCreated()
      throws Exception {
        // create mock data
        Movie movie = new Movie();
        movie.setMovieTitle("nameTest");
        movie.setMovieCategory("genreTest");
        movie.setMovieStarRating(0.0f);
     
        when(movieService.createMovie(movie)).thenThrow(new RuntimeException("Exception occured while adding movie inforamtion."));
        mvc.perform(MockMvcRequestBuilders.post("/movie/")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(movie)))
          .andExpect(status().is5xxServerError());
    }
    
    @Test
    public void callDeleteMovieSuccessfully()
      throws Exception {
     
        when(movieService.deleteMovieById(1)).thenReturn(true);
        mvc.perform(MockMvcRequestBuilders.delete("/movie/1")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isAccepted());
          
    }
    
    @Test
    public void callDeleteMovieButMovieNotExist()
      throws Exception {
      
        when(movieService.deleteMovieById(1)).thenReturn(false);
        mvc.perform(MockMvcRequestBuilders.delete("/movie/1")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isNotFound());
          
    }
    
    
    @Test
    public void callDeleteMovieThrowException()
      throws Exception {
    	
    	  when(movieService.deleteMovieById(1)).
    	  thenThrow(new RuntimeException("Exception occured while deleting movie info."));
      
        mvc.perform(MockMvcRequestBuilders.delete("/movie/1")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().is5xxServerError());
    }
    
    @Test
    public void callMovieUpdateSuccessfully()
      throws Exception {
        // create mock data
        Movie movieDto = new Movie();
        movieDto.setMovieTitle("nameTest");
        movieDto.setMovieCategory("genreTest");
        movieDto.setMovieStarRating(0.0f);
        
     //updated Movie informations
        Movie movie = new Movie();
        movie.setMovieTitle("nameTestUpdated");
        movie.setMovieId(1);
        movie.setMovieCategory("genreTestUpdated");
        movie.setMovieStarRating(0.0f);
     
        when(movieService.updateMovie(movieDto,1)).thenReturn(movie);
        mvc.perform(MockMvcRequestBuilders.put("/movie/{id}",1)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(movieDto)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.movieTitle", is(movie.getMovieTitle())))
          .andExpect(jsonPath("$.movieId", is(movie.getMovieId())))
          .andExpect(jsonPath("$.movieCategory", is(movie.getMovieCategory())));
    }
    
    
    @Test
    public void callUpdateMovieThrowRuntimeException()
      throws Exception {
        // create mock data
        Movie movieDTO = new Movie();
        movieDTO.setMovieTitle("nameTest");
        movieDTO.setMovieCategory("genreTest");
        movieDTO.setMovieStarRating(0.0f);
     
        when(movieService.updateMovie(movieDTO,1)).thenThrow(new RuntimeException("Exception occured while updating movie inforamtion."));
        mvc.perform(MockMvcRequestBuilders.put("/movie/{id}",1)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(movieDTO)))
          .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void callUpdateMovieButMovieNotExist()
      throws Exception {
        // create mock data
        Movie movie = new Movie();
        movie.setMovieTitle("nameTest");
        movie.setMovieCategory("genreTest");
        movie.setMovieStarRating(0.0f);
        
        when(movieService.updateMovie(movie,1)).thenReturn(null);
        mvc.perform(MockMvcRequestBuilders.put("/movie/{id}",1)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(movie)))
          .andExpect(status().isNotFound());
    }
}