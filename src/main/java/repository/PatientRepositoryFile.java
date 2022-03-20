package repository;
import model.Patient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PatientRepositoryFile extends PatientRepositoryInMemory {
    private String FileName;
    private static int idGenerator=1;
    public PatientRepositoryFile(String s) {
        this.FileName = s;
        ReadFromFile();
    }

    private void ReadFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(FileName))) {
            //String line = null;
            String line=br.readLine();
            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid value for idGenerator, starting from 0");
            }
            while((line = br.readLine())!=null) {
                String[] el = line.split(";");
                if (el.length != 5) {
                    System.err.println("Line is not valid." + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(el[0]);
                    int d = Integer.parseInt(el[2]);

                    Patient obj = new Patient(id, el[1], d, el[3], el[4]);
                    super.add(obj);
                } catch(NumberFormatException nr) {
                    System.err.println("Id not valid." + el[0]); }
            }
        }
        catch(IOException ex) {
            throw new RepositoryException("Error reading" + ex); }
    }

    private void writeToFile()
    {
        try(PrintWriter pw = new PrintWriter(FileName))
        {
            pw.println(idGenerator);
            for(Patient obj: findAll())
            {
                String str = obj.getId() + ";" + obj.getName() + ";" + obj.getAge() +";" + obj.getCnp() + ";" + obj.getProblem();
                pw.println(str);
            }
        }catch(IOException ex) { throw new RepositoryException("error" + ex);
        }

    }

    @Override
    public Patient add(Patient p){
        p.setId(getNextId());
        super.add(p);
        writeToFile();
        return p;
    }

    private static int getNextId(){
        return idGenerator++;
    }

    @Override
    public void delete(Patient m)
    {
        try  {
            super.delete(m);
            writeToFile();
        }
        catch(RuntimeException ex){throw new RepositoryException(ex);}
    }

    @Override
    public void update(Integer id, Patient p)
    {
        try {
            super.update(id,p);
            writeToFile();
        }
        catch(RuntimeException ex) {throw new RepositoryException(ex);}
    }
}
