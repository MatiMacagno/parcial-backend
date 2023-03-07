package com.examen.DAO.impl;

import com.examen.DAO.IDAO;
import com.examen.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDAO<Odontologo> {

    private static final String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/db_Odontologia;INIT=RUNSCRIPT FROM 'create.sql'";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";
    private Logger logger = Logger.getLogger(OdontologoDAOH2.class);


    @Override
    public Odontologo guardar(Odontologo odontologo) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO odontologos (numeroMatricula, nombre, apellido) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()){
                odontologo.setId(keys.getLong(1));
            }

            preparedStatement.close();
            logger.info("Se guardo con exito");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.info(throwables);
        }


        return odontologo;

    }

    @Override
    public void eliminar(Long id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM odontologos WHERE id = ?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info("Se elimino con exito");

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.info(throwables);
        }

    }

    @Override
    public Odontologo buscar(Long id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT FROM odontologos WHERE id = ?");
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()){

                Long matriculaOdontologo = result.getLong("numeroMatricula");
                String nombreOdontologo = result.getString("nombre");
                String apellidoOdontologo = result.getString("apellido");

                odontologo = new Odontologo(matriculaOdontologo, nombreOdontologo, apellidoOdontologo);

            }

            preparedStatement.close();
            logger.info("Encontrado con exito");

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.info(throwables);
        }


        return odontologo;

    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> listaOdontologos = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM odontologos");
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long matriculaOdontologo = result.getLong("numeroMatricula");
                String nombreOdontologo = result.getString("nombre");
                String apellidoOdontologo = result.getString("apellido");

                Odontologo odontologo = new Odontologo(matriculaOdontologo, nombreOdontologo, apellidoOdontologo);
                listaOdontologos.add(odontologo);
            }

            preparedStatement.close();
            logger.info("Encontrados con exito");

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.info(throwables);
        }


        return listaOdontologos;
    }

}

