package com.octo_spoon.octo_spoon_mobile.Book;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.octo_spoon.octo_spoon_mobile.R;

import java.util.HashMap;
import java.util.Map;

public class StageConnectActivity extends AppCompatActivity {

    private FloatingActionButton fabToChoose;
    private ListView resourcesList;
    private ListView methodologiesList;
    private  VideoListAdapter resourcesAdapter;
    private  VideoListAdapter methodologiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_connect);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Conectar");

        HashMap<String, String> videos = new HashMap<String, String>();
        videos.put("Aprendizaje basado " +
                "en proyectos","Cuidando y mejorando nuestro medio ambiente, experiencia realizada por la Escuela Básica G-33 de Talhuán, Ninhue.");
        videos.put("Pre-Textos","Trabajando con " +
                "Pre-Textos: articulando la Historia y el Arte, experiencia realizada por la Escuela Roberto Matta de Quillota.");
        videos.put("Google classroom","Trabajando con la plataforma Google para la educación ambiental, experiencia realizada por la Escuela Lidia González Barriga de Collipulli.");


        HashMap<String, String> methodologies = new HashMap<String, String>();
        methodologies.put("Jennifer CRUZ","Aprendizaje basado en proyectos (ABP) para equipos directivos");
        methodologies.put("Dafna y Mónica GELLER", "Ingeniería para niños (EFK-Chile)");
        methodologies.put("Diego URIBE","TinkerTrak, creatividad para un aprendizaje");


        resourcesAdapter = new VideoListAdapter(this, videos);
        resourcesList = (ListView) findViewById(R.id.resources_list);
        resourcesList.setAdapter(resourcesAdapter);

        methodologiesAdapter = new VideoListAdapter(this, methodologies);
        methodologiesList = (ListView) findViewById(R.id.methodologies_list);
        methodologiesList.setAdapter(methodologiesAdapter);

        fabToChoose = (FloatingActionButton) findViewById(R.id.fab_to_choose);
        fabToChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StageChooseActivity.getIntent(StageConnectActivity.this));
            }
        });
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context,StageConnectActivity.class);
        return intent;
    }
}
