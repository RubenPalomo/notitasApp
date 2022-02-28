package es.ifp.notitasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VerNotaActivity extends AppCompatActivity {

    protected TextView label1;
    protected Button botonVolver;
    protected Button botonBorrar;

    private Intent pasarPantalla;
    private Bundle extras;
    private DataBaseSQL db;
    private MediaPlayer mp;

    private String nota;

    public String getNota(){
        extras = getIntent().getExtras();
        if(extras != null){
            nota = extras.getString("infoNota");
            return nota;
        }
        else{
            return getString(R.string.error_verNota_text);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_nota);
        getSupportActionBar().hide();

        label1 = (TextView) findViewById(R.id.label1_vernota);
        botonVolver = (Button) findViewById(R.id.volver_verNota);
        botonBorrar = (Button) findViewById(R.id.borrar_verNota);

        label1.setText(getNota());

        db = new DataBaseSQL(VerNotaActivity.this);

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(VerNotaActivity.this, R.raw.sonidoclick);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                    }
                });
                mp.start();

                pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });

        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(VerNotaActivity.this, R.raw.sonidoclick);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                    }
                });
                mp.start();

                try {
                    db.borrarNota(db.getIdNota(nota));
                    Toast.makeText(VerNotaActivity.this, getText(R.string.toastBorrado_verNota_text),
                            Toast.LENGTH_SHORT).show();
                    db.close();
                } catch (Exception e) {
                    Toast.makeText(VerNotaActivity.this, getText(R.string.toastError_verNota_text),
                            Toast.LENGTH_SHORT).show();
                }

                pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
        finish();
        startActivity(pasarPantalla);
    }
}