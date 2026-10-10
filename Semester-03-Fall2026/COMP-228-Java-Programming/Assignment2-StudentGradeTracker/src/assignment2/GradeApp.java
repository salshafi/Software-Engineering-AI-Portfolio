package assignment2;

/* Student Name: Salman Shafi
 * Program Name: GradeApp.java
 * Date: October 9, 2026
 * Description: Driver class that creates Student objects, sets their scores
 *              two different ways, then prints their profiles and the total count.
 */
public class GradeApp {

    // prints the title at the top of the report
    public static void printReportHeader() {
        System.out.println("=================================");
        System.out.println("  --- JAVA TECH STUDENT SYSTEM ---");
        System.out.println("=================================");
    }

    public static void main(String[] args) {
        printReportHeader();

        // student 1 uses the array version of setScores
        Student student1 = new Student("Amina Khan");
        double[] marks = {85.5, 90.0, 78.0};
        student1.setScores(marks);

        // student 2 uses the version with 3 separate numbers
        Student student2 = new Student("Daniel Lee");
        student2.setScores(72.0, 88.5, 95.0);

        student1.displayProfile();
        student2.displayProfile();

        System.out.println("Total students tracked: " + Student.getTotalStudentsTracked());

        // extra tests for the edge cases
        System.out.println();
        System.out.println("--- Extra tests ---");

        // no name given, and one score over 100
        Student student3 = new Student();
        student3.setScores(105.0, 90.0, 80.0);
        student3.displayProfile();

        // array with only 2 scores, the 3rd one should stay 0.0
        Student student4 = new Student("Priya Patel");
        double[] shortArray = {70.0, 80.0};
        student4.setScores(shortArray);
        student4.displayProfile();

        // scores never set, so the average should be 0.00
        Student student5 = new Student("Sam Lee");
        student5.displayProfile();

        System.out.println("Total students tracked: " + Student.getTotalStudentsTracked());
    }
}