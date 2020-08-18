package com.avi.movie.service;

import com.avi.movie.domain.Movie;

public interface MovieService {

public Movie getMovieById(Integer id) throws Exception;

public Integer createMovie(Movie movie) throws Exception;

public boolean deleteMovieById(Integer id) throws Exception;

public Movie updateMovie(Movie movie,Integer movieId) throws Exception;
}
