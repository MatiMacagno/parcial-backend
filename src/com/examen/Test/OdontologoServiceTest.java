package com.examen.Test;

import com.examen.DAO.impl.OdontologoDAOH2;
import com.examen.Service.OdontologoService;
import com.examen.model.Odontologo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class OdontologoServiceTest {

    private static final OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());

    Odontologo odontologo = new Odontologo(123L, "Matias", "Macagno");
    Odontologo odontologo1 = new Odontologo(456L, "Paula", "Ramirez");
    Odontologo odontologo2 = new Odontologo(789L, "Cristian", "Castro");

    @Test
    public void guardarTest() {
        odontologoService.guardarOdontologo(odontologo);
        odontologoService.guardarOdontologo(odontologo2);
    }

    @Test
    public void buscarTodosTest() {
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        odontologos.add(odontologo);
        odontologos.add(odontologo1);
        odontologos.add(odontologo2);
        Assertions.assertTrue(odontologos.size() > 0);
        System.out.println(odontologos);
    }

}
