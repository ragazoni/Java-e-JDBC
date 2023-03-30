package br.com.app.desktop.controller;

import br.com.app.desktop.dao.ProdutoDAO;
import br.com.app.desktop.factory.ConexacaoFactory;
import br.com.app.desktop.model.Produto;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    private ProdutoDAO produtoDAO;
    public ProdutoController() {
        Connection connection = new ConexacaoFactory().recuperarConexao();
        this.produtoDAO = new ProdutoDAO(connection);
    }

    public void deletar(Integer id) {
        this.produtoDAO.deletar(id);
    }

    public void salvar(Produto produto) {
        this.produtoDAO.salvar(produto);
    }

    public List<Produto> listar() {
       return this.produtoDAO.listar();
    }

    public void alterar(String nome, String descricao, Integer id) {
        this.produtoDAO.alterar(nome,descricao,id);
    }
}
