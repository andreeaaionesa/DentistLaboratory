package services;

import model.Appointment;
import model.Patient;
import repository.AppointmentRepo;
import repository.PatientRepo;
import repository.RepositoryException;

import java.util.List;

public class Service{

    public PatientRepo ar;

    private AppointmentRepo br;

    public Service(PatientRepo ar, AppointmentRepo br) {
        this.ar = ar;
        this.br = br;
    }

    public int addP(String name, int age, String height, String experience) throws ServicesException{
        try {
            Patient p = new Patient(name, age, height, experience);
            Patient newf = ar.add(p);
            return newf.getId();
        }catch (RepositoryException ex){
            throw new ServicesException("Error adding patient"+ex);
        }
    }

    public List<Patient> getFByName(String name){
        return ar.findByName(name);
    }

    public List<Appointment> getTeamByRace(String name) {
        return br.filterByP(name);
    }

    public void addAppointment(String date, String hour, Patient p) throws ServicesException{
        try {
            Appointment en = new Appointment(date, hour, p);
            ar.update(p.getId(), p);
            br.add(en);
        }catch (RepositoryException er){
            throw  new ServicesException("Error adding registration"+er);
        }
    }
}

