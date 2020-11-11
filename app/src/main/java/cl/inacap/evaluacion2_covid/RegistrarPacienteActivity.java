package cl.inacap.evaluacion2_covid;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import cl.inacap.evaluacion2_covid.dao.PacientesDAO;
import cl.inacap.evaluacion2_covid.dao.PacientesDAOSqlite;
import cl.inacap.evaluacion2_covid.dto.Paciente;

public class RegistrarPacienteActivity extends AppCompatActivity {


    private EditText rutTxt;
    private EditText nombreTxt;
    private EditText apellidoTxt;
    private EditText fechaTxt;
    private Spinner AreaTxt;
    private Switch sintomaTxt;
    private EditText TempTxt;
    private Switch tosTxt;
    private EditText presionTxt;
    private Button registrarBtn;
    private PacientesDAO pdao = new PacientesDAOSqlite(this);
    String[] Areas = { "Atención a Publico", "Otro" };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_paciente);
        this.rutTxt = findViewById(R.id.rut_rg);
        this.nombreTxt = findViewById(R.id.nombre_rg);
        this.apellidoTxt = findViewById(R.id.apellido_rg);
        this.AreaTxt = findViewById(R.id.area_rg);
        this.sintomaTxt = findViewById(R.id.sintomas_rg);
        this.fechaTxt = findViewById(R.id.fecha_rg);
        this.TempTxt = findViewById(R.id.temp_rg);
        this.tosTxt = findViewById(R.id.tos_rg);
        this.presionTxt = findViewById(R.id.presion_rg);
        this.registrarBtn = findViewById(R.id.ingresar_btn);

        this.setSupportActionBar(findViewById(R.id.toolbar));
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        ArrayAdapter<String> adapterA = new ArrayAdapter<>(this
                , android.R.layout.simple_spinner_dropdown_item, Areas);
        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AreaTxt.setAdapter(adapterA);

        fechaTxt.setInputType(InputType.TYPE_NULL);

        this.fechaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cldr = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

                DatePickerDialog picker = new DatePickerDialog(RegistrarPacienteActivity.this,
                        (view, year, monthOfYear, dayOfMonth) ->
                                fechaTxt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year),
                        cldr.get(Calendar.YEAR), cldr.get(Calendar.MONTH), cldr.get(Calendar.DAY_OF_MONTH));
                picker.getDatePicker().setMinDate(System.currentTimeMillis());
                picker.show();
            }
        });

        this.registrarBtn.setOnClickListener(view -> {

            List<String> errores = new ArrayList<String>();

            String rut = rutTxt.getText().toString().trim();
            String nombre = nombreTxt.getText().toString().trim();
            String apellido = apellidoTxt.getText().toString().trim();
            String fecha = fechaTxt.getText().toString().trim();
            String area = (String) AreaTxt.getSelectedItem();
            boolean sintoma = sintomaTxt.isChecked();
            String temp = TempTxt.getText().toString().trim();
            String presion = presionTxt.getText().toString().trim();
            boolean tos = tosTxt.isChecked();






            if (rut.isEmpty()){
                errores.add("Ingrese un rut ");
            }else if (!rut.matches("^[0-9]+-[0-9kK]{1}$")){
                errores.add("ingrese un rut valido");
            }
            if (nombre.isEmpty()){
                errores.add("Ingrese un nombre");
            }
            if (apellido.isEmpty()){
                errores.add("Ingrese un apellido");
            }
            if (fecha.isEmpty()){
                errores.add("Ingrese una fecha");
            }
            if (area.isEmpty()){
                errores.add("Ingrese un Area de Trabajo");
            }
            Float tempFloat = null;
            if (temp.isEmpty()) {
                errores.add("ingrese una temperatura");
            }else{
                try {
                    tempFloat = Float.parseFloat(temp);
                    if (tempFloat <= 20){
                        errores.add("la Temp° debe ser mayor o igual a 20°C");
                    }
                } catch (Exception ex) {
                    errores.add("ingrese un valor numerico");

                }
            }
            int presionInt = 0;

            if (presion.isEmpty()) {
                errores.add("ingrese una presion");
            }else{
                try {
                    presionInt =Integer.parseInt(presion);
                } catch (Exception ex) {
                    errores.add("ingrese un valor numerico entero");

                }
            }
            if (errores.isEmpty()){
                Paciente p = new Paciente();
                p.setRut(rut);
                p.setNombre(nombre);
                p.setApellido(apellido);
                p.setFecha(fecha);
                p.setArea(area);
                p.setSintoma(sintoma);
                p.setTemperatura(tempFloat);
                p.setTos(tos);
                p.setPresion(presionInt);
                pdao.save(p);
                startActivity(new Intent(RegistrarPacienteActivity.this,
                        PrincipalActivity.class));
                Toast.makeText(RegistrarPacienteActivity.this, "Paciente Ingresado", Toast.LENGTH_SHORT).show();


            }else{
                StringBuilder error = new StringBuilder();
                for (String e:errores){
                    error.append("-").append(e).append("\n");

                }
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
            }


        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}