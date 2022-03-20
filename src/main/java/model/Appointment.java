package model;

public class Appointment implements Identifiable <Integer>
{ private int Id;
    private String date, hour;
    private Patient p;

    public  Appointment()
    {
        this.Id=0;
        this.date=" ";
        this.hour=" ";
        this.p=new Patient();
    }

    public Appointment(int Id, String date, String hour,Patient p)
    {
        this.Id=Id;
        this.date=date;
        this.hour=hour;
        this.p=p;
    }

    public Appointment(String date, String hour, Patient p)
    {

        this.date=date;
        this.hour=hour;
        this.p=p;
    }

    public Integer getId()
    { return Id; }

    public String getDate()
    { return date; }

    public String getHour()
    { return hour;}



    public Patient getP()
    {return p;}

    public void setId(Integer Id)
    {this.Id=Id;}

    public void setDate(String date)
    {this.date=date;}

    public void setHour(String hour)
    {this.hour=hour;}

    public void setP(Patient p)
    {this.p=p;}

    public String toString(){
        String str = Id + "," + date +","+ hour + "," + p;
        return str;

    }







}
