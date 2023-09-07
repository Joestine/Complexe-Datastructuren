import enums.StationType;

public class Station {
    private int id;
    private String code;
    private int uic;
    private String nameShort;
    private String nameMedium;
    private String nameLong;
    private String slug;
    private String country;
    private StationType type;
    private double latitude;
    private double longitude;

    public Station(int id, String code, int uic, String nameShort, String nameMedium, String nameLong, String slug, String country, StationType type, double latitude, double longitude) {
        this.id = id;
        this.code = code;
        this.uic = uic;
        this.nameShort = nameShort;
        this.nameMedium = nameMedium;
        this.nameLong = nameLong;
        this.slug = slug;
        this.country = country;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", uic=" + uic +
                ", nameShort='" + nameShort + '\'' +
                ", nameMedium='" + nameMedium + '\'' +
                ", nameLong='" + nameLong + '\'' +
                ", slug='" + slug + '\'' +
                ", country='" + country + '\'' +
                ", type=" + type +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
