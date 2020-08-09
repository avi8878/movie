package com.avi.movie;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.avi.movie.controller.MovieControllerTestCases;
import com.avi.movie.service.MovieServiceTestCases;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   MovieControllerTestCases.class,
   MovieServiceTestCases.class
})

public class AppTests {   
}  
