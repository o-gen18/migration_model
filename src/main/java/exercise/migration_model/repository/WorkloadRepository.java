package exercise.migration_model.repository;

import exercise.migration_model.model.Workload;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface WorkloadRepository extends CassandraRepository<Workload, UUID> {
}
