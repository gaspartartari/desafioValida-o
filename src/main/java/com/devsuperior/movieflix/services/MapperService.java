package com.devsuperior.movieflix.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;


import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.projections.MovieProjection;

@Service
public class MapperService {
    
    private final ModelMapper modelMapper;

    public MapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        configureMovieMappings();
    }

    private void configureMovieMappings() {
        TypeMap <MovieProjection, MovieCardDTO> movieTypeMap = modelMapper.createTypeMap(MovieProjection.class, MovieCardDTO.class);
        movieTypeMap.addMapping(x -> x.getMovieYear(), MovieCardDTO :: setYear);
    }

    public GenreDTO genreToDto(Genre entity){
        return modelMapper.map(entity, GenreDTO.class);
    }

    public MovieDetailsDTO movieToDto(Movie entity) {
        return modelMapper.map(entity, MovieDetailsDTO.class);
    }

    public MovieCardDTO movieProjectionToDto(MovieProjection entity) {
        return modelMapper.map(entity, MovieCardDTO.class);
    }

    public ReviewDTO reviewToDto(Review review) {
        return modelMapper.map(review, ReviewDTO.class);
    }

}
