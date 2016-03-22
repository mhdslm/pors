package net.mv.pors.patient.service;

import java.util.List;

import net.mv.pors.doctor.domain.Doctor;
import net.mv.pors.doctor.dto.DoctorDto;
import net.mv.pors.patient.domain.Patient;
import net.mv.pors.patient.dto.PatientDto;
import net.mv.pors.user.dto.UserDto;

public interface PatientService {
	
	public boolean storePatientInfo(PatientDto patientDto);
	public List<DoctorDto> listDoctorsForPatient(PatientDto patientDto);
	public PatientDto findPatientById(Long patientId);
	public PatientDto findPatientByUser(UserDto userDto);
	public boolean registerPatientToDoctor (Long userId, Long doctorId);
	public List<DoctorDto> listUnregisterDoctorsForPatient(PatientDto patientDto);
	//public void makeAppointment(AppointmentDto appointmentDto);

}
