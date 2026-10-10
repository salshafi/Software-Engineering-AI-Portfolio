package gradetracker;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class StudentTest {

    // runs some code and returns everything it printed to System.out
    private String capturePrinted(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }
        return out.toString();
    }

    @Test
    void averageOfThreeNormalScores() {
        Student s = new Student("Amina Khan");
        s.setScores(85.5, 90.0, 78.0);
        assertEquals(84.5, s.calculateAverage(), 0.0001);
    }

    @Test
    void averageIsOnlyRoundedWhenPrinted() {
        Student s = new Student("Daniel Lee");
        s.setScores(new double[] {72.0, 88.5, 95.0});

        // calculateAverage() returns the exact value
        assertEquals(255.5 / 3, s.calculateAverage(), 1e-9);

        // displayProfile() rounds it to 2 decimals
        String printed = capturePrinted(s::displayProfile);
        assertTrue(printed.contains("Average: 85.17"));
    }

    @Test
    void scoreAboveMaxIsCappedAndWarns() {
        Student s = new Student("Cap Test");
        String printed = capturePrinted(() -> s.setScores(105.0, 90.0, 80.0));

        assertTrue(printed.contains("too high"));
        assertEquals(90.0, s.calculateAverage(), 0.0001); // (100 + 90 + 80) / 3
    }

    @Test
    void scoreExactlyAtMaxIsNotCapped() {
        Student s = new Student("Edge Test");
        String printed = capturePrinted(() -> s.setScores(100.0, 100.0, 100.0));

        assertFalse(printed.contains("too high"));
        assertEquals(100.0, s.calculateAverage(), 0.0001);
    }

    @Test
    void missingScoreStaysZeroAndCountsInAverage() {
        Student s = new Student("Priya Patel");
        s.setScores(new double[] {70.0, 80.0});
        assertEquals(50.0, s.calculateAverage(), 0.0001); // (70 + 80 + 0) / 3
    }

    @Test
    void longerArrayIsCutOffAtThreeScores() {
        Student s = new Student("Long Array");
        assertDoesNotThrow(() -> s.setScores(new double[] {10.0, 20.0, 30.0, 40.0}));
        assertEquals(20.0, s.calculateAverage(), 0.0001); // 4th score ignored
    }

    @Test
    void scoresNeverSetGivesZeroAverage() {
        Student s = new Student("Sam Lee");
        assertEquals(0.0, s.calculateAverage(), 0.0001);
    }

    @Test
    void arrayAndThreeNumberVersionsGiveSameResult() {
        Student a = new Student("A");
        Student b = new Student("B");
        a.setScores(new double[] {60.0, 70.0, 80.0});
        b.setScores(60.0, 70.0, 80.0);
        assertEquals(a.calculateAverage(), b.calculateAverage(), 0.0001);
    }

    @Test
    void noArgConstructorUsesUnknownName() {
        Student s = new Student();
        String printed = capturePrinted(s::displayProfile);
        assertTrue(printed.contains("Student: Unknown"));
    }

    @Test
    void negativeScoreIsRaisedToMinimum() {
        Student s = new Student("Negative Test");
        String printed = capturePrinted(() -> s.setScores(-20.0, 90.0, 80.0));

        assertTrue(printed.contains("too low"));
        assertEquals(170.0 / 3, s.calculateAverage(), 1e-9); // (0 + 90 + 80) / 3
    }

    @Test
    void nullArrayDoesNotCrashAndKeepsScores() {
        Student s = new Student("Null Test");
        s.setScores(50.0, 60.0, 70.0);

        assertDoesNotThrow(() -> s.setScores((double[]) null));
        assertEquals(60.0, s.calculateAverage(), 0.0001);
    }

    @Test
    void counterGoesUpByOnePerStudent() {
        int before = Student.getTotalStudentsTracked();
        new Student("Counter Test");
        assertEquals(before + 1, Student.getTotalStudentsTracked());
    }

    @Test
    void noArgConstructorIsCountedOnlyOnce() {
        int before = Student.getTotalStudentsTracked();
        new Student();
        assertEquals(before + 1, Student.getTotalStudentsTracked());
    }
}
