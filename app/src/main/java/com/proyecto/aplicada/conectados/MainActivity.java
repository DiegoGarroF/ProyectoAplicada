package com.proyecto.aplicada.conectados;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String dato="";
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        //correo.getText()
       // correo.setText(""+getIntent().getExtras().getString("usuario"));
       dato=getIntent().getExtras().getString("usuario");

        toolbar.setTitle(getUsuario(dato));

        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView=(ListView)findViewById(R.id.listViewChtas);

        arrayList=new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.mensajes,arrayList);
        listView.setAdapter(adapter);

        for(int i=0; i<getMensajes(dato).length; i++)
        {
            if(getMensajes(dato)[i]!=null) {
                System.out.println("\nDATOS OBTENIDOS"+getMensajes(dato)[i]+"  Posicion "+i);
                arrayList.add(getMensajes(dato)[i]);
            }
        }
        adapter.notifyDataSetChanged();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        TextView correo=(TextView)findViewById(R.id.correo);
        TextView nombre=(TextView)findViewById(R.id.nombre);
        correo.setText(getCorreo(dato));
        nombre.setText(getUsuario(dato));
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
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Metodo para obtener el usurio
    public  String getUsuario(String palabra)
    {
        int posicion=palabra.indexOf('#');
        if(posicion>=0)
        {
            return palabra.substring(0,posicion);
        }
        return null;
    }
    // Metodo para obtener el usurio
    public  String getCorreo(String palabra)
    {
        int posicion=palabra.indexOf('#');
        int posicionCorreo=palabra.indexOf('*');
        if(posicion>=0)
        {
            return palabra.substring((posicion+1),posicionCorreo);
        }
        return null;
    }
    public String[] getMensajes(String palabra)
    {
        int posicion=palabra.indexOf('*');
        String letras=palabra.substring((posicion+1),palabra.length());
        String vector[]=letras.split("~");

        return vector;
    }
}
