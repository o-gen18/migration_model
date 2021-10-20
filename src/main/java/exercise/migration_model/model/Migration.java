package exercise.migration_model.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Table
public class Migration {
    public enum MigrationState {
        NOT_STARTED, RUNNING, ERROR, SUCCESS
    }
    @PrimaryKeyColumn(name = "migration_id", type = PrimaryKeyType.PARTITIONED)
    private UUID id;
    private List<String> selectedMountPoints;

    private Workload source;
    private TargetCloud targetCloud;
    private MigrationState migrationState;

    public Migration() { }

    public Migration(UUID id, List<String> selectedMountPoints,
                     Workload source, TargetCloud targetCloud, MigrationState migrationState) {
        this.id = id;
        this.selectedMountPoints = selectedMountPoints;
        this.source = source;
        this.targetCloud = targetCloud;
        this.migrationState = migrationState;
    }

    public void run() {
        try {
            Thread.sleep(5*60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!selectedMountPoints.contains("c:\\")) {
            return;
        }
        retainSelectedMountPoints(source, selectedMountPoints);
        targetCloud.setTarget(this.source);
    }

    private void retainSelectedMountPoints(Workload source, List<String> selectedMountPoints) {
        List<Volume> extra = new ArrayList<>();
        source.getVolumes().forEach((vol) -> {
            if (!selectedMountPoints.contains(vol.getMountPoint())) {
                extra.add(vol);
            }
        });
        source.getVolumes().removeAll(extra);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<String> getSelectedMountPoints() {
        return selectedMountPoints;
    }

    public void setSelectedMountPoints(List<String> selectedMountPoints) {
        this.selectedMountPoints = selectedMountPoints;
    }

    public Workload getSource() {
        return source;
    }

    public void setSource(Workload source) {
        this.source = source;
    }

    public TargetCloud getTargetCloud() {
        return targetCloud;
    }

    public void setTargetCloud(TargetCloud targetCloud) {
        this.targetCloud = targetCloud;
    }

    public MigrationState getMigrationState() {
        return migrationState;
    }

    public void setMigrationState(MigrationState migrationState) {
        this.migrationState = migrationState;
    }

    @Override
    public String toString() {
        return "Migration{" +
                "id=" + id +
                ", selectedMountPoints=" + selectedMountPoints +
                ", source=" + source +
                ", targetCloud=" + targetCloud +
                ", migrationState=" + migrationState +
                '}';
    }
}
