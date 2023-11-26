package main.models;

import main.enums.StationType;

public class Station implements Comparable<Station> {
    private final int id;
    private final String code;
    private final int uic;
    private final String nameShort;
    private final String nameMedium;
    private final String nameLong;
    private final String slug;
    private final String country;
    private final StationType type;
    private final Geolocation geolocation;

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
        this.geolocation = new Geolocation(latitude, longitude);
    }

    public boolean isInsideRectangle(Geolocation a, Geolocation b) {
        return geolocation.latitude() >= a.latitude()
                && geolocation.latitude() <= b.latitude()
                && geolocation.longitude() >= a.longitude()
                && geolocation.longitude() <= b.longitude();
    }

    @Override
    public String toString() {
        return "Station {" + nameShort + ", " + code + "}";
    }

    public String getCode() {
        return code;
    }

    public String getNameShort() {
        return nameShort;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }
    @Override
    public int compareTo(Station o) {
        return this.nameShort.compareTo(o.nameShort);
    }
}
