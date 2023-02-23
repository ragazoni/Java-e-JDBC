package br.com.lojavrtual.factory;

import br.com.lojavrtual.constants.Constants;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexacaoFactory {

    private DataSource dataSources;

    public ConexacaoFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(System.getenv(Constants.URL));
        comboPooledDataSource.setUser(System.getenv(Constants.USER));
        comboPooledDataSource.setPassword(System.getenv(Constants.PASSWORD));
        //"jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
        //"root"
        //"jholdri010203"

        this.dataSources = comboPooledDataSource;

    }

    public Connection getConnection() throws SQLException {
       return this.dataSources.getConnection();
    }
}

