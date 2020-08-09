package com.avi.movie.dto;

import javax.persistence.Column;

/**
*<h1>This is the DTO Class for Movie.</h1>
*@author avinashsingh
*/   
public class MovieDTO {
	private String movieTitle; 

	private String movieCategory;  

	private float  movieStarRating;

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMovieCategory() {
		return movieCategory;
	}

	public void setMovieCategory(String movieCategory) {
		this.movieCategory = movieCategory;
	}

	public float getMovieStarRating() {
		return movieStarRating;
	}

	public void setMovieStarRating(float movieStarRating) {
		this.movieStarRating = movieStarRating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movieCategory == null) ? 0 : movieCategory.hashCode());
		result = prime * result + Float.floatToIntBits(movieStarRating);
		result = prime * result + ((movieTitle == null) ? 0 : movieTitle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieDTO other = (MovieDTO) obj;
		if (movieCategory == null) {
			if (other.movieCategory != null)
				return false;
		} else if (!movieCategory.equals(other.movieCategory))
			return false;
		if (Float.floatToIntBits(movieStarRating) != Float.floatToIntBits(other.movieStarRating))
			return false;
		if (movieTitle == null) {
			if (other.movieTitle != null)
				return false;
		} else if (!movieTitle.equals(other.movieTitle))
			return false;
		return true;
	}
	
	
	
}
