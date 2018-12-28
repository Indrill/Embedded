package com.example.thomas.gymclubapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.thomas.gymclubapp.DataBase.ManageDB;
import com.example.thomas.gymclubapp.Fragments.CalendarFragment;
import com.example.thomas.gymclubapp.Fragments.CoursesFragment;
import com.example.thomas.gymclubapp.Fragments.LoginFragment;
import com.example.thomas.gymclubapp.Fragments.NewsFragment;
import com.example.thomas.gymclubapp.Fragments.RegisterFragment;
import com.example.thomas.gymclubapp.Fragments.TrainersFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ManageDB db = new ManageDB(this);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new NewsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_news);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_calendar:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                        new CalendarFragment()).commit();
                break;
            case R.id.nav_news:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                        new NewsFragment()).commit();
                break;
            case R.id.nav_trainers:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                        new TrainersFragment()).commit();
                break;
            case R.id.nav_courses:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                        new CoursesFragment()).commit();
                break;
            case R.id.nav_login:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                        new LoginFragment()).commit();
                break;
            case R.id.nav_register:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                        new RegisterFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
