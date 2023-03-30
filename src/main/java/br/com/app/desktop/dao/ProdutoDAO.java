package br.com.app.desktop.dao;

import br.com.app.desktop.model.Categoria;
import br.com.app.desktop.model.Produto;
import com.mysql.cj.xdevapi.SqlDataResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) {
        try {
            String sql = "INSERT INTO PRODUTOS (NOME, DESCRICAO) VALUES (?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, produto.getNome());
                pstm.setString(2, produto.getDescricao());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        produto.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void salvarComCategoria(Produto produto) {
        try {
            String sql = "INSERT INTO PRODUTOS (NOME, DESCRICAO, CATEGORIA_ID) VALUES (?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, produto.getNome());
                pstm.setString(2, produto.getDescricao());
                pstm.setInt(3, produto.getCategoriaId());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        produto.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Produto> listar() {
        try {
            List<Produto> produtos = new ArrayList<Produto>();
            String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTOS";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                trasformarResultSetEmProduto(produtos, pstm);
            }
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Produto> buscar(Categoria ct) {
        try {
            List<Produto> produtos = new ArrayList<Produto>();
            String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTOS WHERE CATEGORIA_ID = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, ct.getId());
                pstm.execute();

                trasformarResultSetEmProduto(produtos, pstm);
            }
            return produtos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deletar(Integer id) {
        try {
            try (PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTOS WHERE ID = ?")) {
                stm.setInt(1, id);
                stm.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void alterar(String nome, String descricao, Integer id) {
        try {
            try (PreparedStatement stm = connection
                    .prepareStatement("UPDATE PRODUTOS P SET P.NOME = ?, P.DESCRICAO = ? WHERE ID = ?")) {
                stm.setString(1, nome);
                stm.setString(2, descricao);
                stm.setInt(3, id);
                stm.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) {
        try {
            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

                    produtos.add(produto);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
