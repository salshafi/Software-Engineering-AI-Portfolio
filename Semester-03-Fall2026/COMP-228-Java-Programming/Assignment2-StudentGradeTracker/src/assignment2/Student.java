package assignment2;

/* Student Name: Salman Shafi
 * Program Name: Student.java
 * Date: October 9, 2026
 * Description: Student class that keeps a name and 3 test scores,
 *              and counts how many Student objects have been created.
 */
public class Student {

    // highest mark a test can have, it never changes
    public static final double MAX_SCORE = 100.0;

    // one number shared by all students, counts how many were created
    private static int totalStudentsTracked = 0;

    // every student has their own name and scores
    private String studentName;
    private double[] testScores;

    // makes a student with the given name and 3 scores that start at 0.0
    public Student(String studentName) {
        this.studentName = studentName;
        this.testScores = new double[3];
        totalStudentsTracked++;   // only counted here so nobody gets counted twice
    }

    // no name given, so use the constructor above with "Unknown"
    public Student() {
        this("Unknown");   // has to be the first line
    }

    // copies the scores in, anything over MAX_SCORE gets changed to MAX_SCORE
    public void setScores(double[] scores) {
        // stops at 3 so a longer array can't go past the end
        int count = Math.min(scores.length, testScores.length);
        for (int i = 0; i < count; i++) {
            if (scores[i] > MAX_SCORE) {
                System.out.println("Score " + scores[i] + " is too high. Using " + MAX_SCORE);
                testScores[i] = MAX_SCORE;
            } else {
                testScores[i] = scores[i];
            }
        }
    }

    // same thing with 3 separate numbers, puts them in an array and calls
    // the array version so the MAX_SCORE check is only written once
    public void setScores(double s1, double s2, double s3) {
        double[] temp = {s1, s2, s3};
        setScores(temp);
    }

    // adds up the scores and divides by how many there are
    public double calculateAverage() {
        double sum = 0;
        for (double score : testScores) {
            sum += score;
        }
        return sum / testScores.length;
    }

    // prints the name, each score and the average
    public void displayProfile() {
        System.out.println("Student: " + studentName);
        for (int i = 0; i < testScores.length; i++) {
            System.out.printf("  Test %d: %.1f%n", i + 1, testScores[i]);
        }
        System.out.printf("  Average: %.2f%n%n", calculateAverage());
    }

    // main can't see the private counter, so it asks for it with this
    public static int getTotalStudentsTracked() {
        return totalStudentsTracked;
    }
}