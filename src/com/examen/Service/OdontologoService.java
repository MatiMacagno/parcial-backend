package com.examen.Service;

import com.examen.DAO.IDAO;
import com.examen.model.Odontologo;

import java.util.List;

public class OdontologoService {

    public OdontologoService(IDAO<Odontologo> odontologoIDAO) {
        this.odontologoIDAO = odontologoIDAO;
    }

    public OdontologoService() {
    }

    private IDAO<Odontologo> odontologoIDAO;

    public IDAO<Odontologo> getOdontologoIDAO() {
        return odontologoIDAO;
    }

    public void setOdontologoIDAO(IDAO<Odontologo> odontologoIDAO) {
        this.odontologoIDAO = odontologoIDAO;
    }

    public Odontologo guardarOdontologo(Odontologo o) {
        return odontologoIDAO.guardar(o);
    }

    public void eliminarOdontologo(Long id) {
        odontologoIDAO.eliminar(id);
    }

    public Odontologo buscarOdontologo(Long id) {
        return odontologoIDAO.buscar(id);
    }

    public List<Odontologo> buscarTodos() {
        return odontologoIDAO.buscarTodos();
    }

}
