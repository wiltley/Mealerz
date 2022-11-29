package com.example.mealerapplication.ui.client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.mealerapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ClientSearchView extends AppCompatActivity {
    ListView listview;
    SearchView search;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_search_view);

        spinner = findViewById(R.id.spinner);

        List<String> search = new ArrayList<>();
        search.add("Meal Type");
        search.add("Cuisine Type");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, search);
        spinner.setAdapter(adapter);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.bottom_navigation_menu,menu);
//
//        MenuItem menuItem = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Please type here");
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//
//    }
}