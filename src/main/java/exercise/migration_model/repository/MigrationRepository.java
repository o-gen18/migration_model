package exercise.migration_model.repository;

import exercise.migration_model.model.Migration;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface MigrationRepository extends CassandraRepository<Migration, UUID> {
}
