package com.cas.db;


import com.cas.interfaces.ResultHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Executor {
    private static Executor instance;
    private static final DataSource mysqlDataSource = JdbcConnector.getDataSource();

    private Executor() {
    }

    public static synchronized Executor getInstance() {
        if (instance == null)
            instance = new Executor();
        return instance;
    }

    private void setArgs(PreparedStatement stmt, Object... args) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            if (args[i].getClass() == Integer.class)
                stmt.setInt(i + 1, (Integer)args[i]);
            else if (args[i].getClass() == Long.class)
                stmt.setLong(i + 1, (Long)args[i]);
            else if (args[i].getClass() == Date.class)
                stmt.setDate(i + 1, (Date)args[i]);
            else if (args[i].getClass() == Double.class)
                stmt.setDouble(i + 1, (Double)args[i]);
            else if (args[i].getClass() == String.class)
                stmt.setString(i + 1, (String)args[i]);
            else if (args[i].getClass() == Boolean.class)
                stmt.setBoolean(i + 1, (Boolean)args[i]);
        }
    }

    public int executeUpdate(final String update, Object... args) throws SQLException {
        try (final Connection con = mysqlDataSource.getConnection();
             final PreparedStatement stmt = con.prepareStatement(update)) {
            setArgs(stmt, args);
            return stmt.executeUpdate();
        }
    }

    public <T> T executeQuery(final String query, final ResultHandler<T> handler, Object... args) throws SQLException {
        try (final Connection con = mysqlDataSource.getConnection();
             final PreparedStatement stmt = con.prepareStatement(query)) {
            setArgs(stmt, args);
            return handler.handle(stmt.executeQuery());
        }
    }
}
