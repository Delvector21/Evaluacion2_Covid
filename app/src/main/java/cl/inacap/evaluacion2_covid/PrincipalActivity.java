package cl.inacap.evaluacion2_covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cl.inacap.evaluacion2_covid.adapters.PacientesArrayAdapter;
import cl.inacap.evaluacion2_covid.dao.PacientesDAO;
import cl.inacap.evaluacion2_covid.dao.PacientesDAOSqlite;
import cl.inacap.evaluacion2_covid.dto.Paciente;

public class PrincipalActivity extends AppCompatActivity {

    private FloatingActionButton agregarBtn;
    private ListView pacientesLv;
    private List<Paciente> pacientes;
    private PacientesArrayAdapter adapter;
    private PacientesDAO pdao = new PacientesDAOSqlite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.agregarBtn = findViewById(R.id.agregar_btn);
        this.agregarBtn.setOnClickListener(view -> {
            startActivity(new Intent(PrincipalActivity.this,
                    RegistrarPacienteActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        pacientes = pdao.getAll();
        adapter = new PacientesArrayAdapter(this,
                R.layout.paciente_list, pacientes);
        pacientesLv = findViewById(R.id.pacientes_lv);
        pacientesLv.setAdapter(adapter);
        /*pacientesLv.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(Principal.this,
                    VerPacienteActivity.class);
            Paciente actual = pacientes.get(i);
            intent.putExtra("paciente", actual);
        });*/
    }
}