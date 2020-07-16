package com.cas.dao.StaffEntityRH;

import com.cas.dao.PatientDAO;
import com.cas.entities.StaffEntity;
import com.cas.interfaces.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffEntityHandler implements ResultHandler<StaffEntity> {

    private static StaffEntityHandler instance;

    private StaffEntityHandler() {}

    public static synchronized StaffEntityHandler getInstance() {
        if (instance == null)
            instance = new StaffEntityHandler();
        return instance;
    }

    @Override
    public StaffEntity handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return null;
        final Long id = resultSet.getLong("id");
        final Double degree = resultSet.getDouble("salary");
        return new StaffEntity(id, degree,
                PatientDAO.getInstance().getPatientById(id));
    }

}
