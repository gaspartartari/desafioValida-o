package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
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

    @Transactional
    public List<ReviewDTO> findReviewsByMovieId(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie " + id + " does not exist"));
        List<Review> result = reviewRepository.findReviewsByMovieId(id);
        return result.stream().map(mapperService::reviewToDto).toList();
    }

}
