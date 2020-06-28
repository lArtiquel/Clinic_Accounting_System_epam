package com.cas.dao.AppointmentRH;

import com.cas.interfaces.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentCountHandler implements ResultHandler<Integer> {

    private static AppointmentCountHandler instance;

    private AppointmentCountHandler() {}

    public static synchronized AppointmentCountHandler getInstance() {
        if (instance == null)
            instance = new AppointmentCountHandler();
        return instance;
    }

    @Override
    public Integer handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return 0;
        return resultSet.getInt("count");
    }

}
