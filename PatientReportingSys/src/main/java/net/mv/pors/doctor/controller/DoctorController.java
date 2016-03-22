package net.mv.pors.doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.mv.pors.appointment.dto.AppointmentDto;
import net.mv.pors.appointment.service.AppointmentService;
import net.mv.pors.doctor.dto.DoctorDto;
import net.mv.pors.doctor.service.DoctorService;

@RestController
@RequestMapping(value = "doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorServiceImpl;

	@Autowired
	private AppointmentService appointmentServiceImpl;

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value = "submitInfo")
	public ResponseEntity<DoctorDto> submitInfo(@RequestBody DoctorDto doctorDto) {

		ResponseEntity<DoctorDto> response = null;
		if (doctorDto != null && doctorDto.getEmail() != null) {

			doctorServiceImpl.storeDoctorInfo(doctorDto);

			response = new ResponseEntity<DoctorDto>(doctorDto, HttpStatus.CREATED);
		} else {
			response = new ResponseEntity<DoctorDto>(doctorDto, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "pendingAppointments")
	public @ResponseBody List<AppointmentDto> findPendingAppointments(@RequestParam Long doctorId) {
		System.out.println(appointmentServiceImpl.findPendingAppointments(doctorId));
		return appointmentServiceImpl.findPendingAppointments(doctorId);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "appointmentsHistory")
	public @ResponseBody List<AppointmentDto> findAppointments(@RequestParam Long doctorId) {
		System.out.println(appointmentServiceImpl.findPendingAppointments(doctorId));
		return appointmentServiceImpl.findAllAppointments(doctorId);
	}

}
