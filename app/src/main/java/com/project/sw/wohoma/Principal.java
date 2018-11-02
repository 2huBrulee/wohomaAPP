package com.project.sw.wohoma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.project.sw.wohoma.api.model.TiendaModel;

public class Principal extends AppCompatActivity {

    private TextView tvToolBar;
    private Toolbar myToolbar;
    //private MenuView.ItemView iv_cerrarSesion;

    private Bundle bundle;
    private TextView tvNombre;
    private TextView tvDni;
    private TextView tvTienda;

    private int idEmpleado;
    private String nombreEmpleado;
    private String dniEmpleado;
    private String tiendaEmpleado;
    private int idTienda;

    TiendaModel tiendaModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        myToolbar = (Toolbar) findViewById(R.id.appToolBar);
        tvToolBar =  myToolbar.findViewById(R.id.appToolBar_title);
        //iv_cerrarSesion = findViewById(R.id.item_cerrar_sesion);

        bundle = getIntent().getExtras();
        tvNombre = findViewById(R.id.nombreTxt);
        tvDni = findViewById(R.id.DNIText);
        tvTienda = findViewById(R.id.tiendaTxt);

        idEmpleado = bundle.getInt("USER_ID");
        nombreEmpleado = bundle.getString("USER_FULL_NAME");
        dniEmpleado = bundle.getString("USER_DNI");
        idTienda = bundle.getInt("ID_TIENDA");
        //falta capturar la tienda
        tiendaEmpleado = bundle.getString("USER_TIENDA");
        tiendaModel = (TiendaModel) bundle.getSerializable("TIENDA_DATA");

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tvToolBar.setText("Inicio");

        tvNombre.setText(nombreEmpleado);
        tvDni.setText(dniEmpleado);
        tvTienda.setText(tiendaEmpleado);
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

    public void asistenciaBtn(View view) {
        Intent intent = new Intent(this, Asistencia.class);
        intent.putExtra("USER_ID", idEmpleado);
        //intent.putExtra("USER_DNI", dniEmpleado);
        intent.putExtra("ID_TIENDA", idTienda);
        intent.putExtra("USER_TIENDA", tiendaEmpleado);
        intent.putExtra("TIENDA_DATA", tiendaModel);
        startActivity(intent);
    }

    public void historialBtn (View view) {
        Intent intent = new Intent(this, Historial.class);
        intent.putExtra("USER_ID", idEmpleado);
        intent.putExtra("USER_FULL_NAME", nombreEmpleado);
        intent.putExtra("USER_DNI", dniEmpleado);
        intent.putExtra("ID_TIENDA", idTienda);
        intent.putExtra("USER_TIENDA", tiendaEmpleado);
        startActivity(intent);
    }

    public void permisosBtn (View view) {
        Intent intent = new Intent(this, Permisos.class);
        intent.putExtra("USER_ID", idEmpleado);
        startActivity(intent);
    }


}

