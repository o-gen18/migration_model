package exercise.migration_model.repository;

import exercise.migration_model.model.Volume;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface VolumeRepository extends CassandraRepository<Volume, UUID> {
}
