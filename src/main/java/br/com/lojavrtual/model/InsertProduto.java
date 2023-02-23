package br.com.lojavrtual.model;

import br.com.lojavrtual.dao.ProdutoDAO;
import br.com.lojavrtual.factory.ConexacaoFactory;

import java.sql.*;
import java.util.List;

public class InsertProduto {

    public static void main(String[] args) throws SQLException {

        Produto produto = new Produto("Geladeira", "freezer inverso");

        try (Connection connection = new ConexacaoFactory().getConnection()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.salvar(produto);
            List<Produto> listaDeProdutos = produtoDAO.listar();
            listaDeProdutos.stream().forEach(lp -> System.out.println(lp));
        }
    }
}
