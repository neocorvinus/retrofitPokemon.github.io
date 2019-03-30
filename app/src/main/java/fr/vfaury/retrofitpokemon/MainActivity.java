package fr.vfaury.retrofitpokemon;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        GerritAPI pokemonRestApi = retrofit.create(GerritAPI.class);
        Call<RestPokemonResponse> call = pokemonRestApi.getListPokemon();


        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                RestPokemonResponse restPokemonResponse = response.body();
                List<Pokemon> listPokemon = restPokemonResponse.getResults();
                showList(listPokemon);
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                Log.d("Erreur", "API KO");
            }
        });
    }

    private void showList(List<Pokemon> list){
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mAdapter = new MyAdapter(list, new OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon pokemon) {
                Intent details = new Intent(MainActivity.this, Activity_Details.class);
                Gson gson = new Gson();
                details.putExtra("pokemon_key", gson.toJson(pokemon));
                startActivity(details, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            }
        });
        recyclerView.setAdapter(mAdapter);
    }
}