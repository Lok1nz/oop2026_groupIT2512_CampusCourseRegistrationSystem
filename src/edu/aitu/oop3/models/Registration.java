public class Registration {
    private int id;
    private int studentId;
    private int courseId;
    private Timestamp registrationDate;

    public Registration(int id, int studentId, int courseId, Timestamp registrationDate) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.registrationDate = registrationDate;
    }

    // Геттеры
    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    public Timestamp getRegistrationDate() { return registrationDate; }

    @Override
    public String toString() {
        return "Registration #" + id + ": Student ID " + studentId + " -> Course ID " + courseId;
    }
}