package com.cas.dao;

import com.cas.dao.StaffEntityRH.StaffEntityHandler;
import com.cas.entities.StaffEntity;
import com.cas.db.Executor;

import java.sql.SQLException;

public class StaffEntityDAO {

    private static StaffEntityDAO instance;
    private final Executor executor = Executor.getInstance();
    private final StaffEntityHandler staffEntityHandler = StaffEntityHandler.getInstance();

    private StaffEntityDAO() {}

    public static synchronized StaffEntityDAO getInstance() {
        if (instance == null)
            instance = new StaffEntityDAO();
        return instance;
    }

    // DB scripts
    private static final String Get_StaffEntity_By_Id =
            "SELECT * FROM staff_entity WHERE id = ?";

    private static final String Create_Staff_Entity =
            "INSERT INTO staff_entity(id, salary) VALUES(?,?)";

    private static final String Remove_By_Id =
            "DELETE FROM staff_entity WHERE id = ?";

    public StaffEntity getStaffEntityById(Long id) throws SQLException {
        return executor.executeQuery(Get_StaffEntity_By_Id, staffEntityHandler, id);
    }

    public void createStaffEntity(Long id, Double salary) throws SQLException {
        executor.executeUpdate(Create_Staff_Entity, id, salary);
    }

    public void removeById(Long id) throws SQLException {
        executor.executeUpdate(Remove_By_Id, id);
        // cascade removal from `users` table
        UserDAO.getInstance().removeById(id);
    }

}
