package gradetracker;

/* Student Name: Salman Shafi
 * Program Name: Student.java
 * Date: October 9, 2026
 * Description: Student class that keeps a name and 3 test scores,
 *              and counts how many Student objects have been created.
 */
public class Student {

    // the highest mark a test can have, it never changes
    public static final double MAX_SCORE = 100.0;

    // the lowest mark a test can have, it never changes
    public static final double MIN_SCORE = 0.0;

    // one number shared by all students, it counts how many were created
    private static int totalStudentsTracked = 0;

    // each student has their own name and their own scores
    private String studentName;
    private double[] testScores;

    // makes a student with this name, the 3 scores start at 0.0
    public Student(String studentName) {
        this.studentName = studentName;
        this.testScores = new double[3];
        totalStudentsTracked++;   // counted only here so a student is never counted twice
    }

    // no name given, so call the constructor above with "Unknown"
    public Student() {
        this("Unknown");   // this has to be the first line
    }

    // saves the scores from an array
    // a score over MAX_SCORE is changed to MAX_SCORE
    // a score under MIN_SCORE is changed to MIN_SCORE
    public void setScores(double[] scores) {
        // if no array was given, print a message and keep the old scores
        if (scores == null) {
            System.out.println("No scores given. Scores unchanged.");
            return;
        }

        // only go up to 3 scores so a longer array can't go past the end
        int count = Math.min(scores.length, testScores.length);
        for (int i = 0; i < count; i++) {
            if (scores[i] > MAX_SCORE) {
                System.out.println("Score " + scores[i] + " is too high. Using " + MAX_SCORE);
                testScores[i] = MAX_SCORE;
            } else if (scores[i] < MIN_SCORE) {
                System.out.println("Score " + scores[i] + " is too low. Using " + MIN_SCORE);
                testScores[i] = MIN_SCORE;
            } else {
                testScores[i] = scores[i];
            }
        }
    }

    // same job but takes 3 separate numbers
    // it puts them in an array and calls the method above,
    // so the score checks are only written once
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

    // the counter is private, so other classes use this method to read it
    public static int getTotalStudentsTracked() {
        return totalStudentsTracked;
    }
}