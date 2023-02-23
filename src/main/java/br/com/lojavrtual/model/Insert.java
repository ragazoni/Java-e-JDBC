package br.com.lojavrtual.model;

import br.com.lojavrtual.factory.ConexacaoFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {

    public static void main(String[] args) throws SQLException {

        ConexacaoFactory conexacaoFactory = new ConexacaoFactory();
        Connection connection = conexacaoFactory.getConnection();

        Statement stm = connection.createStatement();
        stm.execute("INSERT INTO PRODUTOS (nome, descricao) VALUES ('Mouse', 'Mouse sem fio')"
                , Statement.RETURN_GENERATED_KEYS);

        ResultSet rst = stm.getGeneratedKeys();
        while (rst.next()) {
            Integer id = rst.getInt(1);
            System.out.println("O id criado foi: " + id);
        }
        rst.close();
        stm.close();
        connection.close();
    }
}

