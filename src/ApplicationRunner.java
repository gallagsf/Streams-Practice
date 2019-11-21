import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;

public class ApplicationRunner {
    public static void main(String[] args) throws IOException {
        ArrayList<Student> studentsList = new ArrayList<>();
        Path pathToFile = Paths.get("students.txt");


        //BufferedReader code inspired from code at https://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html
        BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII);

        String line = br.readLine();
        line = br.readLine();

        while (line != null){
            String[] attributes = line.split(",");
            Student newStudent = new Student();
            newStudent.createStudent(attributes);
            studentsList.add(newStudent);
            line = br.readLine();
        }

        System.out.println("GPA > 3.7\n------------------------------\n");

        studentsList.stream()
                .filter(student -> student.getGPA() > 3.7)
                .forEach(student -> System.out.println(student.getName() + ": " + student.getGPA()));

        System.out.println("\nGPA == 4\n------------------------------\n");

        studentsList.stream()
                .filter(student -> student.getGPA() == 4)
                .forEach(student -> System.out.println(student.getName() + ": " + student.getGPA()));

        System.out.println("\nMax credits of 4.0 students\n------------------------------\n");

        Student max = studentsList.stream()
                .filter(student -> student.getGPA() == 4)
                .max(Comparator.comparing(Student::getCredits)).get();

        System.out.println(max.getCredits());

        System.out.println("\nAverage GPA\n------------------------------\n");

        double avg = studentsList.stream()
                .mapToDouble(student -> student.getGPA()).average().getAsDouble();

        System.out.printf("%.2f\n", avg);

        System.out.println("\nMin GPA\n------------------------------\n");

        double min = studentsList.stream()
                .mapToDouble(student -> student.getGPA()).min().getAsDouble();

        System.out.println(min);

        System.out.println("\nCredit hour summary statistics\n------------------------------\n");

        DoubleSummaryStatistics summary = studentsList.stream().mapToDouble(student -> student.getCredits()).summaryStatistics();

        System.out.printf("Average: %.2f" + "\n", summary.getAverage());
        System.out.println("\nMax: " + summary.getMax() + "\n");
        System.out.println("Min: " + summary.getMin() + "\n");
        System.out.println("Count: " + summary.getCount() + "\n");
        System.out.println("Sum: " + summary.getSum() + "\n");

    }
}
