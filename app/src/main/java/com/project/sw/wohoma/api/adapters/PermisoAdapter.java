package com.project.sw.wohoma.api.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.sw.wohoma.R;
import com.project.sw.wohoma.api.model.PermisoModel;

import java.text.ParseException;
import java.util.List;

public class PermisoAdapter extends RecyclerView.Adapter<PermisoAdapter.ViewHolder>{

    private List<PermisoModel> listaPermisos;
    private Context context;

    public PermisoAdapter(Context context, List<PermisoModel> listaPermisos){
        this.context = context;
        this.listaPermisos = listaPermisos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView permisoFechaInicio, permisoFechaFin, permisoBand;
        public ViewHolder(View itemView) {
            super(itemView);
            permisoFechaInicio = itemView.findViewById(R.id.itemPermisoFechaInicio);
            permisoFechaFin = itemView.findViewById(R.id.itemPermisoFechaFin);
            permisoBand = itemView.findViewById(R.id.itemPermisoBand);
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PermisoModel entidadPermiso = listaPermisos.get(position);
        holder.permisoFechaInicio.setText(entidadPermiso.getFechaInicial());
        holder.permisoFechaFin.setText(entidadPermiso.getFechaFinal());
        holder.permisoBand.setText(entidadPermiso.estado);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_permiso, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return listaPermisos.size();
    }
}
