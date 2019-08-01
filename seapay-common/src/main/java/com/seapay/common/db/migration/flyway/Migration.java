package com.seapay.common.db.migration.flyway;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import org.flywaydb.core.Flyway;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class Migration {
    public static void main(String[] args) {

        Set<String> requiredConfig = new HashSet<>(asList(
            "DB_HOST",
            "DB_NAME",
            "DB_USERNAME",
            "DB_PASSWORD"
        ));
        ApplicationConfiguration config = Figaro.configure(requiredConfig);

        Flyway flyway = new Flyway();
        flyway.setDataSource(
            String.format("jdbc:postgresql://%s/%s", config.getValueAsString("DB_HOST"), config.getValueAsString("DB_NAME")),
            config.getValueAsString("DB_USERNAME"),
            config.getValueAsString("DB_PASSWORD"));
        flyway.setBaselineOnMigrate(true);
        flyway.migrate();
    }

}
