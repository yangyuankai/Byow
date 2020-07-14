public class Social {
    private String name;
    private String email;
    private String restau;
    private String shop;
    private String late;
    private String event;

    public Social(String name, String email, String restau, String shop, String late, String event) {
        this.name = name;
        this.email = email;
        this.restau = restau;
        this.shop = shop;
        this.event = event;
        this.late = late;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getEvent() {
        return event;
    }

    public String getLate() {
        return late;
    }

    public String getRestau() {
        return restau;
    }

    public String getShop() {
        return shop;
    }
}
