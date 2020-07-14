public class Academic {
    private String name;
    private String email;
    private String college;
    private String major;
    private double gpa;
    private String q1;
    private String tips;
    private String rigor;
    private String year;


    public Academic(String name, String email, String college, String major, double gpa, String q1, String tips, String rigor, String year) {
        this.college = college;
        this.email = email;
        this.gpa = gpa;
        this.major = major;
        this.name = name;
        this.q1 = q1;
        this.rigor = rigor;
        this.tips = tips;
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public String getEmail() {
        return email;
    }

    public double getGpa() {
        return gpa;
    }

    public String getCollege() {
        return college;
    }

    public String getMajor() {
        return major;
    }

    public String getName() {
        return name;
    }

    public String getQ1() {
        return q1;
    }

    public String getRigor() {
        return rigor;
    }

    public String getTips() {
        return tips;
    }
}
