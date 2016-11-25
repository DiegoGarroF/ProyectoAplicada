package com.proyecto.aplicada.conectados;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    String datosUsuarios[];
    private ListView listView;
    private ProductListAdapter adapter;
    private ArrayList<Product> listaMensajes;
    public static String CORREO_USUARIO;
    String dir = "";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Mensajes");
        datosUsuarios = getIntent().getExtras().getString("usuario").split("##");

        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView = (ListView) findViewById(R.id.listViewChtas);
        listaMensajes = new ArrayList<>();

        if (datosUsuarios != null && datosUsuarios.length > 2) {
            String detalles[] = datosUsuarios[2].split("///////////");
            for (int i = 0; i < (detalles.length - 2); i += 3) {


                listaMensajes.add(new Product(detalles[i + 1], detalles[i + 2].substring(detalles[i + 2].length() - 8, detalles[i + 2].length() - 3), detalles[i]));
            }


            adapter = new ProductListAdapter(getApplicationContext(), listaMensajes);
            listView.setAdapter(adapter);
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

        TextView correo = (TextView) findViewById(R.id.correo);
        TextView nombre = (TextView) findViewById(R.id.nombre);

        correo.setText(datosUsuarios[1]);
        nombre.setText(datosUsuarios[0]);
        CORREO_USUARIO = datosUsuarios[1];

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

            final CharSequence[] items = {"Gmail", "Outlook", "UCR"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Elija  Servidor de Correos");
            builder.setItems(items, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int item) {
                    if (items[item].toString().equals("Gmail")) {
                        dir = "https://accounts.google.com/ServiceLogin";
                    }
                    if (items[item].toString().equals("Outlook")) {
                        dir = "https://login.live.com/login.srf";
                    }
                    if (items[item].toString().equals("UCR")) {
                        dir = "https://correo.ucr.ac.cr/";
                    }
                    mostrarNavegadorWeb(dir);
                    dialog.cancel();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();


        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(this, ActivityMsjNuevo.class);
            intent.putExtra("usuario", Login.NOMBREUSUARIO);
            this.startActivity(intent);
        } else if (id == R.id.nav_sendCorreo) {
            Intent intent = new Intent(this, EnviarCorreo.class);
            intent.putExtra("usuario", Login.NOMBREUSUARIO);
            this.startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void mostrarNavegadorWeb(String dir) {
        Intent intento = new Intent(Intent.ACTION_VIEW);
        intento.setData(Uri.parse(dir));
        this.startActivity(intento);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
