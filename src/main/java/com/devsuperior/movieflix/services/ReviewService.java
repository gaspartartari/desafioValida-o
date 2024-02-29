package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MapperService mapperService;

    @Autowired
    private AuthService authService;

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public ReviewDTO insert(ReviewDTO dto){
      
        if(!movieRepository.existsById(dto.getMovieId()))
            throw new ResourceNotFoundException("Movie id " + dto.getMovieId() + " does not exist");
        Review review = new Review(null, dto.getText(), movieRepository.findById(dto.getMovieId()).get(), authService.authenticated());
        review = reviewRepository.save(review);
        return mapperService.reviewToDto(review);
    }

}
