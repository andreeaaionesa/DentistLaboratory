package repository;

import model.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class PatientRepositoryInMemory extends AbstractRepository< Integer, Patient> implements PatientRepo {

    @Override
    public List<Patient> findAllpatients() {
        return getAll().stream().collect(Collectors.toList());
    }
    @Override
    public List<Patient> findByName(String name) {
        return getAll().stream().filter(x->x.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
}
