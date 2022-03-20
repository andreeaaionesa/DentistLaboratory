package repository;

import model.Appointment;
import model.Patient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AppointmentRepositoryFile extends AppointmentRepositoryInMemory {
    private String FileName;
    private PatientRepo aRepo;
    private static int idGenerator=1;

    public AppointmentRepositoryFile(String s, PatientRepo aRepo) {
        this.FileName = s;
        this.aRepo=aRepo;
        ReadFromFile();
    }

    private static int getNextId(){
        return idGenerator++;
    }

    private void ReadFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(FileName))) {
            String line=br.readLine();
            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid Value for idGenerator, starting from 0");
            }
            while((line = br.readLine())!=null) {
                String[] el = line.split(";");
                if (el.length != 5) {
                    System.err.println("Line is not valid." + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(el[0]);
                    int fId = Integer.parseInt(el[3]);
                    Patient p = aRepo.findById(fId);
                    Appointment o = new Appointment(id, el[1], el[2], p);
                    super.add(o);
                }
                catch(NumberFormatException nr) {
                    System.err.println("Id not valid." + el[0]+ nr); }
            }
        }
        catch(IOException ex) {
            throw new RepositoryException("Error" + ex); }
    }

    private void writeToFile()
    {
        try(PrintWriter pw = new PrintWriter(FileName))
        {
            pw.println(idGenerator);
            for(Appointment obj: findAll())
            {
                String str = obj.getId() + ";" + obj.getDate() + ";" + obj.getHour() +";" + obj.getP();
                pw.println(str);
            }
        }catch(IOException ex) { throw new RepositoryException("error" + ex);
        }
    }

    @Override
    public Appointment add(Appointment m){
        m.setId(getNextId());
        super.add(m);
        writeToFile();
        return m;
    }

    @Override
    public void delete(Appointment m)
    {
        try  {
            super.delete(m);
            writeToFile();
        }
        catch(RuntimeException ex){throw new RepositoryException(ex);}
    }

    @Override
    public void update(Integer id, Appointment m)
    {
        try {
            super.update(id,m);
            writeToFile();
        }
        catch(RuntimeException ex) {throw new RepositoryException(ex);}
    }
}