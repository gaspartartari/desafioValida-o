package com.devsuperior.movieflix.projections;

public interface MovieProjection {
    
    Long getId();
    String getTitle();
    String getSubtitle();
    Integer getMovieYear();
    String getImgUrl();
}
