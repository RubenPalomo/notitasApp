package es.ifp.notitasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrearNotaActivity extends AppCompatActivity {

    private Intent pasarPantalla;
    private DataBaseSQL db;
    private MediaPlayer mp;

    protected Button botonCrear;
    protected Button botonVolver;
    protected EditText caja1;

    private String notita;

    public void crearNota(){
        mp = MediaPlayer.create(CrearNotaActivity.this, R.raw.sonidoclick);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
            }
        });
        mp.start();

        db = new DataBaseSQL(CrearNotaActivity.this);
        notita = caja1.getText().toString();
        if(notita.equals("")){
            Toast.makeText(CrearNotaActivity.this, getText(R.string.toast1_crearNota_text), Toast.LENGTH_LONG).show();
        }
        else{
            db.crearNota(notita);
            caja1.setText("");
            db.close();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);
        getSupportActionBar().hide();

        botonCrear = (Button) findViewById(R.id.crear_crearNota);
        botonVolver = (Button) findViewById((R.id.volver_crearNota));
        caja1 = (EditText) findViewById(R.id.caja1_crearNota);

        caja1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    crearNota();
                    return true;
                }
                return false;
            }
        });

        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearNota();
            }
        });

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(CrearNotaActivity.this, R.raw.sonidoclick);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                    }
                });
                mp.start();

                pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
        finish();
        startActivity(pasarPantalla);
    }
}