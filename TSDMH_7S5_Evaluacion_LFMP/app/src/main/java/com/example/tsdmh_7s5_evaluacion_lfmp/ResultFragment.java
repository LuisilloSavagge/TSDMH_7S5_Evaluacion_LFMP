package com.example.tsdmh_7s5_evaluacion_lfmp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {

    private static final String ARG_CATEGORIA = "categoria";

    public static ResultFragment newInstance(String categoria) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORIA, categoria);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_result_fragment, container, false);
        TextView tvEstado = view.findViewById(R.id.tvEstadoSeleccion);
        TextView tvLista = view.findViewById(R.id.tvListaResultados);

        if (getArguments() != null && getArguments().containsKey(ARG_CATEGORIA)) {
            String categoria = getArguments().getString(ARG_CATEGORIA);
            tvEstado.setText("Mostrando Fragmento de la Selección...\nCategoría: " + categoria);

            // Seleccionamos los elementos dinámicos según la categoría elegida en el Spinner
            String elementos = obtenerElementosPorCategoria(categoria);
            tvLista.setText(elementos);
        } else {
            tvEstado.setText("Mostrando Fragmento de la Selección...");
            tvLista.setText("Seleccione una categoría arriba para ver los elementos detallados.");
        }

        return view;
    }

    // Método auxiliar para retornar los productos o datos específicos de cada categoría
    private String obtenerElementosPorCategoria(String categoria) {
        switch (categoria) {
            case "PRODUCTOS DE OFICINA":
                return "• Bolígrafo Azul - $1.50\n" +
                        "• Cuaderno A4 - $4.00\n" +
                        "• Marcadores Color - $12.00\n" +
                        "• Marcadores A - $4.00\n" +
                        "• Cuaderno A#2 - $4.00\n" +
                        "• CuadernoBolígrafo Azul - $11.50";

            case "Base de Datos":
                return "• MySQL Server - Licencia Pro\n" +
                        "• PostgreSQL Cloud Instance\n" +
                        "• MongoDB Atlas Cluster\n" +
                        "• SQLite Local Storage\n" +
                        "• Redis Cache Service";

            case "Redes y Sincronización":
                return "• Router Cisco Gigabit\n" +
                        "• Cable UTP Cat 6 (100m)\n" +
                        "• Switch Administrable 24P\n" +
                        "• Adaptador USB Wi-Fi AC600\n" +
                        "• Patch Cord FO SC/LC";

            case "Reportes de Rendimiento":
                return "• Métrica de Uso de CPU\n" +
                        "• Auditoría de Memoria RAM\n" +
                        "• Análisis de Tráfico de Red\n" +
                        "• Log de Transacciones SQL\n" +
                        "• Reporte de Errores del Sistema";

            default:
                return "• Elemento general de la plataforma\n" +
                        "• Sincronización activa";
        }
    }
}