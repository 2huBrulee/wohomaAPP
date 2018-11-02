package com.project.sw.wohoma;

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
import android.widget.TextView;

import com.project.sw.wohoma.api.adapters.AsistenciaAdapter;
import com.project.sw.wohoma.api.model.AsistenciaModel;
import com.project.sw.wohoma.api.webservice.ApiAdapter;
import com.project.sw.wohoma.api.webservice.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Historial extends AppCompatActivity {

    private Toolbar myToolbar;
    private TextView tvToolBar;
    //private MenuView.ItemView iv_cerrarSesion;

    private TextView tvNombre;
    private TextView tvDni;
    private TextView tvTienda;

    private RecyclerView recyclerView;
    private ArrayList<AsistenciaModel> listaAsistencias = new ArrayList<>();
    private AsistenciaAdapter asistenciaAdapter;

    private Bundle bundle;
    private int idEmpleado;
    private String nombreEmpleado;
    private String dniEmpleado;
    private String nombreTienda;

    private ApiService apiService;
    private final String TAG = Asistencia.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        myToolbar = (Toolbar) findViewById(R.id.appToolBar);
        tvToolBar =  myToolbar.findViewById(R.id.appToolBar_title);
        //iv_cerrarSesion = findViewById(R.id.item_cerrar_sesion);

        tvNombre = findViewById(R.id.nombreTxt);
        tvDni = findViewById(R.id.DNITxt);
        tvTienda = findViewById(R.id.tiendaTxt);

        bundle = getIntent().getExtras();
        idEmpleado = bundle.getInt("USER_ID");
        nombreEmpleado = bundle.getString("USER_FULL_NAME");
        dniEmpleado = bundle.getString("USER_DNI");
        nombreTienda = bundle.getString("USER_TIENDA");

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tvToolBar.setText("Historial");

        recyclerView = findViewById(R.id.my_recycler_view);
        asistenciaAdapter = new AsistenciaAdapter(Historial.this, listaAsistencias);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Historial.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(asistenciaAdapter);

        getAsistencia();

        tvNombre.setText(nombreEmpleado);
        tvDni.setText(dniEmpleado);
        tvTienda.setText(nombreTienda);
    }

    public void getAsistencia(){
        apiService = ApiAdapter.createService(ApiService.class);
        Call<List<AsistenciaModel>> call = apiService.getAsistencia(idEmpleado);
        call.enqueue(new Callback<List<AsistenciaModel>>() {
            @Override
            public void onResponse(Call<List<AsistenciaModel>> call, Response<List<AsistenciaModel>> response) {
                if(response.isSuccessful()){
                    System.out.println("DATA ==> "+response.body());
                    for (AsistenciaModel asistencia: response.body())
                        listaAsistencias.add(asistencia);
                    asistenciaAdapter.notifyDataSetChanged();
                }else {
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<List<AsistenciaModel>> call, Throwable t) {
                System.out.println("ERROR AL OBTENER ASISTENCIAS");
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