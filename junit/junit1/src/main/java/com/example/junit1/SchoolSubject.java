package com.example.junit1;

import java.util.List;

public class SchoolSubject {
    private static final double Retake_THRESHOLD = 40.0;

    public SchoolSubject() {
    }

    public static boolean isRetakeRecommended(double score) {
        if (score == 0.0) throw new ArithmeticException();

        if (score < Retake_THRESHOLD)
            return false;
        return true;
    }


    public static Subject findSubjectWithWorstDegree(List<Subject> subject) {
        Subject temp = null;


        for (int i = 0; i < subject.size(); i++) {
            for (int j = i + 1; j < subject.size(); j++) {
                if (subject.get(i).degree > subject.get(j).degree) {
                    temp = subject.get(i);
                    subject.set(i, subject.get(j));
                    subject.set(j, temp);

                }
            }
        }

        return subject.stream().findFirst().orElse(null);
    }

    public static double[] getSubjectScores(List<Subject> subject) {
        double[] subjectScores = new double[subject.size()];
        for (int i = 0; i < subjectScores.length; i++) {
            subjectScores[i] = SchoolSubject.calculateDegree(subject.get(i));
        }
        return subjectScores;
    }


    private static double calculateDegree(Subject subject) {
        double studentDegree = subject.getDegree();

        if (studentDegree == 0.0)
            throw new ArithmeticException();

        return Math.round(studentDegree);
    }


}
