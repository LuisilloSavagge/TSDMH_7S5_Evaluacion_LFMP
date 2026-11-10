package com.example.tsdmh_7s5_evaluacion_lfmp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutSpinnerContainer;
    private Spinner spinnerCategorias;
    private boolean isUserSelecting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutSpinnerContainer = findViewById(R.id.layoutSpinnerContainer);
        spinnerCategorias = findViewById(R.id.spinnerCategorias);


        String[] categorias = {
                "Seleccione una categoría...",
                "PRODUCTOS DE OFICINA"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorias.setAdapter(adapter);

        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    return;
                }


                String categoriaSeleccionada = parent.getItemAtPosition(position).toString();

                ResultFragment resultFragment = ResultFragment.newInstance(categoriaSeleccionada);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, resultFragment)
                        .commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new SolicitudFragment())
                    .commit();
        }
    }

    public void mostrarFaseResultados() {
        layoutSpinnerContainer.setVisibility(View.VISIBLE);
        spinnerCategorias.setSelection(0);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new ResultFragment())
                .commit();
    }
}
