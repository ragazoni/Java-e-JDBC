package br.com.app.desktop.factory;

import br.com.app.desktop.constants.Constants;
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
        this.dataSources = comboPooledDataSource;

    }

    public Connection recuperarConexao() {
        try {
            return this.dataSources.getConnection();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

