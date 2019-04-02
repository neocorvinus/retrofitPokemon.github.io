package fr.vfaury.retrofitpokemon;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.time.Year;

public class Activity_Details extends Activity{

    public TextView name;
    public TextView url;
    public TextView id;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = (TextView) findViewById(R.id.name_txt);
        url = (TextView) findViewById(R.id.url_txt);
        id = (TextView) findViewById(R.id.id_txt);

        String jsonPokemon = getIntent().getStringExtra("pokemon_key");
        Gson gson = new Gson();
        Pokemon pokemon = gson.fromJson(jsonPokemon, Pokemon.class);

        name.setText(pokemon.getName());
        id.setText(String.valueOf(pokemon.getId()));
        url.setText(String.valueOf(pokemon.getUrl()));

    }

}
