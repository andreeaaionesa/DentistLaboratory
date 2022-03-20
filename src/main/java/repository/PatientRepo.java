package repository;

import model.Patient;

import java.util.List;

public interface PatientRepo extends Repository<Integer, Patient>{
    List<Patient> findByName(String name);
    List<Patient> findAllpatients();
}
