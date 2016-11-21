package com.proyecto.aplicada.conectados;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class SqlConexion extends SQLiteAssetHelper {

    private static String baseDatos="BaseDatosAgenda.db";
    private SQLiteDatabase bd;

    public SqlConexion(Context context) {
        super(context, baseDatos, null, 1);
        bd=getReadableDatabase();
    }

    //private String nombreTabla;

    public boolean mEjecutar(String query)
    {
        try {
            bd.beginTransaction();
            bd.execSQL(query);
            bd.endTransaction();
            bd.close();
            return  true;
        }catch (SQLiteAssetException e)
        {
            bd.close();
            return false;
        }
    }//SE PUEDE BORRAR ESTE METODO

    public boolean mInsertarUsuario(ContentValues valores,String nombreTabla)
    {
        try {
            bd.insert(nombreTabla,null,valores);
            return  true;
        }catch (SQLiteAssetException e)
        {
            bd.close();
            return false;
        }
    }

    public Usuario consultarUsuario(String clave, String usuario) {
        String query = "Select * from usuario where usuario = ? and password = ?";
        String[] args = {usuario, clave};
        Cursor cursor = this.mConsultarUser(query, args);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Usuario usuarioEncontrado = new Usuario(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),cursor.getInt(6));
            cursor.close();
            return usuarioEncontrado;
        } else {
            cursor.close();
            return null;
        }
    }

    public Cursor mConsultarUser(String query, String arg[])
    {
        Cursor cursor=bd.rawQuery(query,arg);
        return cursor;
    }



}
