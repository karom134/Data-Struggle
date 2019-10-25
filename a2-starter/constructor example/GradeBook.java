// Assignment 2, Task 2.1
// Name: Natdanai Angumnuaysiri 6180519
// Collaborators: None
// Time Spent: 6:00 hrs

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Put your name and student id here
 */
public class GradeBook {

    // Do not modify these
    private List<Double> assignmentScores;
    private List<Double> quizScores;
    private double midtermScore;
    private double finalScore;
    private Course course;

    // Creates an empty grade book
    public GradeBook() {
        // IMPLEMENT THIS
        assignmentScores = new ArrayList<>();
        quizScores = new ArrayList<>();
        midtermScore = 0;
        finalScore = 0;
        course = new Course(null);
    }

    // Records the score of a particular assignment
    // Note: First assignment is assignment 1
    public void setAssignmentScore(int assignmentNumber, double score) {
        // IMPLEMENT THIS

        for (int i = 0; i <= assignmentNumber + 1; i++) {
            if (assignmentScores.size() <= assignmentNumber - 1) {
                assignmentScores.add(0.0);
            } else
                assignmentScores.set(assignmentNumber - 1, score);
        }
    }

    // Records the score of a particular quiz
    // Note: First quiz is quiz 1
    public void setQuizScore(int quizNumber, double score) {
        // IMPLEMENT THIS
        for (int i = 0; i <= quizNumber + 1; i++) {
            if (quizScores.size() <= quizNumber - 1) {
                quizScores.add(0.0);
            } else
                quizScores.set(quizNumber - 1, score);

        }
    }

    // Returns average score after dropping <numDrop> lowest scores.
    //
    // You must assume that the total number of assignments/quizzes is the
    // maximum of the assignment ids that has been recorded, and
    // assume scores of 0.0 for unrecorded scores.
    //
    // For example, if only scores of quiz 1 and 3 are recorded,
    // you should assume that there are 3 quizzes and quiz 2 score is 0.0.
    public double getAverageScore(List<Double> scores, int numDrop) {
        // IMPLEMENT THIS
        List<Double> GetList = new ArrayList<>(scores);
        Collections.sort(GetList);
        // System.out.println("before" + scores.toString());
        for (int i = 0; i < numDrop; i++) {
            if (GetList.isEmpty()) {
                return 0.0;
            } else
                GetList.remove(0);
        }
        // System.out.println("after" + scores.toString());
        if (GetList.isEmpty()) {
            return 0.0;
        }
        double Sum = GetList.stream().mapToDouble(Double::doubleValue).sum();
        return Sum / GetList.size();
    }

    public double getAverageQuizScore(int numDrop) {
        // IMPLEMENT THIS
        return getAverageScore(quizScores, numDrop);
    }

    public double getAverageAssignmentScore(int numDrop) {
        // IMPLEMENT THIS
        return getAverageScore(assignmentScores, numDrop);
    }

    // Returns the term weighted average according to the following formula:
    // term_avg = (avg_assn_score * assn_weight% + avg_quiz_score * quiz_weight%
    // + midterm_score * midterm_weight% + final_score * final_weight%) / 100
    // If the grading scheme is not define, return -1
    public double getTermAverage(int numAssignmentDrop, int numQuizDrop) {
        // IMPLEMENT THIS
        double AvgA = getAverageAssignmentScore(numAssignmentDrop);
        double AvdQ = getAverageQuizScore(numQuizDrop);
        double Overall = (AvgA * course.getAssignmentWeightPercentage() + AvdQ * course.getQuizWeightPercentage()
                + midtermScore * course.getMidtermWeightPercentage() + finalScore * course.getFinalWeightPercentage())
                / 100;
        return Overall;
    }

    public void printRawScores() {
        // IMPLEMENT THIS
        System.out.println("Assignment Scores:");
        for (int A = 0; A < assignmentScores.size(); A++) {
            System.out.println((A + 1) + " : " + assignmentScores.get(A));
        }
        System.out.println("Quiz Scores:");
        for (int Q = 0; Q < quizScores.size(); Q++) {
            System.out.println((Q + 1) + " : " + quizScores.get(Q));
        }
        System.out.println("Midterm Score:" + midtermScore);
        System.out.println("Final Score:" + finalScore);
    }

    // Add any missing methods here
    public void setMidtermScore(double m) {
        midtermScore = m;
    }

    public void setFinalScore(double f) {
        finalScore = f;
    }

    public void setCourse(String name, double a, double q, double m, double f) {
        new Course(name, a, q, m, f);
    }

    // Do not modify this
    public static void main(String[] args) {
        GradeBook book = new GradeBook();

        book.setAssignmentScore(1, 80);
        book.setAssignmentScore(3, 88);
        book.setAssignmentScore(2, 90);

        book.setQuizScore(1, 100);
        book.setQuizScore(2, 80);

        book.setMidtermScore(100);
        book.setFinalScore(100);

        book.printRawScores();

        System.out.println("Avg Assignment Score (No drop): " + book.getAverageAssignmentScore(0));
        System.out.println("Avg Assignment Score (drop 1): " + book.getAverageAssignmentScore(1));
        System.out.println("Avg Assignment Score (drop 2): " + book.getAverageAssignmentScore(2));
        System.out.println("Avg Assignment Score (drop 3): " + book.getAverageAssignmentScore(3));
        System.out.println("Avg Assignment Score (drop 4): " + book.getAverageAssignmentScore(4));

        System.out.println("Avg Quiz Score (No drop): " + book.getAverageQuizScore(0));
        System.out.println("Avg Quiz Score (drop 1): " + book.getAverageQuizScore(1));
        System.out.println("Avg Quiz Score (drop 2): " + book.getAverageQuizScore(2));

        book.setAssignmentScore(9, 80);
        book.printRawScores();
        System.out.println("Avg Assignment Score (No drop): " + book.getAverageAssignmentScore(0));

    }

}
