package com.examen.DAO;

import java.util.List;

public interface IDAO <T> {

    public T guardar(T t);
    public void eliminar(Long id);
    public T buscar (Long id);
    public List<T> buscarTodos();

}
