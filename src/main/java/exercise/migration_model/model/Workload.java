package exercise.migration_model.model;


import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.*;

@Table
public class Workload {
    @PrimaryKey
    private UUID id;

    private String ip;
    private Credentials credentials;
    private List<Volume> volumes;
    /*
    Constrains:
●	Username, password, IP cannot not be Null
●	IP cannot change
●	You cannot have more than one source with the same IP
     */

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public List<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
    }

    @Override
    public String toString() {
        return "Workload{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", credentials=" + credentials +
                ", volumes=" + volumes +
                '}';
    }

    public static void main(String[] args) throws Exception {
        doSomeStuff();
    }
    public static void doSomeStuff() throws Exception {
        double d = 0.0;
        int i = 1;
        d = i;
        i = (int) d;
        System.out.println(i);
        System.out.println("" + 1 + 2);

        throw new Exception();
    }

}
