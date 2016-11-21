package com.proyecto.aplicada.conectados;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ListView1 extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private ListView listView;
    private ArrayAdapter<String>adapter;
    private ArrayList<String>arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        editText=(EditText)findViewById(R.id.textoListView);
        button=(Button)findViewById(R.id.btnListView);
        listView=(ListView)findViewById(R.id.listViewe);

        arrayList=new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.mensajes,arrayList);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.add(editText.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });




    }
}
