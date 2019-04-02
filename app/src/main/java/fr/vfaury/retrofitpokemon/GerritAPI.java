package fr.vfaury.retrofitpokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GerritAPI {
    @GET("pokemon")
    Call<RestPokemonResponse> getListPokemon();
}
