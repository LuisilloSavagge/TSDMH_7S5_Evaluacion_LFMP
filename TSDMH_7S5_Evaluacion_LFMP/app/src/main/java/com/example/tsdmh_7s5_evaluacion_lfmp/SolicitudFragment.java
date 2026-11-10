package com.example.tsdmh_7s5_evaluacion_lfmp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SolicitudFragment extends Fragment {

    private Button btnSolicitar;
    private ProgressBar progressBar;
    private TextView tvCargando;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_solicitud_fragment, container, false);

        btnSolicitar = view.findViewById(R.id.btnSolicitar);
        progressBar = view.findViewById(R.id.progressBar);
        tvCargando = view.findViewById(R.id.tvCargando);

        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SimularProcesoTask().execute();
            }
        });

        return view;
    }

    private class SimularProcesoTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            btnSolicitar.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
            tvCargando.setText("Cargando datos...");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(200);
                    publishProgress(i * 10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).mostrarFaseResultados();
            }
        }
    }
}

