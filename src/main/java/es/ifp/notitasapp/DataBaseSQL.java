package es.ifp.notitasapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseSQL extends SQLiteOpenHelper {
    protected SQLiteDatabase db;

    public DataBaseSQL(@Nullable Context context) {
        super(context, "notitas", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notas (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nota TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS notas");
    }

    public void crearNota(String notita) {
        db = this.getReadableDatabase();
        db.execSQL("INSERT INTO notas (nota) VALUES ('"+notita+"')");

    }

    public int numeroNotas(){
        int num;
        db = this.getReadableDatabase();
        num = (int) DatabaseUtils.queryNumEntries(db, "notas");
        return num;
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllNotas(){
        Cursor res;
        ArrayList<String> notas = new ArrayList<>();
        db = this.getReadableDatabase();
        res = db.rawQuery("SELECT * FROM notas", null);

        res.moveToFirst();
        while(res.isAfterLast()==false) {
            notas.add(res.getString(res.getColumnIndex("nota")));
            res.moveToNext();
        }
        return notas;
    }

    @SuppressLint("Range")
    public int getIdNota(String nota){
        Cursor res;
        int id = 0;
        db = this.getReadableDatabase();
        res = db.rawQuery("SELECT id FROM notas WHERE nota = '" + nota + "'", null);

        res.moveToFirst();
        while(res.isAfterLast()==false) {
            id = Integer.parseInt(res.getString(res.getColumnIndex("id")));
            res.moveToNext();
        }
        return id;
    }

    public void borrarNota(int idNota){
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notas WHERE id = " + idNota);
    }

    public void deleteAllNotas(){
        db = this.getReadableDatabase();
        db.execSQL("DELETE FROM notas");
    }

    public void close(){
        db.close();
    }
}
