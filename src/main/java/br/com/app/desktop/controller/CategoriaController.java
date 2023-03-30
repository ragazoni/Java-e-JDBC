package br.com.app.desktop.controller;

import br.com.app.desktop.dao.CategoriaDAO;
import br.com.app.desktop.factory.ConexacaoFactory;
import br.com.app.desktop.model.Categoria;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CategoriaController {

    private CategoriaDAO categoriaDAO;

    public CategoriaController() {
        Connection connection = new ConexacaoFactory().recuperarConexao();
        this.categoriaDAO = new CategoriaDAO(connection);
    }
    public List<Categoria> listar() {
        return this.categoriaDAO.listar();
    }
}
