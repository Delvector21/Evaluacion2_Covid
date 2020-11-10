package cl.inacap.evaluacion2_covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import cl.inacap.evaluacion2_covid.dto.Paciente;

public class VerPacienteActivity extends AppCompatActivity {

    private TextView rutTxt;
    private Toolbar toolbar;
    private TextView nombreTxt;
    private TextView apellidoTxt;
    private TextView areaTxt;
    private TextView fechaTxt;
    private TextView sintomasTxt;
    private TextView tosTxt;
    private TextView tempTxt;
    private TextView presionTxt;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_paciente);
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.rutTxt = findViewById(R.id.rut_txt);
        this.nombreTxt = findViewById(R.id.nombre_txt);

        this.areaTxt = findViewById(R.id.area_txt);
        this.fechaTxt = findViewById(R.id.fecha_txt);
        this.sintomasTxt = findViewById(R.id.sintomas_txt);
        this.tosTxt = findViewById(R.id.tos_txt);
        this.tempTxt = findViewById(R.id.temp_txt);
        this.presionTxt = findViewById(R.id.presion_txt);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        if(getIntent()!= null){
            Paciente p = (Paciente) getIntent().getSerializableExtra("paciente");
            this.rutTxt.setText(p.getRut());
            this.nombreTxt.setText(p.getNombre() + " " + p.getApellido());

            this.areaTxt.setText(p.getArea());
            this.fechaTxt.setText(p.getFecha());
            if (p.isSintoma()) {
                this.sintomasTxt.setText("Si");
            }else{
                this.sintomasTxt.setText("No");
            }
            if (p.isTos()) {
                this.tosTxt.setText("Si");
            }else{
                this.tosTxt.setText("No");
            }
            this.tempTxt.setText(p.getTemperatura()+ "°C");
            this.presionTxt.setText("Presión" + p.getPresion());
        }

    }
}