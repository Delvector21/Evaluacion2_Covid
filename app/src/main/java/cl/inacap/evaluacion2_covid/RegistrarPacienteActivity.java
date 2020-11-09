package cl.inacap.evaluacion2_covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;


import java.util.Calendar;

import cl.inacap.evaluacion2_covid.dao.PacientesDAO;
import cl.inacap.evaluacion2_covid.dao.PacientesDAOSqlite;

public class RegistrarPacienteActivity extends AppCompatActivity {

    private Toolbar toolbar;
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

        this.setSupportActionBar(this.toolbar);

        ArrayAdapter<String> adapterA = new ArrayAdapter<>(this
                , android.R.layout.simple_spinner_dropdown_item, Areas);
        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AreaTxt.setAdapter(adapterA);

        fechaTxt.setInputType(InputType.TYPE_NULL);

        this.fechaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cldr = Calendar.getInstance();
                DatePickerDialog picker = new DatePickerDialog(RegistrarPacienteActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fechaTxt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, cldr.get(Calendar.YEAR), cldr.get(Calendar.MONTH), cldr.get(Calendar.DAY_OF_MONTH));
                picker.show();
            }
        });



    }
}