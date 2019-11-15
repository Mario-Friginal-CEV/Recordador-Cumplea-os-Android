package com.example.apps1t.recordadordecumpleanos;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NearBirthday extends Fragment {

    TextView birthdayName, birthdayDate;
    ImageView birthdayImage;
    ArrayList<Birthday> birthdaysAll;
    int posicion = (int)Math.floor(Math.random()*(150-0+1)+0);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_near_birthday, container, false);


        birthdayName = view.findViewById(R.id.nameNear);
        try {
            birthdayName.setText(birthdaysAll.get(posicion).name);
        }catch (Exception e) {}

        birthdayDate = view.findViewById(R.id.dateNear);
        long date = birthdaysAll.get(posicion).date;
        DateFormat simple = new SimpleDateFormat("dd/MMM/yyyy");
        Date result = new Date(date);
        try{
            birthdayDate.setText(simple.format(result));
        }catch (Exception e) {}

        birthdayImage = view.findViewById(R.id.imageNear);
        try{
            Picasso.get().load( birthdaysAll.get(posicion).image).into(birthdayImage);
        }catch (Exception e) {}

        return view;
    }

    /*Metodo que recibe el arraylist*/
    public void recieveBirthday(ArrayList<Birthday> birthdays) {
        birthdaysAll = birthdays;
    }
}
