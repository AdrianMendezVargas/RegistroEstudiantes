package com.itla.registroestudieantes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.itla.registroestudieantes.adapters.EstudianteAdaptador;
import com.itla.registroestudieantes.models.EstudianteWithCarrera;
import com.itla.registroestudieantes.repositories.EstudianteRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    EstudianteRepository estudianteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnnewestudent = findViewById(R.id.btnnewestmain);
        btnnewestudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateEstudianteActivity.class);
                startActivityForResult(intent,1);
            }
        });

        loadEstudents();
    }

    private void loadEstudents(){
        List<EstudianteWithCarrera> estudiantes = estudianteRepository.getAllWithCarrera();

        recyclerView=findViewById(R.id.rvestmain);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter=new EstudianteAdaptador(estudiantes);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        loadEstudents();
    }
}
