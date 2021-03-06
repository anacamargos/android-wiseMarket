package com.example.anacamargos.wisemarket;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SettingsFragment.OnFragmentInteractionListener,
        ProductsFragment.OnFragmentInteractionListener, YourListsFragment.OnFragmentInteractionListener,
        ListDetailsFragment.OnFragmentInteractionListener, HistoricoFragment.OnFragmentInteractionListener{

    FragmentManager fm;
    private String barra;
    private Toolbar toolbar;




    @Override
    public void onFragmentInteraction(Uri uri){

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        barra = "Wise Market";
        toolbar.setTitle(barra);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        fm = getSupportFragmentManager();

        if (id == R.id.nav_lists) {

            // Mostrar lista de compras
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame_layout, new YourListsFragment());
            barra = "Listas de Compras";
            toolbar.setTitle(barra);
            ft.commit();

        } else if (id == R.id.nav_products) {

            // Mostrar lista de produtos
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame_layout, new ProductsFragment());
            barra = "Produtos";
            toolbar.setTitle(barra);
            ft.commit();

        } else if (id == R.id.nav_historic) {

            // Mostrar historico de compras
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame_layout, new HistoricoFragment());
            barra = "Histórico de Compras";
            toolbar.setTitle(barra);
            ft.commit();

        } else if (id == R.id.nav_settings) {

            // Mostrar tela configurações
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame_layout, new SettingsFragment());
            ft.commit();
            barra = "Configurações";
            toolbar.setTitle(barra);


        } else if (id == R.id.nav_logout) {
            // Fazer log out
            // TODO fazer logout
            startActivity(new Intent(this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    /*@Override
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
    } */
}
