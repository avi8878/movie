package com.avi.movie.service;

import com.avi.movie.domain.Movie;
import com.avi.movie.dto.MovieDTO;

public interface MovieService {

public Movie getMovieById(Integer id) throws Exception;

public Integer createMovie(MovieDTO movieDTO) throws Exception;

public boolean deleteMovieById(Integer id) throws Exception;

public Movie updateMovie(MovieDTO movieDTO,Integer movieId) throws Exception;
}
