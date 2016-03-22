package net.mv.pors.patient.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.mv.pors.doctor.domain.Doctor;
import net.mv.pors.doctor.dto.DoctorDto;
import net.mv.pors.doctor.service.DoctorService;
import net.mv.pors.patient.domain.Patient;
import net.mv.pors.patient.dto.PatientDto;
import net.mv.pors.patient.dto.RegisterDoctorDto;
import net.mv.pors.patient.service.PatientService;

@RestController
@RequestMapping(value = "patient")
public class PatientController {
	
	@Autowired
	private PatientService patientServiceImpl;
	
	@Autowired
	private DoctorService doctorServiceImpl;
	

	
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST, value = "submitInfo")
	public ResponseEntity<PatientDto> storePatientInfo(@RequestBody PatientDto patientDto) {

		ResponseEntity<PatientDto> response = null;
		//Set<Doctor> doctors = new HashSet<Doctor>();
		//doctors.add(new Doctor(doctorService.findDoctorById(2l)));
		//patientDtoImpl.setDoctorId(2l);
		if (patientDto != null ){
			patientServiceImpl.storePatientInfo(patientDto);
			response = new ResponseEntity<PatientDto>(patientDto, HttpStatus.CREATED);
		}else{
			response = new ResponseEntity<PatientDto>(patientDto, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
	
	
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST, value = "listDoctors")
	public ResponseEntity<List<DoctorDto>> listDoctors(@RequestBody PatientDto patientDto) {

		ResponseEntity<List<DoctorDto>> response = null;
		//Set<Doctor> doctors = new HashSet<Doctor>();
		//doctors.add(new Doctor(doctorService.findDoctorById(2l)));
		//patientDtoImpl.setDoctorId(2l);
		List<DoctorDto> doctors = new ArrayList<DoctorDto>();
		if (patientDto != null ){
			//patientServiceImpl.storePatientInfo(patientDto);
			doctors = patientServiceImpl.listDoctorsForPatient(patientDto);
			
			response = new ResponseEntity<List<DoctorDto>>(doctors, HttpStatus.CREATED);
		}else{
			response = new ResponseEntity<List<DoctorDto>>(doctors, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
	
	
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST, value = "listUnregisteredDoctors")
	public ResponseEntity<List<DoctorDto>> listUnregisteredDoctors(@RequestBody PatientDto patientDto) {

		ResponseEntity<List<DoctorDto>> response = null;
		//Set<Doctor> doctors = new HashSet<Doctor>();
		//doctors.add(new Doctor(doctorService.findDoctorById(2l)));
		//patientDtoImpl.setDoctorId(2l);
		List<DoctorDto> doctors = new ArrayList<DoctorDto>();
		if (patientDto != null ){
			//patientServiceImpl.storePatientInfo(patientDto);
			doctors = patientServiceImpl.listUnregisterDoctorsForPatient(patientDto);
			
			response = new ResponseEntity<List<DoctorDto>>(doctors, HttpStatus.CREATED);
		}else{
			response = new ResponseEntity<List<DoctorDto>>(doctors, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
	
	
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST, value = "registerDoctor")
	public ResponseEntity<RegisterDoctorDto> registerDoctor(@RequestBody RegisterDoctorDto registerDoctorDto) {

		ResponseEntity<RegisterDoctorDto> response = null;
		PatientDto patientDto = new PatientDto();
		DoctorDto doctorDto = new DoctorDto();
		//Set<Doctor> doctors = new HashSet<Doctor>();
		//doctors.add(new Doctor(doctorService.findDoctorById(2l)));
		//patientDtoImpl.setDoctorId(2l);
		//List<DoctorDto> doctors = new ArrayList<DoctorDto>();
		if (registerDoctorDto != null ){
			/*patientDto =	patientServiceImpl.findPatientById(registerDoctorDto.getId());
			doctorDto = doctorServiceImpl.findDoctorById(registerDoctorDto.getRegistered());*/
			//doctors = patientServiceImpl.listDoctorsForPatient(patientDto);
			
			patientServiceImpl.registerPatientToDoctor(registerDoctorDto.getId(), registerDoctorDto.getRegistered());
			//System.out.println(registerDoctorDto);
			
			response = new ResponseEntity<RegisterDoctorDto>(registerDoctorDto, HttpStatus.CREATED);
		}else{
			response = new ResponseEntity<RegisterDoctorDto>(registerDoctorDto, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
	
	
	
	
	

}
