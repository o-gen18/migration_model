package exercise.migration_model.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.migration_model.model.Credentials;
import exercise.migration_model.model.Volume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.convert.CassandraCustomConversions;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.core.cql.session.init.KeyspacePopulator;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class CassandraConfig extends AbstractCassandraConfiguration {
    public static final String KEYSPACE = "migration";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(CassandraConfig.class.getName());

    @Override
    public String[] getEntityBasePackages() {
        return new String[] {"exercise.migration_model.model"};
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE)
                .ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true);
        return Arrays.asList(specification);
    }

    /* Not for permanent installation of cassandra. */
    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
    }

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }


    @Component
    @WritingConverter
    public static class CredentialsWriteConverter implements Converter<Credentials, String> {

        @Override
        public String convert(Credentials source) {
            String converted = "";
            try {
                converted = objectMapper.writeValueAsString(source);
            } catch (JsonProcessingException e) {
                LOGGER.error("Could not write Credentials as string", e);
            }
            return converted;
        }
    }

    @Component
    @ReadingConverter
    public static class CredentialsReadConverter implements Converter<String, Credentials> {

        @Override
        public Credentials convert(String source) {
            Credentials converted = new Credentials();
            try {
                converted = objectMapper.readValue(source, Credentials.class);
            } catch (JsonProcessingException e) {
                LOGGER.error("Could not create Credentials from string", e);
            }
            return converted;
        }
    }

    @Component
    @WritingConverter
    public static class VolumeWriteConverter implements Converter<Volume, String> {

        @Override
        public String convert(Volume source) {
            String converted = "";
            try {
                converted = objectMapper.writeValueAsString(source);
            } catch (JsonProcessingException e) {
                LOGGER.error("Could not write Volume as string", e);
            }
            return converted;
        }
    }

    @Component
    @ReadingConverter
    public static class VolumeReadConverter implements Converter<String, Volume> {

        @Override
        public Volume convert(String source) {
            Volume converted = new Volume();
            try {
                converted = objectMapper.readValue(source, Volume.class);
            } catch (JsonProcessingException e) {
                LOGGER.error("Could not create Volume from string", e);
            }
            return converted;
        }
    }

//    @Bean
//    public CassandraCustomConversions cassandraCustomConversions(VolumeWriteConverter volumeWriteConverter,
//                                                                 VolumeReadConverter volumeReadConverter,
//                                                                 CredentialsWriteConverter credentialsWriteConverter,
//                                                                 CredentialsReadConverter credentialsReadConverter) {
//        return new CassandraCustomConversions(Arrays.asList(volumeReadConverter,
//                volumeWriteConverter,
//                credentialsReadConverter,
//                credentialsWriteConverter));
//    }

    @Override
    public CassandraCustomConversions customConversions() {
        return new CassandraCustomConversions(Arrays.asList(
                new CredentialsWriteConverter(),
                new CredentialsReadConverter()));
    }
}
