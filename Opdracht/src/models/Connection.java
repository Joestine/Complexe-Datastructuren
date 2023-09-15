package models;

public class Connection {
    private final String start;
    private final String end;
    private final int distance;

    public Connection(String start, String end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }
}
