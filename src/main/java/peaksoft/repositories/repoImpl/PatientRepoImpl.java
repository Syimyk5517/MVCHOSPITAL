package peaksoft.repositories.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.models.Patient;
import peaksoft.repositories.PatientRepo;

import java.util.List;


@Repository
@RequiredArgsConstructor
@Transactional
public class PatientRepoImpl implements PatientRepo {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Patient> getAllPatient() {
        return entityManager.createQuery("select p from Patient p", Patient.class).getResultList();
    }

    @Override
    public void savePatient(Patient patient) {
        entityManager.persist(patient);
    }

    @Override
    public void finById(Long id) {
        entityManager.find(Patient.class,id);
    }

    @Override
    public void delete(Long id) {
   Patient patient = entityManager.find(Patient.class,id);
   entityManager.remove(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return null;
    }
}
