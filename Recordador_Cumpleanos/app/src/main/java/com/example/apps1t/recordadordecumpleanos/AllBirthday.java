package com.example.apps1t.recordadordecumpleanos;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class AllBirthday extends Fragment {

    ListView ListAllBirthdays;
    AdapterBirthdays adapterBirthdays;
    ArrayList<Birthday> birthdaysAll;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_all_birthday, container, false);

        ListAllBirthdays = view.findViewById(R.id.ListAllBirthdays);

        adapterBirthdays = new AdapterBirthdays(this.getContext(), R.layout.birthdaylist, birthdaysAll);

        ListAllBirthdays.setAdapter(adapterBirthdays);

        return view;
    }

    /*Metodo que recibe el listado del arraylist*/
    public void recieveArrayList(ArrayList<Birthday> birthdays) {
        birthdaysAll = birthdays;
    }
}
