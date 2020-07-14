public class studentOrg {
    private String name;
    private String email;
    private String representative;
    private String email_repre;
    private String commonName;
    private String size;
    private String info;
    private String part;
    private String tags;
    private String socialMedia;
    private String logo;
    private String wish;


    public studentOrg(String name, String email, String representative, String email_repre, String commonName, String size, String info, String part, String tags, String socialMedia, String wish, String logo) {
        this.name = name;
        this.commonName = commonName;
        this.email = email;
        this.email_repre = email_repre;
        this.info = info;
        this.logo = logo;
        this.part = part;
        this.representative = representative;
        this.size = size;
        this.socialMedia = socialMedia;
        this.tags = tags;
        this.wish = wish;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getEmail_repre() {
        return email_repre;
    }

    public String getInfo() {
        return info;
    }

    public String getLogo() {
        return logo;
    }

    public String getPart() {
        return part;
    }

    public String getRepresentative() {
        return representative;
    }

    public String getSize() {
        return size;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public String getTags() {
        return tags;
    }

    public String getWish() {
        return wish;
    }
}

