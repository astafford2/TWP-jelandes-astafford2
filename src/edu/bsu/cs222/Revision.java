package edu.bsu.cs222;

public class Revision {
    String username;
    String timestamp;

    Revision(String user, String time)
    {
        username = user;
        timestamp = time;
    }

    @Override
    public String toString() {
        return "Revision{" +
                "username='" + username + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
