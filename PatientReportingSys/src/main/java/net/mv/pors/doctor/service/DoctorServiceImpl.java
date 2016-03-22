package net.mv.pors.doctor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mv.pors.doctor.dao.DoctorDao;
import net.mv.pors.doctor.domain.Doctor;
import net.mv.pors.doctor.dto.DoctorDto;
import net.mv.pors.user.dao.UserDao;
import net.mv.pors.user.domain.User;
import net.mv.pors.user.dto.UserDto;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	DoctorDao doctorDaoImpl;
	
	@Autowired
	UserDao userDaoImpl;
	
	@Override
	public void storeDoctorInfo(DoctorDto doctorDto) {
		System.out.println("doctorDto's username: " + doctorDto.getUsername());
		User user = userDaoImpl.retrieveUserByUsername(doctorDto.getUsername());
		System.out.println("User: " + user);
		Doctor doctor = new Doctor(doctorDto);
		doctor.setUser(user);
		System.out.println("Doctor's user object: " + doctor.getUser());
		doctorDaoImpl.saveDoctorInfo(doctor);
	}

	@Override
	public DoctorDto findDoctorById(Long doctorId) {
		return new DoctorDto(doctorDaoImpl.retrieveDoctorById(doctorId));

	}

	@Override
	public DoctorDto findDoctorByUser(Long id) {
		Doctor doctor = doctorDaoImpl.retrieveDoctorByUser(id);
		return new DoctorDto(doctor);
	}
}
