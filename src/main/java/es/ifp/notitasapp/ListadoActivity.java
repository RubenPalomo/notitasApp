package es.ifp.notitasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {
    protected ListView listaNotas;

    private ArrayList<String> notas = new ArrayList<>();
    private ArrayAdapter<String> adaptador;
    private DataBaseSQL db;
    private Intent pasarPantalla;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listaNotas = (ListView) findViewById(R.id.listView_second);

        db = new DataBaseSQL(this);

        if(db.numeroNotas()!=0){
            notas = db.getAllNotas();
            adaptador = new ArrayAdapter<String>(ListadoActivity.this,
                    android.R.layout.simple_list_item_1, notas);
            listaNotas.setAdapter(adaptador);
            db.close();
        }
        else{
            Toast.makeText(this, getString(R.string.toast1_second_text), Toast.LENGTH_SHORT).show();
        }

        listaNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mp = MediaPlayer.create(ListadoActivity.this, R.raw.sonidoclick2);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                    }
                });
                mp.start();

                pasarPantalla = new Intent(ListadoActivity.this, VerNotaActivity.class);

                String contenidoItem = parent.getItemAtPosition(position).toString();
                pasarPantalla.putExtra("infoNota", contenidoItem);
                finish();
                startActivity(pasarPantalla);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.crear_menu:
                mp = MediaPlayer.create(ListadoActivity.this, R.raw.sonidoclick2);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                    }
                });
                mp.start();

                pasarPantalla = new Intent(ListadoActivity.this, CrearNotaActivity.class);
                finish();
                startActivity(pasarPantalla);
                return true;

            case R.id.opciones_menu:
                mp = MediaPlayer.create(ListadoActivity.this, R.raw.sonidoclick2);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                    }
                });
                mp.start();

                pasarPantalla = new Intent(ListadoActivity.this, BorrarNotasActivity.class);
                finish();
                startActivity(pasarPantalla);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}