package com.doctor.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.dao.DoctorDao;
import com.doctor.model.Doctor;




@RestController
public class DoctorController {

	private static final Logger logger = Logger
			.getLogger(DoctorController.class);

	@Autowired
	private DoctorDao dao;
	
//	@RequestMapping(value = "/doctorlogin", method = RequestMethod.GET)
//	public String showDoctorLogin(Model model){
//		model.addAttribute("doctor", new Doctor());
//		model.addAttribute("login",new Login());
//		return "doctorlogin";
//	}
//
//	@RequestMapping(value = "/editsamp", method = RequestMethod.GET)
//	public List<Patient> showPatientList(){
//		List<User> userlist =userdao.listUsers();
//		ModelAndView modelAndView =new ModelAndView("editsamp","userlist", userlist);
//		String url = "http://localhost:8080/HospitalPatientService/patients";
//		Restt
//		return modelAndView;
//	}

	
	@RequestMapping(value = "/addDoctor", method = RequestMethod.POST)
	public void saveDoctor(@RequestBody Doctor doctor) {
		logger.info("saving doctor info and redirecting to login page");
		dao.saveDoctor(doctor);
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public void updateDoctor(@RequestBody Doctor doctor,@PathVariable("id") int id) {
		logger.info("saving doctor info and redirecting to login page");
		dao.updateDoctor(doctor,id);
	}

	@RequestMapping(value = "/getDoctor/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public Doctor getDoctor(@PathVariable("id") int id) {
		logger.info("getting doctor info");
		Doctor doctor = dao.getDoctorDetails(id);
		return doctor;
	}
	
	@RequestMapping(value = "/doctors", method = RequestMethod.GET,headers="Accept=application/json")
	public List<Doctor> listDoctors()
	{
		List<Doctor> doctors = dao.listDoctors();
		return doctors;
	}

	@RequestMapping(value = "/deleteDoctor/{id}", method = RequestMethod.DELETE)
	public void deleteDoctor(@PathVariable("id") int id) {
		logger.info("Deleting doctor");
		dao.deleteDoctor(id);
	}
}
