package net.mv.pors.appointment.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.mv.pors.appointment.dto.AppointmentDto;
import net.mv.pors.appointment.service.AppointmentService;
import net.mv.pors.doctor.domain.Doctor;
import net.mv.pors.doctor.dto.DoctorDto;
import net.mv.pors.doctor.service.DoctorService;
import net.mv.pors.patient.domain.Patient;
import net.mv.pors.patient.dto.PatientDto;
import net.mv.pors.patient.dto.RegisterDoctorDto;
import net.mv.pors.patient.service.PatientService;

@RestController
@RequestMapping(value = "appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentServiceImpl;
	
	@Autowired 
	private DoctorService doctorServiceImpl;
	
	@Autowired
	private PatientService patientServiceImpl;
	
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST, value = "makeAppointment")
	public ResponseEntity<AppointmentDto> makeAppointment(@RequestBody AppointmentDto appointmentDto) throws ParseException {

		ResponseEntity<AppointmentDto> response = null;
		//Set<Doctor> doctors = new HashSet<Doctor>();
		//doctors.add(new Doctor(doctorService.findDoctorById(2l)));
		//patientDtoImpl.setDoctorId(2l);
		//System.out.println(appointmentDto);
		//
			String oldstring = appointmentDto.getAppDate();
			Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(oldstring);
			String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);
			//System.out.println(newstring);
			
			//System.out.println("PLEASE WORK");
			appointmentDto.setAppDate(newstring);
	
		
		 
		
		if (appointmentDto != null ){

			//appointmentDto.setDoctorId(42l); // set doctorId manually
			
			System.out.println(appointmentDto);

			appointmentServiceImpl.makeAppointment(appointmentDto);
			response = new ResponseEntity<AppointmentDto>(appointmentDto, HttpStatus.CREATED);
		}else{
			response = new ResponseEntity<AppointmentDto>(appointmentDto, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST, value = "action")
	public ResponseEntity<AppointmentDto> approveAppointment(@RequestBody AppointmentDto appointmentDto){
		ResponseEntity<AppointmentDto> response = null;
		
		
		System.out.println("AppoimtmentDto in controller: " + appointmentDto);
		System.out.println("Appointment params: " + appointmentDto);
		
		if(appointmentDto != null){
			appointmentServiceImpl.actionOnAppointment(appointmentDto);
			
			//================== send email notification ==========================
			DoctorDto doctorDto = doctorServiceImpl.findDoctorById(appointmentDto.getDoctorId());
			String approvedMessage = "Your appointment has been confirmed.";
			String declinedMessage = "Your appointment has been declined. Please contact Dr. "
									+doctorDto.getFirstName()+" "+doctorDto.getLastName();
			String bodyMessage = null;
			
			PatientDto patientDto = patientServiceImpl.findPatientById(appointmentDto.getPatientId());
			if(appointmentDto.getStatus().equals("accepted")){
				bodyMessage = approvedMessage;
			}else{
				bodyMessage = declinedMessage;
			}
			appointmentServiceImpl.sendEmailNotification(patientDto.getEmail(), "Appointment Notification", bodyMessage);
			//appointmentServiceImpl.sendPreConfiguredMail("Please see details of your appointment");
			//================== end of email service ==============================
			
			response = new ResponseEntity<AppointmentDto>(appointmentDto, HttpStatus.CREATED);
		}else{
			response = new ResponseEntity<AppointmentDto>(appointmentDto, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST, value = "viewAppointments")
	public ResponseEntity<List<AppointmentDto>> listAppointments(@RequestBody PatientDto patientDto) {

		ResponseEntity<List<AppointmentDto>> response = null;
		
		List<AppointmentDto> appointmentsDto = new ArrayList<AppointmentDto>();
		if (patientDto != null ){
			//appointmentDto.setDoctorId(3l); // set doctorId manually
			 appointmentsDto = appointmentServiceImpl.gatherAppointmentsByPatient(patientDto);
			 //System.out.println(appointmentsDto);
			System.out.println(appointmentsDto);
			 
			 for(AppointmentDto element :appointmentsDto){
				/*System.out.println(element.getPatient());
				System.out.println(element.getDoctor());*/
				System.out.println(element.getAppId());
				System.out.println(element.getAppDate());
				System.out.println(element.getReason());
				System.out.println(element.getDoctorId());
				
				element.setDoctor(new Doctor(doctorServiceImpl.findDoctorById(element.getDoctorId())));
				element.setPatient(new Patient(patientServiceImpl.findPatientById(element.getPatientId())));
				System.out.println(element.getPatient());
				System.out.println(element.getDoctor());
			 }
			 
			response = new ResponseEntity<List<AppointmentDto>>(appointmentsDto, HttpStatus.CREATED);
		}else{
			response = new ResponseEntity<List<AppointmentDto>>(appointmentsDto, HttpStatus.BAD_REQUEST);
		}
		
		//System.out.println("Hitting the Restful WebService");

		return response;
	}
	
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST, value = "unregisterDoctor")
	public ResponseEntity<AppointmentDto> unregisterDoctor(@RequestBody AppointmentDto appointmentDto) {

		ResponseEntity<AppointmentDto> response = null;
		if (appointmentDto != null ){
			 appointmentServiceImpl.unregisterPatientFromDoctor(appointmentDto.getUsername(), appointmentDto.getDoctorId());	
			response = new ResponseEntity<AppointmentDto>(appointmentDto, HttpStatus.CREATED);
		}else{
			response = new ResponseEntity<AppointmentDto>(appointmentDto, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
	
	
	
	

}
