package domain;

import java.util.List;
import domain.exceptions.EnrollmentRulesViolationException;

public class EnrollmentRequest {
    private Student student;
    private List<CourseSectionExamDate> courses;


    public Student getStudent() {
        return student;
    }

    public EnrollmentRequest(Student student, List<CourseSectionExamDate> courses) {
        this.courses = courses;
        this.student = student;

    }

    public List<CourseSectionExamDate> getCourses() {
        return courses;
    }
    public int getUnitsRequested() {
        return courses.stream().mapToInt(course -> course.getCourse().getUnits()).sum();
    }

    public void checkUnitsRequested() throws EnrollmentRulesViolationException {
        if ((this.getStudent().getGpa() < 12 && this.getUnitsRequested() > 14) ||
                (this.getStudent().getGpa() < 16 && this.getUnitsRequested() > 16) ||
                (this.getUnitsRequested() > 20))
            throw new EnrollmentRulesViolationException(String.format("Number of units (%d) requested does not match GPA of %f",
                    this.getUnitsRequested(), this.getStudent().getGpa()));
    }

}


