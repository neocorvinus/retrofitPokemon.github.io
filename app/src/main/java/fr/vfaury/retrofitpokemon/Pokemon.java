package fr.vfaury.retrofitpokemon;

import java.util.ArrayList;

public class Pokemon<abilities> {
    private String name;
    private String url;
    private int id;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
