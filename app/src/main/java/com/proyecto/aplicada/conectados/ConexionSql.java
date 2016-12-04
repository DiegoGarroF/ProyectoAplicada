package com.proyecto.aplicada.conectados;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Garro on 20/11/2016.
 */

public class  ConexionSql extends AsyncTask<String,Void,String>
{
    Context context;
    AlertDialog alertDialog;
    public String resultadoConsulta="";
    Constantes constantes;
    String tipo="";
    String estadoLogueo=null;
    ConexionSql (Context ctx)
    {
        this.context=ctx;
    }
    private String UsuarioLogueado="";
    @Override
    protected String doInBackground(String... params){
        constantes= new Constantes();

        String type=params[0];
        String login_url=constantes.IpServidor;
        if(type.equals("Login"))
        {
            tipo="Login";
            return validarAcciones(params,login_url);
        }
        else if(type.equals("Registrar"))
        {
            login_url=constantes.InsertarUsuario;
            tipo="Registrar";
            return validarAcciones(params,login_url);

        }else if(type.equals("insertarMsj"))
        {
            login_url=constantes.InsertarMsj;
            tipo="insertarMsj";
            return validarAcciones(params,login_url);
        }
        else if(type.equals("logueado"))
        {
            tipo="logueado";
            return validarAcciones(params,login_url);
        }
        return  null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {

        if(tipo.equalsIgnoreCase("Login"))
        {
            alertDialog= new AlertDialog.Builder(context).create();
            if(verificarLogin(result)==null)
            {


                Constantes constantes= new Constantes();
                alertDialog.setTitle(constantes.mensajeDeError);
                alertDialog.show();
            }
            if(!verificarLogin(result)){
                alertDialog.setMessage(result);
                alertDialog.show();
            }

            else {
                Intent intent= new Intent(context,MainActivity.class);
                intent.putExtra("usuario",result);

                context.startActivity(intent);
                Login.datos=result;
            }
        }
        else if(tipo.equalsIgnoreCase("Registrar"))
        {
            Intent intent= new Intent(context,Login.class);
            intent.putExtra("usuario",result);

            context.startActivity(intent);
        }
        else if(tipo.equalsIgnoreCase("insertarMsj"))
        {
            Intent intent= new Intent(context,MainActivity.class);
            intent.putExtra("usuario",result);
            context.startActivity(intent);
        }
        else if(tipo.equalsIgnoreCase("logueado"))
        {
            Login.datos=result;
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public Boolean verificarLogin(String datos)
    {
        if(!datos.equalsIgnoreCase("Usuario Incorrecto")) {
            resultadoConsulta="Existe";
            return true;
        }
        return false;
    }

    public String validarAcciones(String params[],String login_url)
    {

        if(tipo.equalsIgnoreCase("Login")||tipo.equalsIgnoreCase("logueado")) {
            try {

                String user_name = params[1];
                String password = params[2];
                UsuarioLogueado = user_name = params[1];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);

                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (Exception e) {

            }

        }// Fin del Login
        else if(tipo.equalsIgnoreCase("Registrar"))
        {
            try {
                String usuario = params[1];
                String password = params[2];
                String nombre=params[3];
                String carnet=params[4];
                String correo=params[5];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data =
                        URLEncoder.encode("usuario", "UTF-8") + "=" + URLEncoder.encode(usuario, "UTF-8") +
                                "&" + URLEncoder.encode("password", "UTF-8")+ "=" + URLEncoder.encode(password, "UTF-8")+
                                "&" + URLEncoder.encode("nombre", "UTF-8")  + "=" + URLEncoder.encode(nombre, "UTF-8")+
                                "&" + URLEncoder.encode("carnet", "UTF-8")  + "=" + URLEncoder.encode(carnet, "UTF-8")+
                                "&" + URLEncoder.encode("correo", "UTF-8")  + "=" + URLEncoder.encode(correo, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (Exception e) {

            }
        }else if(tipo.equalsIgnoreCase("insertarMsj")){
            try {
                String remitente = params[1];
                String destinatario = params[2];
                String mensaje=params[3];
                String estado=params[4];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data =
                        URLEncoder.encode("remitente", "UTF-8") + "=" + URLEncoder.encode(remitente, "UTF-8") +
                                "&" + URLEncoder.encode("destinatario", "UTF-8")+ "=" + URLEncoder.encode(destinatario, "UTF-8")+
                                "&" + URLEncoder.encode("mensaje", "UTF-8")  + "=" + URLEncoder.encode(mensaje, "UTF-8")+
                                "&" + URLEncoder.encode("estado", "UTF-8")  + "=" + URLEncoder.encode(estado, "UTF-8");
                bufferedWriter.write(post_data);


                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (Exception e) {

            }
        }

        return null;
    }


}
