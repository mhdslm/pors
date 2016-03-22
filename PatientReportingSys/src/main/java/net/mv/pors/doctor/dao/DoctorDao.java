package net.mv.pors.doctor.dao;

import net.mv.pors.doctor.domain.Doctor;
import net.mv.pors.user.domain.User;

public interface DoctorDao {

	public Doctor retrieveDoctorById(Long doctorId);
	public Doctor retrieveDoctorByUser(Long userId);
	public void saveDoctorInfo(Doctor doctor);
	public void addAppointment(Doctor doctor);

}
