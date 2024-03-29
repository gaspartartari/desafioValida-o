package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Review;

public interface ReviewRepository extends JpaRepository <Review, Long> {

    @Query(value = """
    SELECT obj FROM Review obj JOIN FETCH obj.user JOIN FETCH obj.movie WHERE obj.movie.id = :id
    """)
    List<Review> findReviewsByMovieId(Long id);
    
}
