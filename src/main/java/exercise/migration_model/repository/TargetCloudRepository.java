package exercise.migration_model.repository;

import exercise.migration_model.model.TargetCloud;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface TargetCloudRepository extends CassandraRepository<TargetCloud, UUID> {
}
