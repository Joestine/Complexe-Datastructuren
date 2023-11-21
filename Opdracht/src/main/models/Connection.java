package main.models;

public class Connection implements Comparable<Connection> {
    private final String start;
    private final String end;
    private final int distance;

    public Connection(String start, String end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    @Override
    public int compareTo(Connection connection) {
        return Integer.compare(this.distance, connection.distance);
    }

    @Override
    public String toString() {
        return start + " - " + end + " (" + distance + ")";
    }
}
