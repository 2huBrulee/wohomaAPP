package com.project.sw.wohoma;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.sw.wohoma.api.model.EmpleadoModel;
import com.project.sw.wohoma.api.model.LoginRequest;
import com.project.sw.wohoma.api.model.LoginResponse;
import com.project.sw.wohoma.api.model.RolModel;
import com.project.sw.wohoma.api.model.TiendaModel;
import com.project.sw.wohoma.api.webservice.ApiAdapter;
import com.project.sw.wohoma.api.webservice.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText edUser;
    private EditText edPass;
    private Button iniciarBtn;

    private String email;
    private String pass;

    private CharSequence BIENVENIDO = "Bienvenido";
    private CharSequence error_login = "Correo y/o contraseña no válidos";
    private CharSequence datos_vacios = "Faltan datos";
    private CharSequence ERROR = "UPPS PASO ALGO";
    private Toast toast;

    private Intent intent;
    private Context context;
    private int duration = Toast.LENGTH_SHORT;

    private ApiService apiService;
    private final String TAG = Login.class.getSimpleName();

    private LoginResponse loginResponse;
    private EmpleadoModel empleado;
    private RolModel rolModel;
    private TiendaModel tiendaModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUser = findViewById(R.id.userTxt);
        edPass = findViewById(R.id.passTxt);
        iniciarBtn = findViewById(R.id.iniciarBtn);

        iniciarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edUser.getText().toString();
                pass = edPass.getText().toString();

                context = getApplicationContext();
                if (!email.isEmpty() && !pass.isEmpty()) {
                    LoginRequest datos = new LoginRequest(email, pass);
                    validateLogin(datos);
                } else {
                    toast = Toast.makeText(context, datos_vacios, duration);
                    toast.show();
                }
            }
        });
    }

    public void validateLogin(LoginRequest datos){
        apiService = ApiAdapter.createService(ApiService.class);
        Call<LoginResponse> call = apiService.validateLogin(datos);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    loginResponse = response.body();
                    if(loginResponse.getStatus() == 200){
                        empleado = loginResponse.getEmpleado();
                        rolModel = empleado.getIdRol();
                        tiendaModel = empleado.getIdTienda();
                        if(rolModel.getIdRol() == 1){
                            intent = new Intent(Login.this, PrincipalAdmin.class);
                            startActivity(intent);
                        }else{
                            intent = new Intent(Login.this, Principal.class);
                            intent.putExtra("USER_ID", empleado.getIdEmpleado());
                            intent.putExtra("USER_FULL_NAME", empleado.getNombre() + " " + empleado.getApellidos());
                            intent.putExtra("USER_DNI", empleado.getDni().toString());
                            intent.putExtra("USER_TIENDA", tiendaModel.getNombreTienda());
                            intent.putExtra("ID_TIENDA", tiendaModel.getIdTienda());
                            intent.putExtra("TIENDA_DATA", tiendaModel);
                            startActivity(intent);
                        }
                    }
                    edUser.setText("");
                    edPass.setText("");

                    Log.e(TAG, "EXITO");
                    toast = Toast.makeText(context, BIENVENIDO, duration);
                    toast.show();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
                toast = Toast.makeText(context, ERROR, duration);
                toast.show();
            }
        });
    }

}