package cl.inacap.evaluacion2_covid.dao;

import java.util.List;

import cl.inacap.evaluacion2_covid.dto.Paciente;

public interface PacientesDAO {

    List<Paciente> getAll();
    Paciente save(Paciente p);

}
