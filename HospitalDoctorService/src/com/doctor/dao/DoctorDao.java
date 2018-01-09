package com.doctor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doctor.model.Doctor;
import com.doctor.utility.DBUtility;


@Repository
public class DoctorDao {
	
	private Connection connection;

	public DoctorDao() {
		connection = DBUtility.getConnection();
	}


	public void saveDoctor(Doctor doctor) {
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into user(firstName,middleName,lastName,email,password,sex,dateOfBirth,phoneNo,type,specialization) values (?,?,?,?,?,?,?,?,?,?)");
			// Parameters start with 1
			preparedStatement.setString(1, doctor.getFirstName());
			preparedStatement.setString(2,doctor.getMiddleName());
			preparedStatement.setString(3, doctor.getLastName());
			preparedStatement.setString(4, doctor.getEmail());
			preparedStatement.setString(5, doctor.getPassword());
			preparedStatement.setString(6, doctor.getSex());
			preparedStatement.setString(7, doctor.getDateOfBirth());
			preparedStatement.setString(8, doctor.getPhoneNo());
			preparedStatement.setString(9, "doctor");
			preparedStatement.setString(10, doctor.getSpecialization());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateDoctor(Doctor doctor,int id) {
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("update user set id=?,firstName=?,middleName=?,lastName=?,email=?,password=?,sex=?,dateOfBirth=?,phoneNo=? where id="+id);
			// Parameters start with 1
			preparedStatement.setInt(1, doctor.getId());
			preparedStatement.setString(2, doctor.getFirstName());
			preparedStatement.setString(3,doctor.getMiddleName());
			preparedStatement.setString(4, doctor.getLastName());
			preparedStatement.setString(5, doctor.getEmail());
			preparedStatement.setString(6, doctor.getPassword());
			preparedStatement.setString(7, doctor.getSex());
			preparedStatement.setString(8, doctor.getDateOfBirth());
			preparedStatement.setString(9, doctor.getPhoneNo());
			preparedStatement.setString(10, "doctor");
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Doctor getDoctorDetails(int id)
	{
		Doctor doctor= new Doctor(); 
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from user where id=? and type='doctor'");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				doctor.setId(rs.getInt("id"));
				doctor.setFirstName(rs.getString("firstName"));
				doctor.setMiddleName(rs.getString("middleName"));
				doctor.setLastName(rs.getString("lastName"));
				doctor.setEmail(rs.getString("email"));
				doctor.setPassword(rs.getString("password"));
				doctor.setSex(rs.getString("sex"));
				doctor.setDateOfBirth(rs.getString("dateOfBirth"));
				doctor.setPhoneNo(rs.getString("phoneNo"));
				doctor.setSpecialization(rs.getString("specialization"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctor;
	}
	
	public List<Doctor> listDoctors(){
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from user where type='doctor'");
			while (rs.next()) {
				Doctor doctor = new Doctor();
				doctor.setId(rs.getInt("id"));
				doctor.setFirstName(rs.getString("firstName"));
				doctor.setMiddleName(rs.getString("middleName"));
				doctor.setLastName(rs.getString("lastName"));
				doctor.setEmail(rs.getString("email"));
				doctor.setPassword(rs.getString("password"));
				doctor.setSex(rs.getString("sex"));
				doctor.setDateOfBirth(rs.getString("dateOfBirth"));
				doctor.setPhoneNo(rs.getString("phoneNo"));
				doctor.setSpecialization(rs.getString("specialization"));
				doctors.add(doctor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return doctors;
	}
	
	public void deleteDoctor(int id)
	{
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from user where id=?");
			// Parameters start with 1
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
