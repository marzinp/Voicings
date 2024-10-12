package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static DBHelper dbHelper;
    private static SQLiteDatabase db;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this.getApplicationContext();
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        DatabaseManager.initializeInstance(dbHelper);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_choix, R.id.nav_edit, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        insertSampleData();
    }

    private void insertSampleData() {

        TypeRepo typeRepo = new TypeRepo();
        MelodyRepo melodyRepo = new MelodyRepo();
        StyleRepo styleRepo = new StyleRepo();
        VoicingRepo voicingRepo = new VoicingRepo();
        typeRepo.delete();
        melodyRepo.delete();
        styleRepo.delete();
        voicingRepo.delete();
        //Insert Sample data if the table is empty

        Type type = new Type();
        int i = 0;
        List<String> list = new ArrayList<>(List.of("Maj7", "Min7", "Dom7", "Min7b5", "Dim7", "Alt"));
        while (i < list.size()) {
            type.setName(list.get(i));
            type.setTypeId(String.valueOf(i));
            i++;
            typeRepo.insert(type);
        }
        Style style = new Style();
        i = 0;
        list = new ArrayList<>(List.of("Closed", "Opened", "Spread", "Quartal", "SoWhat", "UpperStructure"));
        while (i < list.size()) {
            style.setName(list.get(i));
            style.setStyleId(String.valueOf(i));
            i++;
            styleRepo.insert(style);
        }
        Melody melody = new Melody();
        i = 0;
        list = new ArrayList<>(List.of("1", "2", "b3", "3", "4", "b5", "5", "#5", "6", "7", "M7", "b9", "9", "11", "#11", "b13", "13"));
        while (i < list.size()) {
            melody.setName(list.get(i));
            melody.setMelodyId(String.valueOf(i));
            i++;
            melodyRepo.insert(melody);
        }

        Voicing voicing = new Voicing();
        i = 0;
        List<List<String>> listoflists = Arrays.asList(Arrays.asList("Maj7", "M7", "Closed", "1"),
                Arrays.asList("Min7", "11", "Spread", "2"),
                Arrays.asList("Maj7", "9", "Closed", "3"));
        while (i < listoflists.size()) {
            voicing.setType(listoflists.get(i).get(0));
            voicing.setMelody(listoflists.get(i).get(1));
            voicing.setStyle(listoflists.get(i).get(2));
            voicing.setVoicingId(String.valueOf(i));
            i++;
            voicingRepo.insert(voicing);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}