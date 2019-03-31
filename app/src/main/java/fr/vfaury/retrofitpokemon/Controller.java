package fr.vfaury.retrofitpokemon;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Console;
import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {
    private MainActivity activity;
    public Controller (MainActivity activity){
        this.activity = activity;
    }

    public void onCreate(){
        start();
    }

    private static Retrofit retrofit;
    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/";

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder().baseUrl(POKEAPI_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public void start(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(POKEAPI_URL).addConverterFactory(GsonConverterFactory.create()).build();

        SharedPreferences pokemon_cache = PreferenceManager.getDefaultSharedPreferences(activity);
        final SharedPreferences.Editor editor = pokemon_cache.edit();

        GerritAPI pokemonRestApi = retrofit.create(GerritAPI.class);
        Call<RestPokemonResponse> call = pokemonRestApi.getListPokemon();
        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                RestPokemonResponse restPokemonResponse = response.body();
                List<Pokemon> listPokemon = restPokemonResponse.getResults();

                Gson gson = new Gson();
                String json = gson.toJson(listPokemon);
                editor.putString("pokemon_cache",json);
                editor.apply();

                activity.showList(listPokemon);
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                Toast.makeText(activity, "Internet Error", Toast.LENGTH_LONG).show();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
                Gson gson = new Gson();
                String json = prefs.getString("pokemon_cache", null);
                Type type = new TypeToken<List<RestPokemonResponse>>() {}.getType();
                List<Pokemon> input = gson.fromJson(json, type);
                activity.showList(input);
            }
        });
    }


}
