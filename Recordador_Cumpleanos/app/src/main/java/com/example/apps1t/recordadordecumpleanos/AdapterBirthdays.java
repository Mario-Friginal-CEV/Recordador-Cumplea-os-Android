package com.example.apps1t.recordadordecumpleanos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterBirthdays extends ArrayAdapter {

    Context context;
    int _item_layout;
    ArrayList<Birthday> birthdays;
    TextView birthdayName, birthdayDate;
    ImageView birthdayImage;

    public AdapterBirthdays(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);

        this.context = context;
        this._item_layout = resource;
        this.birthdays = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Crear vista de la fila
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(_item_layout, parent, false);
        }

        birthdayName = convertView.findViewById(R.id.birthdayName);
        try {
            birthdayName.setText(birthdays.get(position).name);
        }catch (Exception e) {}

        birthdayDate = convertView.findViewById(R.id.birthdayDate);
        long date = birthdays.get(position).date;
        DateFormat simple = new SimpleDateFormat("dd/MMM/yyyy");
        Date result = new Date(date);
        try{
        birthdayDate.setText(simple.format(result));
        }catch (Exception e) {}

        birthdayImage = convertView.findViewById(R.id.birthdayImage);
        try{
            Picasso.get().load(birthdays.get(position).image).into(birthdayImage);
        }catch (Exception e) {}

        return convertView;
    }
}
