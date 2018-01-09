package com.patient.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.patient.model.User;
import com.patient.utility.DBUtility;

@Repository("userDao")
public class UserDao {
	
	private Connection connection;

	public UserDao() {
		connection = DBUtility.getConnection();
	}


	public List listUsers(){
		List<User> users = new ArrayList<User>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from tblUser limit 15");
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("middleName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setDateOfBirth(rs.getString("dateOfBirth"));
				user.setPhoneNo(rs.getString("phoneNo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
}
