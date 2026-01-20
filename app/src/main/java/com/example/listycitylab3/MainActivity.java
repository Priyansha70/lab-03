package com.example.listycitylab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AddCityFragment.OnFragmentInteractionListener {

    // 🔹 CHANGED: use City instead of String
    private ArrayList<com.example.listycitylab3.City> dataList;
    private ListView cityList;
    private ArrayAdapter<com.example.listycitylab3.City> cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 🔹 Create City objects (with city + province)
        dataList = new ArrayList<>();
        dataList.add(new com.example.listycitylab3.City("Edmonton", "AB"));
        dataList.add(new com.example.listycitylab3.City("Vancouver", "BC"));
        dataList.add(new com.example.listycitylab3.City("Moscow", "RU"));
        dataList.add(new com.example.listycitylab3.City("Sydney", "NSW"));
        dataList.add(new com.example.listycitylab3.City("Berlin", "DE"));

        cityList = findViewById(R.id.city_list);

        // 🔹 ArrayAdapter<City> — uses City.toString()
        cityAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dataList
        );
        cityList.setAdapter(cityAdapter);

        // 🔹 Tap a city to EDIT it (Fragment)
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            com.example.listycitylab3.City selectedCity = dataList.get(position);

            // Pass City + position to Fragment
            com.example.listycitylab3.AddCityFragment fragment =
                    com.example.listycitylab3.AddCityFragment.newInstance(selectedCity, position);

            fragment.show(getSupportFragmentManager(), "EDIT_CITY");
        });
    }

    // 🔹 CALLED when user presses "Save" in AddCityFragment
    @Override
    public void onOkPressed(com.example.listycitylab3.City updatedCity, int position) {

        if (position >= 0 && position < dataList.size()) {

            // 🔥 USE SETTERS (this satisfies the hint)
            com.example.listycitylab3.City originalCity = dataList.get(position);
            originalCity.setCity(updatedCity.getCity());
            originalCity.setProvince(updatedCity.getProvince());

            cityAdapter.notifyDataSetChanged();
        }
    }
}
