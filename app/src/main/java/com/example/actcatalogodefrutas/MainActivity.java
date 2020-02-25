package com.example.actcatalogodefrutas;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.actcatalogodefrutas.adapter.FrutaAdapter;
import com.example.actcatalogodefrutas.dialogo.DialogoAgregarFruta;
import com.example.actcatalogodefrutas.servicio.Fruta;
import com.example.actcatalogodefrutas.servicio.ServicioFrutas;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements DialogoAgregarFruta.OnAgregarFrutaListener {

    private FrutaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rv = findViewById(R.id.recyvlerView);
        try {
            adapter = new FrutaAdapter(this, ServicioFrutas.getInstance(this).cargarDatos(), new FrutaAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(FrutaAdapter.FrutaViewHolder holder, int posicion) {
                    confirmacion(posicion);     
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar el archivo", Toast.LENGTH_SHORT).show();
        }
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    private void confirmacion(final int posicion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Está seguro de querer eliminar el elemento?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            ServicioFrutas.getInstance(MainActivity.this).eliminar(posicion);
                        } catch (IOException e) {
                            Toast.makeText(MainActivity.this, "Error al actualizar el archivo", Toast.LENGTH_SHORT).show();
                        } catch (ClassNotFoundException e) {
                            Toast.makeText(MainActivity.this, "Error al eliminar el elemento", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            DialogoAgregarFruta agregarFruta = new DialogoAgregarFruta();
            agregarFruta.show(getSupportFragmentManager(),DialogoAgregarFruta.TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAgregarFruta(Fruta fruta) {
        try {
            ServicioFrutas.getInstance(this).guardarFruta(fruta);
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "Error al actualizar el archivo", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(MainActivity.this, "Error al guardar elemento en la lista", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }
}
