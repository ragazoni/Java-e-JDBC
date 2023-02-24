package br.com.lojavrtual.dao;

import br.com.lojavrtual.model.Categoria;
import br.com.lojavrtual.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT ID, NOME FROM CATEGORIA";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();
            try (ResultSet resultSet = pstm.getResultSet()) {
                while (resultSet.next()) {
                    Categoria categoria = new Categoria(resultSet.getInt(1), resultSet.getString(2));
                    categorias.add(categoria);
                }

            }
        }
        return categorias;
    }

    public List<Categoria> listarComProduto() throws SQLException {
        Categoria ultima = null;
        List<Categoria> categorias = new ArrayList<>();

        String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN " +
                "PRODUTOS P ON C.ID = P.CATEGORIA_ID";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();
            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    if (ultima == null || !ultima.getNome().equals(rst.getString(2))) {
                        Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
                        categorias.add(categoria);
                        ultima = categoria;
                    }
                    Produto produto =
                            new Produto(rst.getInt(3), rst.getString(4), rst.getString(5));
                    ultima.adicionar(produto);
                }
            }
            return categorias;
        }
    }
}

