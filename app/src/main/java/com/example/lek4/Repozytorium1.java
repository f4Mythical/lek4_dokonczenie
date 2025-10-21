package com.example.lek4;

import java.util.ArrayList;

public class Repozytorium1 {
    public static ArrayList<Pytanie>ZwrocWszystkiePytania(){
        ArrayList<Pytanie> pytanie = new ArrayList<>();
        pytanie.add(new Pytanie(R.drawable.kot1, "czy jest to slodkie?", true, "tak jest to slodkie"));
        pytanie.add(new Pytanie(R.drawable.kotek2, "czy jest to mega slodkie?", true, "no oczywiscie"));
        pytanie.add(new Pytanie(R.drawable.kot3, "czy jest to rudy?", true, "jeszcze sie pytasz?"));
        return pytanie;
    }
}
