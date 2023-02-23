package br.com.lojavrtual.model;

import br.com.lojavrtual.factory.ConexacaoFactory;

import java.sql.*;

public class InsertPrepamentStament {

    public static void main(String[] args) throws SQLException {

        try (Connection connection = new ConexacaoFactory().getConnection()) {

            connection.setAutoCommit(false);

            PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTOS (nome, descricao) " +
                    "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            ResultSet rst = adicionarItem("Smart Tv", "Tela infinita 4K", stm);

            connection.commit();
            rst.close();
            stm.close();

        }
    }

    private static ResultSet adicionarItem(String nome, String descricao, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, descricao);
        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();
        while (rst.next()) {
            Integer id = rst.getInt(1);
            System.out.println("O id criado foi: " + id);
        }
        return rst;
    }
}
