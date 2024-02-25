package com.rittz.recyclerconclickyviewmodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PeliculaRepo {
    @GET("peliculas/")
    Call<List<Pelicula>> getPeliculas();
}

