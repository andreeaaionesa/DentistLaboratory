package repository;

import model.Appointment;

import java.util.List;

public interface AppointmentRepo extends Repository<Integer, Appointment> {
    List<Appointment> filterByP(String name);
}
