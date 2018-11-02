package com.project.sw.wohoma.api.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.sw.wohoma.R;
import com.project.sw.wohoma.api.model.TiendaModel;

import java.util.List;

public class TiendaAdapter extends RecyclerView.Adapter<TiendaAdapter.ViewHolder>{

    private List<TiendaModel> tiendas;
    private Context context;

    public TiendaAdapter(Context context, List<TiendaModel> tiendas){
        this.tiendas = tiendas;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView idTienda, direccion, referencia, telefono;
        public ViewHolder(View itemView) {
            super(itemView);
            idTienda = itemView.findViewById(R.id.idTienda);
            direccion = itemView.findViewById(R.id.direccion);
            referencia = itemView.findViewById(R.id.referencia);
            telefono = itemView.findViewById(R.id.telefono);
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TiendaModel tienda = tiendas.get(position);
        holder.idTienda.setText(tienda.getNombreTienda());
        holder.direccion.setText(tienda.getDireccion());
        holder.referencia.setText(tienda.getReferencia());
        holder.telefono.setText(String.valueOf(tienda.getTelefono()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tienda, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return tiendas.size();
    }

}
