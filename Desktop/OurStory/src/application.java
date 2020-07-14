public class application {
    private String name;
    private String major;
    private String year;
    private String factors;
    private String cope;
    private String advice;


    public application(String name, String major, String year, String factors, String cope, String advice) {
        this.name = name;
        this.major = major;
        this.year = year;
        this.factors = factors;
        this.cope = cope;
        this.advice = advice;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public String getYear() {
        return year;
    }

    public String getAdvice() {
        return advice;
    }

    public String getCope() {
        return cope;
    }

    public String getFactors() {
        return factors;
    }
}
