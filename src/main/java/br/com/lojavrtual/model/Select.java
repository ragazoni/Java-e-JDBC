package br.com.lojavrtual.model;

import br.com.lojavrtual.factory.ConexacaoFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {

    public static void main(String[] args) throws SQLException {

        ConexacaoFactory conexacaoFactory = new ConexacaoFactory();
        Connection connection = conexacaoFactory.getConnection();

        Statement stm = connection.createStatement();
        stm.execute("SELECT ID, NOME, DESCRICAO FROM PRODUTOS");
        ResultSet rst = stm.getResultSet();

        while (rst.next()){
            Integer id = rst.getInt("ID");
            String nome = rst.getString("NOME");
            String descricao = rst.getString("DESCRICAO");
            System.out.println("id " + id);
            System.out.println("Nome " + nome);
            System.out.println("Descricao " + descricao);
        }

    }


}


