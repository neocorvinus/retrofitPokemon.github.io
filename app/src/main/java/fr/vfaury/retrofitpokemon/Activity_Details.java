package fr.vfaury.retrofitpokemon;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class Activity_Details extends Activity{

    public TextView name;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = (TextView) findViewById(R.id.name_txt);

        String jsonPokemon = getIntent().getStringExtra("pokemon_key");
        Gson gson = new Gson();
        Pokemon pokemon = gson.fromJson(jsonPokemon, Pokemon.class);

        name.setText(pokemon.getName());

    }

}
