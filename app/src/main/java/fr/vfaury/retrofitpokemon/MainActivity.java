package fr.vfaury.retrofitpokemon;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Controller controller;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        controller = new Controller(this);
        controller.onCreate();

    }

    public void showList(List<Pokemon> list){
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