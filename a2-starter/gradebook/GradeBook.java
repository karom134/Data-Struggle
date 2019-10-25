import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

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
        while (assignmentNumber - 1 >= assignmentScores.size()) {
            assignmentScores.add(0.0);
        }
        assignmentScores.set(assignmentNumber - 1, score);
    }

    // Records the score of a particular quiz
    // Note: First quiz is quiz 1
    public void setQuizScore(int quizNumber, double score) {
        // IMPLEMENT THIS
        while (quizNumber - 1 >= quizScores.size()) {
            quizScores.add(0.0);
        }
        quizScores.set(quizNumber - 1, score);
    }

    public void setMidtermScore(double score) {
        midtermScore = score;
    }

    public void setFinalScore(double score) {
        finalScore = score;
    }

    // Returns average score after dropping <numDrop> lowest scores.
    //
    // You must assume that the total number of assignments/quizzes is the
    // maximum of the assignment ids that has been recorded, and
    // assume scores of 0.0 for unrecorded scores.
    //
    // For example, if only scores of quiz 1 and 3 are recorded,
    // you should assume that there are 3 quizzes and quiz 2 score is 0.0.
    private double getAverageScore(List<Double> scores, int numDrop) {
        // IMPLEMENT THIS
        if (scores.isEmpty()) {
            return 0.0;
        }
        if (scores.size() < numDrop) {
            return 0.0;
        }
        List<Double> tmp = new ArrayList<>(scores);
        Collections.sort(tmp);
        Double sum = 0.0;
        if (numDrop == 0) {
            for (int i = 0; i < tmp.size(); i++) {
                sum = sum + tmp.get(i);
            }
        } else {
            for (int idx = numDrop; idx < tmp.size(); idx++) {
                sum = sum + tmp.get(idx);
            }
            if ((tmp.size() - numDrop) == 0) {
                return 0.0;
            }
        }
        return sum / (tmp.size() - numDrop);
    }

    public double getAverageQuizScore(int numDrop) {
        // IMPLEMENT THIS
        return getAverageScore(quizScores, numDrop);
    }

    public double getAverageAssignmentScore(int numDrop) {
        // IMPLEMENT THIS
        return getAverageScore(assignmentScores, numDrop);
    }

    public void setcourse(String name, double a, double b, double c, double d) {
        this.course=new Course(name, a, b, c, d);
    }

    // Returns the term weighted average according to the following formula:
    // term_avg = (avg_assn_score * assn_weight% + avg_quiz_score * quiz_weight%
    // + midterm_score * midterm_weight% + final_score * final_weight%) / 100
    // If the grading scheme is not define, return -1
    public double getTermAverage(int numAssignmentDrop, int numQuizDrop) {
        // IMPLEMENT THIS
        List<Double> grading = course.getgrading();
        Double A = getAverageQuizScore(numQuizDrop) * grading.get(1)
                + getAverageAssignmentScore(numAssignmentDrop) * grading.get(0) + midtermScore * grading.get(2)
                + finalScore * grading.get(3);
        return A;
    }

    public void printRawScores() {

        // IMPLEMENT THIS
        System.out.println("Assignment score:");
        for (int idx = 0; idx < assignmentScores.size(); idx++) {
            System.out.println((idx + 1) + ":" + assignmentScores.get(idx));
        }
        System.out.println("Quiz score:");
        for (int idx = 0; idx < quizScores.size(); idx++) {
            System.out.println((idx + 1) + ":" + quizScores.get(idx));
        }
        System.out.println("Midterm score:" + midtermScore);
        System.out.println("Final score:" + finalScore);

    }

    // Add any missing methods here

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
