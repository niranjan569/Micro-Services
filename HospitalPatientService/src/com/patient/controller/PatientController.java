package com.patient.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.patient.dao.PatientDao;
import com.patient.dao.UserDao;
//import com.hospital.model.Doctor;
//import com.hospital.model.Login;
import com.patient.model.Patient;
import com.patient.model.User;

@RestController
public class PatientController {

	private static final Logger logger = Logger
			.getLogger(PatientController.class);

	@Autowired(required = true)
	private PatientDao patientDao;
	
	@Autowired(required = true)
	private UserDao userdao;
	
//	@RequestMapping(value = "/patientlogin", method = RequestMethod.GET)
//	public String showDoctorLogin(Model model){
//		model.addAttribute("patient", new Patient());
//		model.addAttribute("login",new Login());
//		return "patientlogin";
//	}
	@RequestMapping(value = "/getPatient/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public Patient getPatient(@PathVariable("id") int id) {
		logger.info("Geting patient info");
		Patient patient = patientDao.getPatientDetails(id);
		return  patient;
	}
	
	@RequestMapping(value = "/patients", method = RequestMethod.GET,headers="Accept=application/json")
	public List<User> listPatients()
	{
		List<User> patients = patientDao.listPatients();
		return patients;
	}

	@RequestMapping(value = "/addPatient", method = RequestMethod.POST)
	public void savePatient(@RequestBody Patient patient) {
		logger.info("saving patient info and redirecting to login page");
		patientDao.savePatient(patient);
	}

	@RequestMapping(value = "/deletePatient/{id}", method = RequestMethod.DELETE)
	public void deletePatient(@PathVariable("id") int id) {
		logger.info("Deleteting patient info");
		patientDao.deletePatient(id);
	}
	
}
