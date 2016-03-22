package net.mv.pors.doctor.service;

import net.mv.pors.doctor.dto.DoctorDto;
import net.mv.pors.user.dto.UserDto;

public interface DoctorService {

	public DoctorDto findDoctorByUser(Long id);
	public DoctorDto findDoctorById(Long doctorId);
	public void storeDoctorInfo(DoctorDto doctorDto);
}
