package peaksoft.services.serviceImpl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.models.Department;
import peaksoft.models.Doctor;
import peaksoft.models.Hospital;
import peaksoft.repositories.DepartmentRepo;
import peaksoft.repositories.DoctorRepo;
import peaksoft.repositories.HospitalRepo;
import peaksoft.services.DoctorService;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo doctorRepo;
    private final HospitalRepo hospitalRepo;
    private final DepartmentRepo departmentRepo;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Doctor> getAll() {
        return doctorRepo.getAll();
    }

    @Override
    public void save(Long hospitalId,Long departmentId,Long doctorId,Doctor doctor) {
        Hospital hospital = hospitalRepo.findById(hospitalId);
        hospital.addDoctor(doctor);
        doctor.setHospital(hospital);
        doctorRepo.save(doctor);
        Department department = departmentRepo.finById(departmentId);
        Doctor doctor1 = doctorRepo.findById(doctorId);
        department.addDoctor(doctor);
        doctor1.addDepartment(department);
        entityManager.merge(doctor1);

    }

    @Override
    public void assignDoctor(Long doctorId, Long departmentId) {

    }

    @Override
    public void findById(Long id) {

    }

    @Override
    public Doctor update(Doctor doctor) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
