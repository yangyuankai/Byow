public class Transition {
    private String name;
    private String email;
    private String major;
    private String residency;
    private String year;
    private String feel;
    private String emo;
    private String concerns;

    public Transition(String name, String email, String major,String residency, String year, String feel, String emo, String concerns ) {
        this.name = name;
        this.email = email;
        this.concerns = concerns;
        this.feel = feel;
        this.year= year;
        this.residency = residency;
        this.emo = emo;
        this.major = major;


    }


    public String getName() {
        return name;
    }

    public String getConcerns() {
        return concerns;
    }

    public String getEmail() {
        return email;
    }

    public String getEmo() {
        return emo;
    }

    public String getFeel() {
        return feel;
    }


    public String getMajor() {
        return major;
    }

    public String getResidency() {
        return residency;
    }


    public String getYear() {
        return year;
    }
}
