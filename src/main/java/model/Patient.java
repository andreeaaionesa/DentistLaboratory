package model;

public class Patient implements Identifiable <Integer>
{ private int Id,age;
    private String name, cnp, problem;
    public Patient(){
        Id=0;
        this.name =" ";
        this.age =0;
        this.cnp =" ";
        this.problem =" ";
    }
    public Patient(int Id, String name, int age, String cnp, String problem )
    {
        this.Id=Id;
        this.name=name;
        this.age=age;
        this.cnp=cnp;
        this.problem=problem;
    }
    public Patient(String name, int age, String cnp, String problem )
    {
        this.name=name;
        this.age=age;
        this.cnp=cnp;
        this.problem=problem;
    }
    public Integer getId()
    {return Id;}

    public String getName()
    { return name; }

    public Integer getAge()
    {return age;}

    public String getCnp()
    {return cnp;}

    public String getProblem()
    {return problem;}


    public void setId(Integer Id)
    { this.Id=Id; }

    public void setName(String name)
    { this.name=name; }

    public void setAge(Integer age)
    { this.age=age; }

    public void setCnp(String cnp)
    {this.cnp=cnp;}

    public void setProblem(String problem)
    {this.problem=problem;}

    public String toString()
    {String str = Id + "," + name + "," + age + "," + cnp + "," + problem;
        return  str;}
}
