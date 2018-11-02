package com.project.sw.wohoma;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.location.Location;
import android.widget.Toast;
import android.widget.Button;

import com.project.sw.wohoma.api.model.TiendaModel;
import com.project.sw.wohoma.api.model.TiendaRequest;
import com.project.sw.wohoma.api.webservice.ApiAdapter;
import com.project.sw.wohoma.api.webservice.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroTienda extends AppCompatActivity {

    private TextView tvToolBar;
    private Toolbar myToolbar;

    private EditText nombreTienda;
    private EditText direcTienda;
    private EditText refTienda;
    private EditText telfTienda;

    private String direccion;
    private String referencia;
    private String telefono;
    double latitud, longitud;

    private Intent intent;
    private Context context;
    private int duration = Toast.LENGTH_SHORT;

    private CharSequence correcto = "Exito en el registro";
    private CharSequence error_login = "Ocurrio un error en el registro";
    private CharSequence datos_vacios = "Faltan datos";
    private CharSequence ERROR = "UPPS PASO ALGO";
    private Toast toast;

    private TiendaRequest tiendaRequest;

    private ApiService apiService;
    private String status;
    private final String TAG = RegistroTienda.class.getSimpleName();

    private LocationManager locationManager = null;
    LocationListener locationListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tienda);

        apiService = ApiAdapter.createService(ApiService.class);
        tiendaRequest = new TiendaRequest();

        nombreTienda = findViewById(R.id.nombreTiendaTxt);
        direcTienda = findViewById(R.id.direcTiendaTxt);
        refTienda = findViewById(R.id.refTiendaTxt);
        telfTienda = findViewById(R.id.telfTiendaTxt);

        Button boton1 = findViewById(R.id.GPS1Btn);
        boton1.setEnabled(true);
        Button boton2 = findViewById(R.id.GPS2Btn);
        boton2.setEnabled(false);
        Button boton3 = findViewById(R.id.GPS3Btn);
        boton3.setEnabled(false);
        Button boton4 = findViewById(R.id.GPS4Btn);
        boton4.setEnabled(false);

        myToolbar = (Toolbar) findViewById(R.id.appToolBar);
        tvToolBar = myToolbar.findViewById(R.id.appToolBar_title);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitud = location.getLatitude();
                longitud = location.getLongitude();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
                System.out.println("PROVIDER ON");
            }

            @Override
            public void onProviderDisabled(String s) {
                System.out.println("PROVIDER OFF");
            }
        };

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tvToolBar.setText("Registrar Tienda");
    }

    public void gps1Btn(View view) {
        boolean flag = obtenerGPS();
        if(flag) {
            tiendaRequest.setLatitud0(latitud);
            tiendaRequest.setLonguitud0(longitud);
            Button boton1 = (Button) findViewById(R.id.GPS1Btn);
            boton1.setEnabled(false);
            Button boton2 = (Button) findViewById(R.id.GPS2Btn);
            boton2.setEnabled(true);
        }
    }

    public void gps2Btn(View view) {
        boolean flag = obtenerGPS();
        if(flag) {
            tiendaRequest.setLatitud1(latitud);
            tiendaRequest.setLonguitud1(longitud);
            Button boton1 = (Button) findViewById(R.id.GPS2Btn);
            boton1.setEnabled(false);
            Button boton2 = (Button) findViewById(R.id.GPS3Btn);
            boton2.setEnabled(true);
        }
    }

    public void gps3Btn(View view) {
        boolean flag = obtenerGPS();
        if(flag) {
            tiendaRequest.setLatitud2(latitud);
            tiendaRequest.setLonguitud2(longitud);
            Button boton1 = (Button) findViewById(R.id.GPS3Btn);
            boton1.setEnabled(false);
            Button boton2 = (Button) findViewById(R.id.GPS4Btn);
            boton2.setEnabled(true);
        }
    }

    public void gps4Btn(View view) {
        boolean flag = obtenerGPS();
        if(flag) {
            tiendaRequest.setLatitud3(latitud);
            tiendaRequest.setLonguitud3(longitud);
            Button boton1 = (Button) findViewById(R.id.GPS4Btn);
            boton1.setEnabled(false);
        }
    }

    public void registrarBtn(View view) {
        tiendaRequest.setNombreTienda(nombreTienda.getText().toString());
        tiendaRequest.setDireccion(direcTienda.getText().toString());
        tiendaRequest.setReferencia(refTienda.getText().toString());
        //posible problema
        tiendaRequest.setTelefono(Integer.parseInt(telfTienda.getText().toString()));

        context = getApplicationContext();
        if (!tiendaRequest.getDireccion().isEmpty() && !tiendaRequest.getReferencia().isEmpty()
                && !tiendaRequest.getNombreTienda().isEmpty()
                && tiendaRequest.getLatitud0() != 0.0 && tiendaRequest.getLonguitud0() != 0.0
                && tiendaRequest.getLatitud1() != 0.0 && tiendaRequest.getLonguitud1() != 0.0
                && tiendaRequest.getLatitud2() != 0.0 && tiendaRequest.getLonguitud2() != 0.0
                && tiendaRequest.getLatitud3() != 0.0 && tiendaRequest.getLonguitud3() != 0.0) {

            saveTienda(tiendaRequest);
        } else {
            toast = Toast.makeText(context, datos_vacios, duration);
            toast.show();
        }
    }

    public boolean obtenerGPS () {
        boolean flag = false;
        Context context = getApplicationContext();
        CharSequence error = "security exception, no location available";
        CharSequence latlong = "latitud: " + latitud + " longitud: " + longitud;
        CharSequence error2 = "Presione otra vez";
        int duration = Toast.LENGTH_SHORT;
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener);
            System.out.println("DATOS DEL GPS ==> " + longitud + " <> " + latitud);
            if (longitud != 0.0 && latitud != 0.0 ) {
                Toast toast = Toast.makeText(context, latlong, duration);
                toast.show();
                flag = true;
            } else {
                Toast toast = Toast.makeText(context, error2, duration);
                toast.show();
                flag = false;
            }
        } catch (SecurityException ex) {
            Toast toast = Toast.makeText(context, error, duration);
            toast.show();
            flag = false;
        }
        return flag;
    }

    public void saveTienda(TiendaRequest tiendaRequest){
        apiService = ApiAdapter.createService(ApiService.class);
        Call<TiendaModel> call = apiService.saveTienda(tiendaRequest);
        call.enqueue(new Callback<TiendaModel>() {
            @Override
            public void onResponse(Call<TiendaModel> call, Response<TiendaModel> response) {
                if (response.isSuccessful()){
                    TiendaModel tiendaModel = response.body();
                    if(tiendaModel.getIdTienda()!=0){
                        toast = Toast.makeText(context, correcto, duration);
                        toast.show();

                        intent = new Intent(RegistroTienda.this, PrincipalAdmin.class);
                        startActivity(intent);

                        direcTienda.setText("");
                        refTienda.setText("");
                        telfTienda.setText("");
                    }else {
                        toast = Toast.makeText(context, error_login, duration);
                        toast.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TiendaModel> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
                toast = Toast.makeText(context, ERROR, duration);
                toast.show();
            }
        });
    }

}