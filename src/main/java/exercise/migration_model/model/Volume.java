package exercise.migration_model.model;

import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

//@UserDefinedType("volume")
@UserDefinedType
public class Volume {
//
//    @Column
//    @PrimaryKey
//    @CassandraType(type = CassandraType.Name.UUID)
//    private UUID id;
//    @Column
    private String mountPoint = "c:\\";
//    @Column
    long totalSize;

    public Volume() {
    }

    public Volume(String mountPoint, int totalSize) {
        this.mountPoint = mountPoint;
        this.totalSize = totalSize;
    }

    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public String toString() {
        return "Volume{" +

                ", mountPoint='" + mountPoint + '\'' +
                ", totalSize=" + totalSize +
                '}';
    }
}
