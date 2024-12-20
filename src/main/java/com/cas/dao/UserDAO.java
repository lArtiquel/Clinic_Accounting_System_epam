package com.cas.dao;

import com.cas.dao.UserRH.IsUserExistsHandler;
import com.cas.dao.UserRH.UserHandler;
import com.cas.entities.User;
import com.cas.db.Executor;
import com.cas.interfaces.ResultHandler;

import java.sql.SQLException;

public class UserDAO {

	private static UserDAO instance;
	private final Executor executor = Executor.getInstance();
	private final ResultHandler<User> userHandler = UserHandler.getInstance();
	private final ResultHandler<Boolean> isUserExistsHandler = IsUserExistsHandler.getInstance();

	private UserDAO() {}

	public static synchronized UserDAO getInstance() {
		if (instance == null)
			instance = new UserDAO();
		return instance;
	}

	private static final String Get_By_Username_And_Password =
			"SELECT * FROM users WHERE username = ? AND `password` = ?";

	private static final String Get_By_Username =
			"SELECT * FROM users WHERE username = ?";

	private static final String Is_New_Username_Available =
			"SELECT * FROM users WHERE username = ? AND username <> ?";

	private static final String Get_By_Id =
			"SELECT * FROM users WHERE id = ?";

	private static final String Create_User =
			"INSERT INTO users(username, `password`, `role`) VALUES(?,?,?)";

	private static final String Remove_By_Id =
			"DELETE FROM users WHERE id = ?";

	private static final String Update_Username_And_Password =
			"UPDATE users SET username = ?, `password` = ? WHERE id = ?";

	private static final String Update_Password =
			"UPDATE users SET `password` = ? WHERE id = ?";

	public User getByUsernameAndPassword(String username, String password) throws SQLException {
		return executor.executeQuery(Get_By_Username_And_Password, userHandler, username, password);
	}

	public User getByUsername(String username) throws SQLException {
		return executor.executeQuery(Get_By_Username, userHandler, username);
	}

	public Boolean existsByUsername(String username) throws SQLException {
		return executor.executeQuery(Get_By_Username, isUserExistsHandler, username);
	}

	public Boolean isNewUsernameAvailable(String newUsername, String oldUsername)
																			throws SQLException {
		return executor.executeQuery(Is_New_Username_Available, isUserExistsHandler, newUsername, oldUsername);
	}

	public User getById(long id) throws SQLException {
		return executor.executeQuery(Get_By_Id, userHandler, id);
	}

	public void createUser(String username, String password, String role) throws SQLException {
		executor.executeUpdate(Create_User, username, password, role);
	}

	public void removeById(Long id) throws SQLException {
		executor.executeUpdate(Remove_By_Id, id);
	}

	public void updateUsernameAndPassword(Long id, String username, String password) throws SQLException {
		executor.executeUpdate(Update_Username_And_Password, username, password, id);
	}

	public void updatePassword(Long id, String password) throws SQLException {
		executor.executeUpdate(Update_Password, password, id);
	}

}
