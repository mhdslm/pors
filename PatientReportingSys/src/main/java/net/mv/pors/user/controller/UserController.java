package net.mv.pors.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.mv.pors.doctor.dto.DoctorDto;
import net.mv.pors.doctor.service.DoctorService;
import net.mv.pors.patient.dto.PatientDto;
import net.mv.pors.patient.service.PatientService;
import net.mv.pors.role.dto.RoleDto;
import net.mv.pors.role.service.RoleService;
import net.mv.pors.user.dto.UserDto;
import net.mv.pors.user.service.UserService;

@RestController
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserService userServiceImpl;
	
	@Autowired
	private PatientService patientServiceImpl;
	
	@Autowired
	private DoctorService doctorServiceImpl;

	@Autowired
	private RoleService roleServiceImpl;

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST, value = "register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {

		ResponseEntity<UserDto> response = null;

		if (userDto != null && userDto.getUsername() != null && userDto.getPassword() != null) {
			userServiceImpl.registerUser(userDto);
			response = new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
		}else{
			response = new ResponseEntity<UserDto>(userDto, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST, value = "authenticate")
	public ResponseEntity<UserDto> authenticateUser(@RequestBody UserDto userDto){
		
		ResponseEntity<UserDto> response = null;
		
		if(userDto != null && userDto.getUsername() != null && userDto.getPassword() != null){
			userDto = userServiceImpl.authenticateUser(userDto);
			if(userDto.isAuthenticated()){
				
				if(userDto.getRoleId() == 1){
					PatientDto patientDto = patientServiceImpl.findPatientByUser(userDto);
					userDto.setClientId(patientDto.getPatientId());
				}else{
					DoctorDto doctorDto = doctorServiceImpl.findDoctorByUser(userDto.getId());
					userDto.setClientId(doctorDto.getDoctorId());
				}
				response = new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
				System.out.println("Successfull Login!!!!");
			}else{
				response = new ResponseEntity<UserDto>(userDto, HttpStatus.CONFLICT);
			}
		}else{
			response = new ResponseEntity<UserDto>(userDto, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value="roles")
	public @ResponseBody List<RoleDto> findRoles(){
		List<RoleDto> roleList = roleServiceImpl.getAllRoles();
		return roleList;
	}
	
}
