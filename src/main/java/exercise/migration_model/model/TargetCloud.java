package exercise.migration_model.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

//@Table
public class TargetCloud {
    public enum CloudType{
        AWS, AZURE, VSPHERE, VCLOUD;
    }

    @PrimaryKey
    private UUID id;
    private CloudType cloudType;
    private Credentials credentials;
    private Workload target;

    public TargetCloud() { }

    public TargetCloud(UUID id, CloudType cloudType, Credentials credentials, Workload target) {
        this.id = id;
        this.cloudType = cloudType;
        this.credentials = credentials;
        this.target = target;
    }

    public CloudType getCloudType() {
        return cloudType;
    }

    public void setCloudType(CloudType cloudType) {
        this.cloudType = cloudType;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Workload getTarget() {
        return target;
    }

    public void setTarget(Workload target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "TargetCloud{" +
                "id=" + id +
                ", cloudType=" + cloudType +
                ", credentials=" + credentials +
                ", target=" + target +
                '}';
    }
}
