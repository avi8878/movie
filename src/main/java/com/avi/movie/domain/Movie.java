package com.avi.movie.domain;


import javax.persistence.Column;  
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;  

/**
*<h1>This is the Entity Class for Movie.</h1>
*@author avinashsingh
*/   
@Entity
public class Movie  
{  
//Defining Movie id as primary key  
@Id  
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "movie_id") 
private int movieId;  

@Column(name = "movie_title")
private String movieTitle; 

@Column(name = "movie_category")  
private String movieCategory;  

@Column(name = "movie_star_rating")
private float  movieStarRating;

public int getMovieId() {
	return movieId;
}

public void setMovieId(int movieId) {
	this.movieId = movieId;
}

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
	result = prime * result + movieId;
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
	Movie other = (Movie) obj;
	if (movieCategory == null) {
		if (other.movieCategory != null)
			return false;
	} else if (!movieCategory.equals(other.movieCategory))
		return false;
	if (movieId != other.movieId)
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