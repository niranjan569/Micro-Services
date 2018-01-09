package com.patient.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.patient.model.Patient;
import com.patient.model.User;
import com.patient.utility.DBUtility;

@Repository("patientDao")
public class PatientDao {

	private static final Logger logger = Logger.getLogger(PatientDao.class);

	private Connection connection;

	public PatientDao() {
		connection = DBUtility.getConnection();
	}

	public static Logger getLogger() {
		return logger;
	}
	
	public void savePatient(Patient patient) {
		logger.info("Saving patient info to DB");
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into user(id,firstName,middleName,lastName,email,password,sex,dateOfBirth,phoneNo) values (?,?,?,?,?,?,?,?,?)");
			// Parameters start with 1
			preparedStatement.setInt(1, patient.getId());
			preparedStatement.setString(2, patient.getFirstName());
			preparedStatement.setString(3,patient.getMiddleName());
			preparedStatement.setString(4, patient.getLastName());
			preparedStatement.setString(5, patient.getEmail());
			preparedStatement.setString(6, patient.getPassword());
			preparedStatement.setString(7, patient.getSex());
			preparedStatement.setString(8, patient.getDateOfBirth());
			preparedStatement.setString(9, patient.getPhoneNo());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public List<User> listPatients(){
		List<User> patients = new ArrayList<User>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from user where type='patient'");
			while (rs.next()) {
				Patient patient = new Patient();
				patient.setId(rs.getInt("id"));
				patient.setFirstName(rs.getString("firstName"));
				patient.setMiddleName(rs.getString("middleName"));
				patient.setLastName(rs.getString("lastName"));
				patient.setEmail(rs.getString("email"));
				patient.setPassword(rs.getString("password"));
				patient.setSex(rs.getString("sex"));
				patient.setDateOfBirth(rs.getString("dateOfBirth"));
				patient.setPhoneNo(rs.getString("phoneNo"));
				patient.setMedicationDetails(rs.getString("medicationDetails"));
				patients.add(patient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patients;
	}
	
	public Patient getPatientDetails(int id) {
		logger.info("Getting patient info from DB");
		Patient patient= new Patient(); 
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from user where id=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				patient.setId(rs.getInt("id"));
				patient.setFirstName(rs.getString("firstName"));
				patient.setMiddleName(rs.getString("middleName"));
				patient.setLastName(rs.getString("lastName"));
				patient.setEmail(rs.getString("email"));
				patient.setPassword(rs.getString("password"));
				patient.setSex(rs.getString("sex"));
				patient.setDateOfBirth(rs.getString("dateOfBirth"));
				patient.setPhoneNo(rs.getString("phoneNo"));
				patient.setMedicationDetails(rs.getString("medicationDetails"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patient;
	}

	public void deletePatient(int id) {
		logger.info("Deleting patient info from DB");
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
