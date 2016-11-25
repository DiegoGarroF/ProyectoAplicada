package com.proyecto.aplicada.conectados;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class EnviarCorreo extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private  EditText editCorrePara;
    private EditText editCorreoDe;
    private EditText editMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_correo);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        final Button btnSe = (Button) findViewById(R.id.btEnviarCorr);
        editCorrePara = (EditText) findViewById(R.id.txtCorreoPara);
        editCorreoDe = (EditText) findViewById(R.id.txtCorreoDe);
        editMensaje = (EditText) findViewById(R.id.txtMensaje);
        editCorreoDe.setText(MainActivity.CORREO_USUARIO);
        editCorreoDe.setEnabled(false);

        btnSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] correoPara = {editCorrePara.getText().toString().trim()};
                String[] correoDe = {editCorreoDe.getText().toString().trim()};
                String mensaje = editMensaje.getText().toString().trim();

                //Toast.makeText(getApplicationContext(), correoDe + "\n"+correoPara+"\n"+mensaje, Toast.LENGTH_LONG).show();

                if (correoDe==null) {
                    Toast.makeText(getApplicationContext(), "Debe digitar un correo Remitente", Toast.LENGTH_LONG).show();
                }
                else if (correoPara==null) {
                    Toast.makeText(getApplicationContext(), "Debe digitar un correo Destinatario", Toast.LENGTH_LONG).show();
                } else if (mensaje.equals("")) {
                    Toast.makeText(getApplicationContext(), "Debe ingresar un mensaje para el correo", Toast.LENGTH_LONG).show();
                } else {
                    if (!correoDe[0].toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                        Toast.makeText(getApplicationContext(), "Debe digitar un correo Remitente válido", Toast.LENGTH_LONG).show();
                    }
                    if (!correoPara[0].toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                        Toast.makeText(getApplicationContext(), "Debe digitar un correo Destinatario válido", Toast.LENGTH_LONG).show();
                    } else {
                        enviar(correoPara, correoDe, "Importante", mensaje);
                    }


                }

            }
        });
    }
    private void enviar(String[] to, String[] cc, String asunto, String mensaje) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
        emailIntent.setType("message/rfc822");
        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email."));
            Log.i("EMAIL", "Correo...");
            editCorrePara.setText("");
            editMensaje.setText("");
            this.finish();
        }
        catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(this, "NO existe ningún cliente de email instalado!.", Toast.LENGTH_SHORT).show();
        }
    }
}
