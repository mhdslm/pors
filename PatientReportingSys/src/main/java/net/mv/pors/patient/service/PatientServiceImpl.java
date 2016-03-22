package net.mv.pors.patient.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mv.pors.doctor.dao.DoctorDao;
import net.mv.pors.doctor.domain.Doctor;
import net.mv.pors.doctor.dto.DoctorDto;
import net.mv.pors.patient.dao.PatientDao;
import net.mv.pors.patient.domain.Patient;
import net.mv.pors.patient.dto.PatientDto;
import net.mv.pors.status.dao.StatusDao;
import net.mv.pors.user.dao.UserDao;
import net.mv.pors.user.domain.User;
import net.mv.pors.user.dto.UserDto;

@Service
public class PatientServiceImpl implements PatientService{
	
	@Autowired 
	private PatientDao patientDaoImpl;
	
	@Autowired
	private UserDao userDaoImpl;
	
	@Autowired
	private StatusDao statusDaoImpl;
	
	@Autowired
	private DoctorDao doctorDaoImpl;

	@Override
	public boolean storePatientInfo(PatientDto patientDto) {
		
		User user = userDaoImpl.retrieveUserByUsername(patientDto.getUsername());
		//Set<Doctor> doctors = new HashSet<Doctor>();
		//doctors.add(doctorDao.retrieveDoctorById(2l));
		Patient patient = new Patient (patientDto);
		patient.setUser(user);
		//patient.setDoctors(doctors);
		
		return patientDaoImpl.savePatientInfo(patient); 
	}

	@Override
	public List<DoctorDto> listDoctorsForPatient(PatientDto patientDto) {
		
		User user = userDaoImpl.retrieveUserByUsername(patientDto.getUsername());
		
		Patient patient = patientDaoImpl.retrievePatientByUser(user);
		
		
		List<Doctor> doctors = new ArrayList<Doctor>();
		List<DoctorDto> doctorsDto = new ArrayList<DoctorDto>();
		
		doctors = patientDaoImpl.retrieveDoctorByPatient(patient);
		
		for(Doctor element:doctors){
			DoctorDto doctorDto = new DoctorDto(element);
			doctorsDto.add(doctorDto);
		}
		
		
		return doctorsDto;
	}
	
	
	@Override
	public List<DoctorDto> listUnregisterDoctorsForPatient(PatientDto patientDto) {
		
		User user = userDaoImpl.retrieveUserByUsername(patientDto.getUsername());
		
		Patient patient = patientDaoImpl.retrievePatientByUser(user);
		
		
		List<Doctor> doctors = new ArrayList<Doctor>();
		List<DoctorDto> doctorsDto = new ArrayList<DoctorDto>();
		
		doctors = patientDaoImpl.unregisteredDoctorByPatient(patient);
		
		for(Doctor element:doctors){
			DoctorDto doctorDto = new DoctorDto(element);
			doctorsDto.add(doctorDto);
		}
		
		
		return doctorsDto;
	}
	

	@Override
	public PatientDto findPatientById(Long patientId) {
		
		return new PatientDto (patientDaoImpl.retrievePatientById(patientId));
	}
	
	public PatientDto findPatientByUser(UserDto userDto) {
		Patient patient = patientDaoImpl.retrievePatientByUserId(userDto.getId());
		return new PatientDto(patient);
	}

	@Override
	public boolean registerPatientToDoctor(Long userId, Long doctorId) {
		
		System.out.println("User Id" + userId);
		Patient patient = patientDaoImpl.retrievePatientByUserId(userId);
		
		Doctor doctor = doctorDaoImpl.retrieveDoctorById(doctorId);
		
		patientDaoImpl.registerToDoctor(patient, doctor);
		
		return false;
	}

	

}
