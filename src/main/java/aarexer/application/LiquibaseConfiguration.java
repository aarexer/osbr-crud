package aarexer.application;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, LiquibaseAutoConfiguration.class})
public class LiquibaseConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.liquibase")
    public LiquibaseProperties mainLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean
    public SpringLiquibase liquibase(DataSource ds) {
        return createLiquibase(ds, mainLiquibaseProperties());
    }

    private SpringLiquibase createLiquibase(DataSource ds, LiquibaseProperties properties) {
        return new SpringLiquibase() {
            {
                setDataSource(ds);
                setChangeLog(properties.getChangeLog());
                setContexts(properties.getContexts());
                setDefaultSchema(properties.getDefaultSchema());
                setDropFirst(properties.isDropFirst());
                setShouldRun(properties.isEnabled());
                setLabels(properties.getLabels());
                setChangeLogParameters(properties.getParameters());
                setRollbackFile(properties.getRollbackFile());
            }
        };
    }
}
