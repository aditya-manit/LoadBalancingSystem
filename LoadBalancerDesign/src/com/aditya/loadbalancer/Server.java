package com.aditya.loadbalancer;

import java.util.Objects;

import static com.aditya.loadbalancer.Status.*;

public class Server {
    private int serverID;
    private Status status;

    public int getServerID() {
        return serverID;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }

    public Status getStatus() {  // we wont be able to use this method if a object server is down.
        return status;
    }

    public Server(int serverID) {
        this.serverID = serverID;
        this.status = ACTIVE;



    }

    public void down() {
        this.status=DOWN;
    }

    public void up() {
        this.status=ACTIVE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return serverID == server.serverID &&
                status == server.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverID);
    }
}
