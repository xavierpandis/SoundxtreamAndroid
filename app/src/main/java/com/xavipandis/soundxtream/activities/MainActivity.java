package com.xavipandis.soundxtream.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.xavipandis.soundxtream.R;
import com.xavipandis.soundxtream.managers.TokenStoreManager;
import com.xavipandis.soundxtream.managers.UserLoginManager;
import com.xavipandis.soundxtream.model.UserToken;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
    protected void onResume() {
        super.onResume();

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String accessToken =  preferences.getString("accessToken","");

        if(accessToken.equals("")){
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }else{

            TokenStoreManager.getInstance().setAccessToken(preferences.getString("accessToken",""));
            TokenStoreManager.getInstance().setRefreshToken(preferences.getString("refreshToken",""));
            TokenStoreManager.getInstance().setTokenType(preferences.getString("tokenType",""));
            TokenStoreManager.getInstance().setUsername(preferences.getString("username",""));
            TokenStoreManager.getInstance().setContext(MainActivity.this);

            String token = TokenStoreManager.getInstance().getAccessToken();
            String refresh = TokenStoreManager.getInstance().getRefreshToken();
            String username = TokenStoreManager.getInstance().getUsername();

            final MainFragment mainFragment =
                    MainFragment.newInstance(token,refresh,username);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, mainFragment, "mainFragment")
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            String token = TokenStoreManager.getInstance().getAccessToken();
            String refresh = TokenStoreManager.getInstance().getRefreshToken();
            String username = TokenStoreManager.getInstance().getUsername();

            final MainFragment mainFragment =
                    MainFragment.newInstance(token,refresh,username);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, mainFragment, "mainFragment")
                    .commit();
        } else if (id == R.id.nav_gallery) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content_main, TrackDetailFragment.newInstance(),"trackDetail")
                    .addToBackStack("mainFragment")
                    .commit();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }  else if (id == R.id.nav_logout) {
            logOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logOut(){
        SharedPreferences mySPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mySPrefs.edit();
        editor.remove("accessToken");
        editor.remove("refreshToken");
        editor.remove("tokenType");
        editor.remove("username");
        editor.apply();
        TokenStoreManager.getInstance().setAccessToken("");
        TokenStoreManager.getInstance().setRefreshToken("");
        TokenStoreManager.getInstance().setTokenType("");
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }
}
