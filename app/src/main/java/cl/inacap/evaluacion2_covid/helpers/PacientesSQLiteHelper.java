package cl.inacap.evaluacion2_covid.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PacientesSQLiteHelper extends SQLiteOpenHelper {

    private final String sqlCreate = "CREATE TABLE " +
            "pacientes(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +
            ",rut TEXT" +
            ",nombre TEXT" +
            ",apellido TEXT" +
            ",fecha TEXT" +
            ",area TEXT" +
            ",sintomas TEXT" +
            ",temperatura REAL" +
            ",tos TEXT" +
            ",presion INTEGER)";


    public PacientesSQLiteHelper(@Nullable Context context,
                                 @Nullable String name,
                                 @Nullable SQLiteDatabase.CursorFactory factory,
                                 int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase dbase) {
        dbase.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase dbase, int oldVersion, int newVersion) {
        dbase.execSQL("DROP TABLE IF EXISTS pacientes");
        dbase.execSQL(sqlCreate);

    }
}
