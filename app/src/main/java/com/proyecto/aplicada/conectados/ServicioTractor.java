package com.proyecto.aplicada.conectados;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Garro on 04/12/2016.
 */

public class ServicioTractor extends AsyncTask<ListView, ListView, Boolean> {
    int contador=0;
    private Context context;

private  ListView listView;

    public ServicioTractor(Context context) {
        this.context = context;
    }

    public  void metodo(final ListView texto)
    {
        Thread hilo = new Thread(new Runnable() {
            public void run() {
                boolean estado=true;

                while (estado) {
                    //
                    try {
                        Thread.sleep(1000);
                        texto.post(new Runnable() {
                            @Override
                            public void run() {
                                cambiar(contador);
                            }
                        });
                        contador++;
                    } catch (InterruptedException e) {

                    }
                }
            }
        });
        hilo.start();
    }
    public void cambiar(int numero)
    {
        ProductListAdapter adapter;
        ArrayList<Product>  listaMensajes = new ArrayList<>();
        ConexionSql conexionSql = new ConexionSql(context);
        conexionSql.execute("logueado", Login.NOMBREUSUARIO, Login.CONTRASEÑA);

       String [] datosUsuarios = Login.datos.split("##");
        String detalles[]=null;
        if (datosUsuarios != null && datosUsuarios.length > 2) {
             detalles= datosUsuarios[2].split("///////////");
            for (int i = 0; i < (detalles.length - 2); i += 3) {
                listaMensajes.add(new Product(detalles[i + 1], detalles[i + 2].substring(detalles[i + 2].length() - 8, detalles[i + 2].length() - 3), detalles[i]));
            }
        }
        if(contador==1) {
            adapter = new ProductListAdapter(context, listaMensajes);
            listView.setAdapter(adapter);
        }
        else {
            if(listView.getAdapter()!=null)
            {
                if(detalles!=null&&  (listView.getAdapter().getCount()!=detalles.length/3))
                {
                    System.out.println("SE ACTUALIZARON LOS DAOS ");
                    adapter = new ProductListAdapter(context, listaMensajes);
                    listView.setAdapter(adapter);
                }
            }
        }
    }

    @Override
    protected Boolean doInBackground(ListView... listViews) {

        listView=listViews[0];
        metodo(listView);
        return null;
    }
}
