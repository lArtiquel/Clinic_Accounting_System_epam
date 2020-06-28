package com.cas.dao.DoctorRH;

import com.cas.dao.StaffEntityDAO;
import com.cas.entities.Doctor;
import com.cas.interfaces.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorListHandler implements ResultHandler<List<Doctor>> {

    private static DoctorListHandler instance;

    private DoctorListHandler() {}

    public static synchronized DoctorListHandler getInstance() {
        if (instance == null)
            instance = new DoctorListHandler();
        return instance;
    }

    @Override
    public List<Doctor> handle(ResultSet resultSet) throws SQLException {
        final List<Doctor> doctorList = new ArrayList<>();
        while (resultSet.next()) {
            final long id = resultSet.getLong("id");
            final String degree = resultSet.getString("degree");
            final String specialization = resultSet.getString("specialization");
            doctorList.add(new Doctor(id, degree, specialization,
                    StaffEntityDAO.getInstance().getStaffEntityById(id)));
        }
        return doctorList;
    }

}
