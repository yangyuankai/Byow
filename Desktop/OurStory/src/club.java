public class club {
    private String name;
    private String major;
    private String year;
    private String reason;
    private String how;
    private String prosCons;
    private String bal;


    public club(String name, String major, String year, String reason, String how, String prosCons, String bal) {
        this.name = name;
        this.major = major;
        this.year = year;
        this.reason = reason;
        this.how = how;
        this.prosCons = prosCons;
        this.bal = bal;
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

    public String getBal() {
        return bal;
    }

    public String getHow() {
        return how;
    }

    public String getProsCons() {
        return prosCons;
    }

    public String getReason() {
        return reason;
    }
}
