package com.project.sw.wohoma;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.sw.wohoma.api.adapters.PermisoAdapter;
import com.project.sw.wohoma.api.model.PermisoModel;
import com.project.sw.wohoma.api.model.PermisoRequest;
import com.project.sw.wohoma.api.webservice.ApiAdapter;
import com.project.sw.wohoma.api.webservice.ApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Permisos extends AppCompatActivity {

    private Toolbar myToolbar;
    private TextView tvToolBar;

    private EditText edRazon;

    DatePickerDialog datePickerDialog;
    private EditText fechaInicio;
    private String diaInicio, mesInicio, anyoInicio;
    private EditText fechaFin;
    private String diaFin, mesFin, anyoFin;

    private Bundle bundle;
    private int idEmpleado;

    private CharSequence ENVIO_EXITOSO = "PERMISO ENVIADO";
    private Toast toast;

    private Intent intent;
    private Context context;
    private int duration = Toast.LENGTH_SHORT;

    private ApiService apiService;
    private final String TAG = Permisos.class.getSimpleName();

    private RecyclerView recyclerView;
    private ArrayList<PermisoModel> listaPermisos = new ArrayList<>();
    private PermisoAdapter permisoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisos);

        context = getApplicationContext();

        myToolbar = (Toolbar) findViewById(R.id.appToolBar);
        tvToolBar =  myToolbar.findViewById(R.id.appToolBar_title);
        fechaInicio = findViewById(R.id.permisoFechaInicio);
        fechaFin = findViewById(R.id.permisoFechaFin);
        edRazon = findViewById(R.id.razonTxt);

        bundle = getIntent().getExtras();

        idEmpleado = bundle.getInt("USER_ID");

        recyclerView = findViewById(R.id.recycler_view_permisos);
        permisoAdapter = new PermisoAdapter(Permisos.this, listaPermisos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Permisos.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(permisoAdapter);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tvToolBar.setText("Solicitud de Permiso");
        getListaPermisoById();

        fechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escogerFecha(fechaInicio, 1);
            }
        });

        fechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escogerFecha(fechaFin, 2);
            }
        });
    }

    public void enviarBtn (View view) {
        String json, fechaInicial, fechaFin, razon;
        fechaInicial = anyoInicio + "-" + mesInicio + "-" + diaInicio;
        fechaFin = anyoFin + "-" + mesFin + "-" + diaFin;
        razon = edRazon.getText().toString();

        PermisoRequest permisoRequest = new PermisoRequest(idEmpleado, razon, fechaInicial, fechaFin);
        enviarPermiso(permisoRequest);
    }

    public void enviarPermiso(PermisoRequest permisoRequest){
        apiService = ApiAdapter.createService(ApiService.class);
        Call<PermisoModel> call = apiService.enviarPermiso(permisoRequest);
        call.enqueue(new Callback<PermisoModel>() {
            @Override
            public void onResponse(Call<PermisoModel> call, Response<PermisoModel> response) {
                if(response.isSuccessful()){
                    toast = Toast.makeText(context, ENVIO_EXITOSO, duration);
                    toast.show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<PermisoModel> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
            }
        });
    }


    public void getListaPermisoById(){
        apiService = ApiAdapter.createService(ApiService.class);
        Call<List<PermisoModel>> call = apiService.getListaPermisoById(idEmpleado);
        call.enqueue(new Callback<List<PermisoModel>>() {
            @Override
            public void onResponse(Call<List<PermisoModel>> call, Response<List<PermisoModel>> response) {
                if(response.isSuccessful()){
                    for (PermisoModel permiso: response.body()){
                        listaPermisos.add(permiso);
                    }
                    permisoAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<PermisoModel>> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
            }
        });
    }

    public void escogerFecha(final EditText fecha, final int setInicio_Fin){
        // calender class's instance and get current date , month and year from calender
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialog = new DatePickerDialog(Permisos.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        fecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        if (setInicio_Fin == 1){
                            diaInicio = String.valueOf(dayOfMonth);
                            mesInicio = String.valueOf(monthOfYear + 1);
                            anyoInicio = String.valueOf(year);
                        }else {
                            diaFin = String.valueOf(dayOfMonth);
                            mesFin = String.valueOf(monthOfYear + 1);
                            anyoFin = String.valueOf(year);
                        }
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
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

