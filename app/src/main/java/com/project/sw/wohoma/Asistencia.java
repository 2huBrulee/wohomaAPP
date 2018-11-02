package com.project.sw.wohoma;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.sw.wohoma.api.model.AsistenciaModel;
import com.project.sw.wohoma.api.model.AsistenciaRequest;
import com.project.sw.wohoma.api.model.TiendaModel;
import com.project.sw.wohoma.api.webservice.ApiAdapter;
import com.project.sw.wohoma.api.webservice.ApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Asistencia extends AppCompatActivity {

    private Toolbar myToolbar;
    private TextView tvToolBar;

    private TextView tvTienda;
    private TextView tvEntradaH;
    private TextView tvSalidaH;
    private Button btnEntrada;
    private Button btnSalida;

    //private EntidadAsistencia entidadAsistencia;

    private Bundle bundle;
    private int idEmpleado;
    private int idTienda;
    private String nombreTienda;
    private TiendaModel tienda;

    private AsistenciaRequest requestEntrada;
    private AsistenciaRequest requestSalida;
    private ArrayList<AsistenciaModel> listaAsistencias = new ArrayList<>();

    private ApiService apiService;
    private final String TAG = Asistencia.class.getSimpleName();

    private CharSequence MSG_ENTRADA_MARCADA = "Entrada registrada:\n";
    private CharSequence MSG_SALIDA_MARCADA = "Salida registrada:\n";
    private CharSequence ERROR_REGISTRO = "Ocurrio un error en el registro";
    private CharSequence ERROR = "UPPS PASO ALGO";

    private Intent intent;
    private Context context;
    private int duration = Toast.LENGTH_SHORT;
    private Toast toast;

    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

    //variables para el gps
    double latitud, longitud;
    private LocationManager locationManager = null;
    LocationListener locationListener = null;
    double[] punto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);

        btnEntrada = findViewById(R.id.btnMarcarEntrada);
        btnEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarUbicacion())
                    marcarEntrada();
            }
        });

        btnSalida = findViewById(R.id.btnMarcarSalida);
        btnSalida.setEnabled(false);
        btnSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarUbicacion())
                    marcarSalida();
            }
        });

        myToolbar = findViewById(R.id.appToolBar);
        tvToolBar =  myToolbar.findViewById(R.id.appToolBar_title);

        context = getApplicationContext();

        tvTienda = findViewById(R.id.tv_tienda);
        tvEntradaH = findViewById(R.id.entradaH);
        tvSalidaH = findViewById(R.id.salidaH);
        bundle = getIntent().getExtras();

        idEmpleado = bundle.getInt("USER_ID");
        idTienda = bundle.getInt("ID_TIENDA");
        nombreTienda = bundle.getString("USER_TIENDA");
        tienda = (TiendaModel) bundle.getSerializable("TIENDA_DATA");
        System.out.println("INFORMACION DE LA TIENDA "+tienda.getNombreTienda());

        requestEntrada = new AsistenciaRequest(idEmpleado);

        getAsistencia();

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tvToolBar.setText("Asistencia");
        tvTienda.setText(nombreTienda);

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
            }

            @Override
            public void onProviderDisabled(String s) {
            }
        };

    }

    public void marcarEntrada(){
        apiService = ApiAdapter.createService(ApiService.class);
        Call<AsistenciaModel> call = apiService.marcarEntrada(requestEntrada);
        call.enqueue(new Callback<AsistenciaModel>() {
            @Override
            public void onResponse(Call<AsistenciaModel> call, Response<AsistenciaModel> response) {
                if(response.isSuccessful()){
                    //btnSalida.setEnabled(true);
                    AsistenciaModel entradaMarcadaDatos = response.body();
                    tvEntradaH.setText(entradaMarcadaDatos.getAsistenciaEntrada());

                    MSG_ENTRADA_MARCADA = MSG_ENTRADA_MARCADA + entradaMarcadaDatos.getAsistenciaEntrada();

                    toast = Toast.makeText(context, MSG_ENTRADA_MARCADA, Toast.LENGTH_SHORT);
                    toast.show();
                    finish();

                }else{
                    toast = Toast.makeText(context, ERROR_REGISTRO, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<AsistenciaModel> call, Throwable t) {
                Log.e(TAG, "NO SE PUDO CONECTAR CON EL API");
            }
        });
    }

    public void marcarSalida(){
        Date horaSalida = new Date();
        String strHoraSalida = formatter.format(horaSalida);
        requestSalida = new AsistenciaRequest(idEmpleado, strHoraSalida);

        apiService = ApiAdapter.createService(ApiService.class);
        Call<AsistenciaModel> call = apiService.marcarSalida(requestSalida, idEmpleado);
        System.out.println("ID ENVIANDO ==> " + idEmpleado);
        call.enqueue(new Callback<AsistenciaModel>() {
            @Override
            public void onResponse(Call<AsistenciaModel> call, Response<AsistenciaModel> response) {
                if(response.isSuccessful()){
                    AsistenciaModel salidaMarcadaDatos = response.body();
                    tvSalidaH.setText(salidaMarcadaDatos.getAsistenciaSalida());

                    MSG_SALIDA_MARCADA = MSG_SALIDA_MARCADA + salidaMarcadaDatos.getAsistenciaSalida();

                    toast = Toast.makeText(context, MSG_SALIDA_MARCADA, Toast.LENGTH_SHORT);
                    toast.show();
                    finish();

                }else{
                    toast = Toast.makeText(context, ERROR_REGISTRO, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<AsistenciaModel> call, Throwable t) {
                Log.e(TAG, "NO SE PUDO CONECTAR CON EL API");
            }
        });
    }

    public void getAsistencia(){
        Date date = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        final String fechaHoy = formatoFecha.format(date);

        apiService = ApiAdapter.createService(ApiService.class);
        Call<List<AsistenciaModel>> call = apiService.getAsistencia(idEmpleado);
        call.enqueue(new Callback<List<AsistenciaModel>>() {
            @Override
            public void onResponse(Call<List<AsistenciaModel>> call, Response<List<AsistenciaModel>> response) {
                if(response.isSuccessful()){
                    for (AsistenciaModel asistencia: response.body())
                        listaAsistencias.add(asistencia);

                    String fechaUltimaAsistencia, asistenciaEntrada, asistenciaSalida;
                    fechaUltimaAsistencia = listaAsistencias.get(0).getFecha();
                    asistenciaEntrada = listaAsistencias.get(0).getAsistenciaEntrada();
                    asistenciaSalida = listaAsistencias.get(0).getAsistenciaSalida();
                    if(fechaUltimaAsistencia.equals(fechaHoy)){
                        tvEntradaH.setText(asistenciaEntrada);
                        btnSalida.setEnabled(true);
                        if(asistenciaSalida.equals("00:00:00")){
                            tvSalidaH.setText("-");
                        }
                        else{
                            tvSalidaH.setText(asistenciaSalida);
                        }
                    }else{
                        tvEntradaH.setText("-");
                        tvSalidaH.setText("-");
                    }

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

    //Validacion de gps
    static boolean intersects(double[] A, double[] B, double[] P) {
        if (A[1] > B[1])
            return intersects(B, A, P);

        //if (P[1] == A[1] || P[1] == B[1])
        //    P[1] += 0.0001;

        if (P[1] > B[1] || P[1] < A[1] || P[0] > max(A[0], B[0]))
            return false;

        if (P[0] <= min(A[0], B[0]))
            return true;

        double red = (P[1] - A[1]) / (double) (P[0] - A[0]);
        double blue = (B[1] - A[1]) / (double) (B[0] - A[0]);
        return red >= blue;
    }

    static boolean contains(double[][] shape, double[] pnt) {
        boolean inside = false;
        int len = shape.length;
        for (int i = 0; i < len; i++) {
            if (intersects(shape[i], shape[(i + 1) % len], pnt)){
                inside = true;
            }
        }
        return inside;
    }

    public boolean obtenerGPS () {
        boolean flag = false;
        Context context = getApplicationContext();
        CharSequence error = "security exception, no location available";
        CharSequence error2 = "Presione otra vez";
        int duration = Toast.LENGTH_SHORT;
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener);
            if (longitud != 0.0 && latitud != 0.0 ) {
                punto = new double[2];
                punto[0] = longitud;
                punto[1] = latitud;
                Toast toast = Toast.makeText(context, "Exito en obtener su ubicacion", duration);
                Log.e(TAG, "puntos: " + punto[0] + " " + punto[1]);
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

    public boolean validarUbicacion() {
        boolean flag = false;
        if (obtenerGPS()) {
            double[][] shape = {{Double.parseDouble(tienda.getLonguitud0()), Double.parseDouble(tienda.getLatitud0())},
                    {Double.parseDouble(tienda.getLonguitud1()), Double.parseDouble(tienda.getLatitud1())},
                    {Double.parseDouble(tienda.getLonguitud2()), Double.parseDouble(tienda.getLatitud2())},
                    {Double.parseDouble(tienda.getLonguitud3()), Double.parseDouble(tienda.getLatitud3())}};
            Log.e(TAG, "shape: " + shape[0][0] + " " + shape[0][1]);
            if (contains(shape, punto)) {
                flag = true;
                Log.e(TAG, "validar ubicacion: " + flag);
                return flag;
            }
        }
        Log.e(TAG, "validar ubicacion: " + flag);
        return flag;
    }

}
