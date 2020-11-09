package cl.inacap.evaluacion2_covid.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import cl.inacap.evaluacion2_covid.dto.Paciente;
import cl.inacap.evaluacion2_covid.helpers.PacientesSQLiteHelper;

public class PacientesDAOSqlite implements PacientesDAO{

    private PacientesSQLiteHelper phelper;




    public PacientesDAOSqlite(Context context) {
        this.phelper = new PacientesSQLiteHelper(context,
                "pacientesDB",
                null,
                1);
    }

    @Override
    public List<Paciente> getAll() {
        SQLiteDatabase reader = this.phelper.getReadableDatabase();
        List<Paciente> pacientes = new ArrayList<>();

        try {
            if (reader != null){
                Cursor c = reader.rawQuery("SELECT id,rut,nombre,apellido,fecha" +
                        ",area,sintomas,temperatura,tos,presion" +
                        " FROM pacientes", null);
                if (c.moveToFirst()){
                    do {
                        Paciente p = new Paciente();
                        p.setId(c.getInt(0));
                        p.setRut(c.getString(1));
                        p.setNombre(c.getString(2));
                        p.setApellido(c.getString(3));
                        p.setFecha(c.getString(4));
                        p.setArea(c.getString(5));
                        if (c.getString(6) == "Si" ){
                            p.setSintoma(true);
                        }else{
                            p.setSintoma(false);
                        }
                        p.setTemperatura(c.getFloat(7));
                        if (c.getString(8) == "Si"){
                            p.setTos(true);
                        }else{
                            p.setTos(false);
                        }
                        p.setPresion(c.getInt(9));
                        pacientes.add(p);

                    }while(c.moveToNext());
                }
                reader.close();
            }
        }catch(Exception ex){
            Log.e("PDAO", ex.toString());
            pacientes = null;

        }

        return pacientes;
    }

    @Override
    public Paciente save(Paciente p) {
        SQLiteDatabase writer = this.phelper.getWritableDatabase();
        String sintoma,tos;
        if (p.isSintoma()){
            sintoma = "Si";
        }else{
            sintoma = "No";
        }
        if (p.isTos()){
            tos = "Si";
        }else{
            tos = "No";
        }

        String sql = String.format("INSERT INTO pacientes(rut,nombre,apellido,fecha,area,sintomas,temperatura,tos,presion)" +
                " VALUES ('%s','%s','%s','%s','%s','%d','%d','%d','%d')"
                ,p.getRut(), p.getNombre(), p.getApellido(), p.getFecha(), p.getArea(),
                sintoma, p.getTemperatura(), tos, p.getPresion());
        writer.execSQL(sql);
        writer.close();

        return p;
    }
}
