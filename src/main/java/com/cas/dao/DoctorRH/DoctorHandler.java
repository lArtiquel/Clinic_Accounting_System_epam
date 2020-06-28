package com.cas.dao.DoctorRH;

import com.cas.dao.StaffEntityDAO;
import com.cas.entities.Doctor;
import com.cas.interfaces.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorHandler implements ResultHandler<Doctor> {

    private static DoctorHandler instance;

    private DoctorHandler() {}

    public static synchronized DoctorHandler getInstance() {
        if (instance == null)
            instance = new DoctorHandler();
        return instance;
    }

    @Override
    public Doctor handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return null;
        final long id = resultSet.getLong("id");
        final String degree = resultSet.getString("degree");
        final String specialization = resultSet.getString("specialization");
        return new Doctor(id, degree, specialization,
                StaffEntityDAO.getInstance().getStaffEntityById(id));
    }

}
