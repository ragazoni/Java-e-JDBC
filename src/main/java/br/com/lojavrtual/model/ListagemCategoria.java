package br.com.lojavrtual.model;

import br.com.lojavrtual.dao.CategoriaDAO;
import br.com.lojavrtual.factory.ConexacaoFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ListagemCategoria {
    public static void main(String[] args) throws SQLException {


        try (Connection connection = new ConexacaoFactory().getConnection()) {
            CategoriaDAO categoriaDao = new CategoriaDAO(connection);
            List<Categoria> categoriList = categoriaDao.listarComProduto();
            categoriList.stream().forEach(ct -> {
                System.out.println(ct.getNome());

                for (Produto produto : ct.getProdutos()) {
                    System.out.println(ct.getNome() + " - " + produto.getNome());
                }
            });
        }
    }
}
