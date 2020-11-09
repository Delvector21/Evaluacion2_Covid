package cl.inacap.evaluacion2_covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText login;
    private EditText pass;
    private Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.login = findViewById(R.id.login);
        this.pass = findViewById(R.id.password);
        this.ingresar = findViewById(R.id.login_btn);

        this.ingresar.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,
                    PrincipalActivity.class));

            /*List<String> errores = new ArrayList<>();
            String loginTxt = login.getText().toString().trim();

            if (!login.getText().toString().matches("^[0-9]+-[0-9kK]{1}$")){
                login.setError("Ingrese un Rut");
                errores.add("Ingrese un Rut valido");

            }
            if (errores.isEmpty()){

            }*/
        });

    }
}