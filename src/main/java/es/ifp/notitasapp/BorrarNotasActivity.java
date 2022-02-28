package es.ifp.notitasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BorrarNotasActivity extends AppCompatActivity {

    protected Button borrar;
    protected Button volver;

    private Intent pasarPantalla;
    private DataBaseSQL db;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_notas);
        getSupportActionBar().hide();

        borrar = (Button) findViewById(R.id.botonBorrar_borrarNotas);
        volver = (Button) findViewById(R.id.botonVolver_borrarNotas);

        db = new DataBaseSQL(BorrarNotasActivity.this);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(BorrarNotasActivity.this, R.raw.sonidoclick);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                    }
                });
                mp.start();

                db.deleteAllNotas();
                Toast.makeText(BorrarNotasActivity.this, getString(R.string.toastBorrado_borrarNotas_text),
                        Toast.LENGTH_SHORT).show();
                db.close();

                pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(BorrarNotasActivity.this, R.raw.sonidoclick);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                    }
                });
                mp.start();

                pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
        finish();
        startActivity(pasarPantalla);
    }
}