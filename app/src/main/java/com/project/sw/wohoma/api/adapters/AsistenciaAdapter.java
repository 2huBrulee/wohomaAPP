package com.project.sw.wohoma.api.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.sw.wohoma.R;
import com.project.sw.wohoma.api.model.AsistenciaModel;

import java.util.ArrayList;

public class AsistenciaAdapter extends RecyclerView.Adapter<AsistenciaAdapter.ViewHolder>{

    private ArrayList<AsistenciaModel> asistencias;
    private Context context;

    public AsistenciaAdapter(Context context, ArrayList<AsistenciaModel> asistencias){
        this.asistencias = asistencias;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView idAsistencia, hEntrada, hSalida, fecha, hExtras;
        public ViewHolder(View itemView) {
            super(itemView);
            //idAsistencia = itemView.findViewById(R.id.idAsistencia);
            hEntrada = itemView.findViewById(R.id.hEntrada);
            hSalida = itemView.findViewById(R.id.hSalida);
            fecha = itemView.findViewById(R.id.fecha);
            hExtras = itemView.findViewById(R.id.hExtras);
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AsistenciaModel asistencia = asistencias.get(position);
        holder.hEntrada.setText(asistencia.getAsistenciaEntrada());
        holder.hSalida.setText(asistencia.getAsistenciaSalida());
        holder.fecha.setText(asistencia.getFecha());
        holder.hExtras.setText(asistencia.getHorasExtras());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_asistencia, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return asistencias.size();
    }

}
