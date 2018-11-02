package com.project.sw.wohoma;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.project.sw.wohoma.api.adapters.TiendaAdapter;
import com.project.sw.wohoma.api.model.TiendaModel;
import com.project.sw.wohoma.api.webservice.ApiAdapter;
import com.project.sw.wohoma.api.webservice.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrincipalAdmin extends AppCompatActivity {

    private TextView tvToolBar;
    private Toolbar myToolbar;

    private Intent intent;
    private Context context;
    private int duration = Toast.LENGTH_SHORT;

    private CharSequence correcto = "Exito en el registro";
    private CharSequence error_login = "Ocurrio un error en el registro";
    private CharSequence datos_vacios = "Faltan datos";
    private CharSequence ERROR = "UPPS PASO ALGO";
    private Toast toast;

    private ApiService apiService;
    private final String TAG = RegistroTienda.class.getSimpleName();

    private RecyclerView recyclerView;
    private ArrayList<TiendaModel> tiendas = new ArrayList<>();
    private TiendaAdapter tiendaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_admin);

        myToolbar = findViewById(R.id.appToolBar);
        tvToolBar = myToolbar.findViewById(R.id.appToolBar_title);

        recyclerView = findViewById(R.id.my_recycler_view);
        tiendaAdapter = new TiendaAdapter(PrincipalAdmin.this, tiendas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PrincipalAdmin.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(tiendaAdapter);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolBar.setText("Tiendas");

        getTiendas();
    }

    public void nuevaTiendaBtn(View view) {
        intent = new Intent(this, RegistroTienda.class);
        startActivity(intent);
    }

    public void getTiendas(){
        apiService = ApiAdapter.createService(ApiService.class);
        Call<List<TiendaModel>> call = apiService.getTiendas();
        call.enqueue(new Callback<List<TiendaModel>>() {
            @Override
            public void onResponse(Call<List<TiendaModel>> call, Response<List<TiendaModel>> response) {
                if(response.isSuccessful()){
                    for(TiendaModel tienda: response.body()){
                        tiendas.add(tienda);
                    }
                    tiendaAdapter.notifyDataSetChanged();
                }else{
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<List<TiendaModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id_item = item.getItemId();
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if( id_item == R.id.item_cerrar_sesion){
            startActivity(intent);
            finish();
        }
        return true;
    }

}
