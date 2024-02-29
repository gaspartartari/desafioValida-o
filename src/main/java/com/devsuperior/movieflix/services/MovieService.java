package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieProjection;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;


@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MapperService mapperService;

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id){
        Movie result = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return mapperService.movieToDto(result);
    }

    public Page<MovieCardDTO> findByGenre(@Valid String genreId, Pageable pageable) {
        Long id = Long.parseLong(genreId);
        if(id == 0)
            id = null;
        Page<MovieProjection> result = movieRepository.findByGenre(id, pageable);
        return result.map(x -> mapperService.movieProjectionToDto(x));

    }
}
