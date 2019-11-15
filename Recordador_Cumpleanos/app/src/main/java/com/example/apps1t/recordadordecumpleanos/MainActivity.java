package com.example.apps1t.recordadordecumpleanos;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView menu;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    BirthDayAPI birthDayAPI;
    RaspberryAPI raspberryAPI;
    ArrayList<Birthday> birthdaysAll;
    Retrofit retrofit;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referencias de los elementos
        menu = findViewById(R.id.menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        //Selecciona los elementos del menu
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeScreen(position);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.abrir_menu, R.string.cerrar_menu);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        changeScreen(0);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://tonterias.herokuapp.com/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        birthDayAPI = retrofit.create(BirthDayAPI.class);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        raspberryAPI = retrofit.create(RaspberryAPI.class);

        raspberryCall();
        getAllBirthdays();
    }

    void changeScreen(int screen) {
        Fragment fragment = null;
        FragmentManager manager = getSupportFragmentManager();

        switch (screen) {
            case 0:
                fragment = new BirthdayAdd();

                break;
            case 1:
                fragment = new AllBirthday();
                sendArrayList(birthdaysAll, (AllBirthday) fragment);
                break;
            case 2:
                fragment = new NearBirthday();
                closestBirthday(birthdaysAll);
                sendBirthday(birthdaysAll, (NearBirthday) fragment);

                break;
        }

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
        drawerLayout.closeDrawer(Gravity.START);
    }

    // Permite desplegar el menu lateral
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    // Permite cerrar el menu lateral
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*Metodo que envia un post al arraylist para crear una nueva entrega*/
    public void addNewBirthday(String name, String image, long date) {
        Call<String> call = birthDayAPI.postBirthday(name, image, date);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String state = response.body();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Metodo para recibir el archivo json con todos los cumpleñaos en forma de arraylist*/
    public void getAllBirthdays() {
        Call<ArrayList<Birthday>> call = birthDayAPI.getBirthdays();

        call.enqueue(new Callback<ArrayList<Birthday>>() {
            @Override
            public void onResponse(Call<ArrayList<Birthday>> call, Response<ArrayList<Birthday>> response) {
                ArrayList<Birthday> birthdays = response.body();
                getArrayList(birthdays);
            }

            @Override
            public void onFailure(Call<ArrayList<Birthday>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Metodo para almacenar el arraylist que llega del get en otro nuevo*/
    public void getArrayList(ArrayList<Birthday> birthdays){
        birthdaysAll = birthdays;
    }

    /*Metodo para enviar el arraylist con los cumpleaños al fragment*/
    public void sendArrayList(ArrayList<Birthday> birthdays, AllBirthday fragment){
        fragment.recieveArrayList(birthdaysAll);
    }

    /*Metodo para encontrar el cumpleaños más cercano*/
    public void closestBirthday(ArrayList<Birthday> birthdaysAll){
        Date date = new Date();
        long today = date.getTime();
        Log.d("*****", String.valueOf(today));
        for(int i = 0; i<birthdaysAll.size(); i++){
            if(birthdaysAll.get(i).date >= today){
                Collections.sort(birthdaysAll, new Comparator<Birthday>() {
                    @Override
                    public int compare(Birthday o1, Birthday o2) {

                        long date1 = o1.getDate();
                        //Calculamos la fecha en dias del año
                        Calendar c = Calendar.getInstance();
                        c.setTimeInMillis(date1);
                        int day1 = c.get(Calendar.DAY_OF_YEAR);

                        long date2 = o2.getDate();
                        //Calculamos la fecha en dias del año
                        Calendar ca = Calendar.getInstance();
                        ca.setTimeInMillis(date2);
                        int day2 = ca.get(Calendar.DAY_OF_YEAR);

                        if(day1 > day2){
                            return 1;
                        }
                        else if(day1 < day2){
                            return -1;
                        }
                        else return 0;
                    }
                });
            }
        }

    }

    /*Metodo que envia el arraylist al fragment*/
    public void sendBirthday(ArrayList<Birthday> birthdaysAll, NearBirthday fragment){
        fragment.recieveBirthday(birthdaysAll);
    }


    public void raspberryCall(){
        Call<String> call = raspberryAPI.raspberryCall();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(MainActivity.this, "Hoy hay un cumpleaños", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Hoy no hay ningún cumpleaños", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
