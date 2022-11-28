package com.example.mealerapplication.data.rendering;

import com.example.mealerapplication.data.model.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Searcher {

    List<Recipe> list;
    public Searcher(List<Recipe> list){
        this.list = (ArrayList<Recipe>) list;
    }


    public List<Recipe> search(String searchBy, String queryType) {

        // TODO: 2022-11-28  Need to mark the words to avoid dupe searches
        HashMap<Recipe, Integer> mark = new HashMap<>();

        String[] keywords = searchBy.split(" ");
        List<Recipe> results = new ArrayList<>();
        for (Recipe r : list) {

            if (queryType.equals("Meal Name")) {

                for (String k : keywords) {
                    for (String s : r.getRecipeName().split(" ")) {

                        if (k.equals(s.toLowerCase())) {
                            if(!mark.containsKey(r)){

                                results.add(r);
                                mark.put(r, 1);

                            }
                        }
                    }
                }

            } else if (queryType.equals("Cuisine Type")) {

                for (String k : keywords) {
                    for (String s : r.getCuisineType().split(" ")) {

                        if (k.equals(s.toLowerCase(Locale.ROOT))) {
                            if(!mark.containsKey(r)){
                                results.add(r);
                                mark.put(r, 1);
                            }
                        }
                    }
                }
            }
        }
        return results;
    }

    public List<Recipe> getOriginal(){
        return list;
    }
}
