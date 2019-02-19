package fr.vfaury.retrofitpokemon;

import android.support.v7.app.AppCompatActivity;

import fr.vfaury.retrofitpokemon.Controller;


public class MainActivity extends AppCompatActivity {
        public void main(String[] args) {
            Controller controller = new Controller();
            controller.start();
        }
}