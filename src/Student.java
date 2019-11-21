public class Student {
    int ID;
    String name;
    double GPA;
    int credits;

    public Student(){}

    public void createStudent(String[] attributes){
        this.ID = Integer.parseInt(attributes[0]);
        this.name = attributes[1];
        this.GPA = Double.parseDouble(attributes[2]);
        this.credits = Integer.parseInt(attributes[3]);
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getGPA() {
        return GPA;
    }

    public int getCredits() {
        return credits;
    }
}
