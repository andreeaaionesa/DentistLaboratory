package repository;

import model.Appointment;

import java.util.List;
import java.util.stream.Collectors;


public class AppointmentRepositoryInMemory extends AbstractRepository<Integer, Appointment> implements AppointmentRepo {
    @Override
    public List<Appointment> filterByP(String name) {
        return getAll().stream().filter(x->x.getP().getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
}
