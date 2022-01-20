package com.example.junit1;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class SchoolSubjectTest {
    private String environment = "dev";

    @BeforeAll
    static void beforeAll() {
        System.out.println("Database connection for only once before all tests");
    }

    @Test
    @DisplayName("Optional Name for isRetakeRecommended")
    void isRetakeRecommended() {
        assertTrue(SchoolSubject.isRetakeRecommended(43));
    }

    @RepeatedTest(value = 5, name = RepeatedTest.SHORT_DISPLAY_NAME)
    void isRetakeRecommendedWithRandoms() {
        //Meaningfull when random  values or multithreads are available
        double value = Math.random() * 50 + 40;
        assertTrue(SchoolSubject.isRetakeRecommended(value)
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {70.0, 80.0, 90.0})
    void isRetakeRecommendedWithParameters(Double myscores) {
        assertTrue(SchoolSubject.isRetakeRecommended(myscores)
        );
    }

    @ParameterizedTest(name = "SubjectName={0},Degree{1},StudentId{2}")
    @CsvFileSource(resources = "/subjects.csv", numLinesToSkip = 1)
    void isRetakeRecommendedWithParametersFromCSV(String subjectname, Double degree) {
        assertTrue(SchoolSubject.isRetakeRecommended(degree)
        );
    }

    //Everything is organized and related methods are at the same class when we run the tests
    @Nested
    class WorstDegree {
        @Test
        void should_ReturnSubjectWorstDegree() {
            List<Subject> list = new ArrayList();
            list.add(new Subject("Literature", 11.0, 1, 3333, "Ayse"));
            list.add(new Subject("Literature", 10.0, 2, 3333, "Ayse"));
            list.add(new Subject("Algebra", 40.0, 1, 3333, "Ayse"));
            list.add(new Subject("Algebra", 70.0, 2, 3333, "Ayse"));
            Subject subject = SchoolSubject.findSubjectWithWorstDegree(list);
            //assertEquals(1,subject.degree);
            //This doesnt check all conditions when failed so to check out all conditions
            assertAll(
                    () -> assertEquals(2, subject.getSemester()) /*,
                    () -> assertEquals(1,subject.getDegree()),
                    () -> assertEquals("Aysem",subject.getStudentname())*/

            );
        }

        @Test
        void should_ReturnSubjectWorstDegreeWhenListHas10000Elements() {
            assumeTrue(SchoolSubjectTest.this.environment.equals("prod"));
            //Test will be skipped
            List<Subject> list = new ArrayList();
            for (int i = 0; i < 10000; i++)
                list.add(new Subject("Literature", Math.random() * 60, 1, 3333, "Ayse"));
            Executable executable = () -> SchoolSubject.findSubjectWithWorstDegree(list);
            assertTimeout(Duration.ofSeconds(1), executable);
            // assertTimeout(Duration.ofMillis(1),executable);
        }


        @Test
        void should_ReturnSubjectWorstDegreeNull() {
            List<Subject> list = new ArrayList();
            Subject subject = SchoolSubject.findSubjectWithWorstDegree(list);
            assertNull(subject);
        }

    }


    @Test
    void should_ThrowArithmeticExceptionWhenSoreZero() {
        //given
        double score = 0.0;
        //when
        // boolean is changed to executable and lambda expression is used
        Executable executable = () -> SchoolSubject.isRetakeRecommended(score);
        //then
        assertThrows(ArithmeticException.class, executable);
    }

    @Test
    @Disabled
    void thismethodisnotready() {
        System.out.println("I am not ready - Ignore Me");
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void thisMethodisnotforWindows() {
        System.out.println("I am not ready - Ignore Me");
    }

    @Test
    void should_ReturnCorrectSubjectScores() {
        //given
        List<Subject> list = new ArrayList();
        list.add(new Subject("Literature", 11.0, 1, 3333, "Ayse"));
        list.add(new Subject("Literature", 10.0, 2, 3333, "Ayse"));
        list.add(new Subject("Algebra", 40.0, 1, 3333, "Ayse"));
        list.add(new Subject("Algebra", 70.0, 2, 3333, "Ayse"));
        double[] expected = {11.0, 10.0, 40.0, 70.0};
        //when
        double[] subjectScores = SchoolSubject.getSubjectScores(list);

        //then  two different objects values although are same Also orders of expected values in array should be the same
        //assertEquals(expected,subjectScores);
        assertArrayEquals(expected, subjectScores);
    }

    @AfterAll
    static void afterAll() {

        System.out.println("Database connection close or close server etc. Runs after all tests");
    }
/*
   @AfterEach
    void aftereach() {
        System.out.println("aftereach  ");
    }

    @BeforeEach
    void beforeeach() {
        System.out.println("beforeEach can be used 1 or more");
    }
*/

}