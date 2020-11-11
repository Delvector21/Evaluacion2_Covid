package cl.inacap.evaluacion2_covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText loginTxt;
    private EditText passTxt;
    private Button ingresarBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.loginTxt = findViewById(R.id.login);
        this.passTxt = findViewById(R.id.password);

        this.ingresarBtn = findViewById(R.id.login_btn);

        this.ingresarBtn.setOnClickListener(view -> {


            List<String> errores = new ArrayList<>();
            String login = loginTxt.getText().toString().trim();
            String pass = passTxt.getText().toString().trim();

            String pwd = "";


            if (login.isEmpty()){

                errores.add("Ingrese un Rut");
                loginTxt.setBackgroundResource(R.drawable.borde_redondo_rojo);


            }else if (!login.matches("^[0-9]{7,8}+-[0-9kK]{1}$")){

                loginTxt.setBackgroundResource(R.drawable.borde_redondo);
                errores.add("Nombre de Usuario Invalido");

            }else{

                switch (login.length()){

                    case 9: pwd = login.substring(3,7);
                        break;
                    case 10: pwd = login.substring(4,8);
                        break;
                }
            }






           if (pass.isEmpty()){
               passTxt.setError("");
               errores.add("ingrese un password");
           }else if (!pass.equals(pwd)){
               errores.add("Password Incorrecto");

           }


            if (errores.isEmpty()){
                startActivity(new Intent(MainActivity.this, PrincipalActivity.class));

            }else{
                StringBuilder error = new StringBuilder();
                for (String e:errores){
                    error.append("-").append(e).append("\n");

                }
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }

        });

    }
}