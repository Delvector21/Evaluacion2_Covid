package cl.inacap.evaluacion2_covid.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

import cl.inacap.evaluacion2_covid.R;
import cl.inacap.evaluacion2_covid.dto.Paciente;

public class PacientesArrayAdapter extends ArrayAdapter<Paciente> {

    private Activity activity;
    private List<Paciente> pacientes;


    public PacientesArrayAdapter(@NonNull Context context, int resource, @NonNull List<Paciente> objects) {
        super(context, resource, objects);
        this.activity = (Activity) context;
        this.pacientes = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.activity.getLayoutInflater();
        View fila = inflater.inflate(R.layout.paciente_list,null,true);

        TextView nombreTv = fila.findViewById(R.id.nombreVw);
        TextView rutTv = fila.findViewById(R.id.rutVw);
        TextView apellidoTv = fila.findViewById(R.id.apellidoVw);
        TextView fechaTv = fila.findViewById(R.id.fechaVw);
        ImageView covidTv = fila.findViewById(R.id.covidVw);


            if (pacientes.get(position).isSintoma()) {
                covidTv.setImageResource(R.drawable.logo_lv);
            }

        nombreTv.setText(pacientes.get(position).getNombre());
        rutTv.setText(pacientes.get(position).getRut());
        apellidoTv.setText(pacientes.get(position).getApellido());
        fechaTv.setText(pacientes.get(position).getFecha());




        return fila;
    }
}
