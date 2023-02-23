package br.com.lojavrtual.model;

import br.com.lojavrtual.factory.ConexacaoFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {

    public static void main(String[] args) throws SQLException {

        ConexacaoFactory conexacaoFactory = new ConexacaoFactory();

        Connection connection = conexacaoFactory.getConnection();

        Statement stm = connection.createStatement();
        stm.execute("DELETE FROM PRODUTOS WHERE NOME = 'mouse'");

        Integer linhasAfetadas = stm.getUpdateCount();
        System.out.println("linhas deletadas " + linhasAfetadas );
    }
}
