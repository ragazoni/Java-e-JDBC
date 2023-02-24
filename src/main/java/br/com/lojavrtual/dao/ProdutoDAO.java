package br.com.lojavrtual.dao;

import br.com.lojavrtual.model.Categoria;
import br.com.lojavrtual.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO PRODUTOS (nome, descricao) VALUES (?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());

            pstm.execute();
            try (ResultSet resultSet = pstm.getGeneratedKeys()) {
                while (resultSet.next()) {
                    produto.setId(resultSet.getInt(1));
                }
            }

        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTOS";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();
            try (ResultSet resultSet = pstm.getResultSet()) {
                while (resultSet.next()) {
                    Produto produto = new Produto(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getString(3));
                    produtos.add(produto);

                }

            }

        }
        return produtos;
    }

    public List<Produto> buscar(Categoria ct) throws SQLException {
        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTOS WHERE CATEGORIA_ID = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, ct.getId());
            pstm.execute();
            try (ResultSet resultSet = pstm.getResultSet()) {
                while (resultSet.next()) {
                    Produto produto = new Produto(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getString(3));
                    produtos.add(produto);

                }

            }

        }
        return produtos;

    }
}
