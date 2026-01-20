package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    private EditText cityEdit;
    private EditText provinceEdit;
    private com.example.listycitylab3.City city;

    public AddCityFragment() {
        // empty constructor REQUIRED
    }

    public static AddCityFragment newInstance(com.example.listycitylab3.City city) {
        AddCityFragment fragment = new AddCityFragment();
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.add_city_fragment, null);

        cityEdit = view.findViewById(R.id.edit_city);
        provinceEdit = view.findViewById(R.id.edit_province);

        if (getArguments() != null) {
            city = (com.example.listycitylab3.City) getArguments().getSerializable("city");
            cityEdit.setText(city.getCity());
            provinceEdit.setText(city.getProvince());
        }

        return new AlertDialog.Builder(getContext())
                .setTitle("Edit City")
                .setView(view)
                .setPositiveButton("Save", (d, w) -> {
                    city.setCity(cityEdit.getText().toString());
                    city.setProvince(provinceEdit.getText().toString());
                })
                .setNegativeButton("Cancel", null)
                .create();
    }
}

