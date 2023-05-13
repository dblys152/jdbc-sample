package com.ys.adapter.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.core.convert.Jsr310TimestampBasedConverters;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableJdbcAuditing
public class DataJdbcConfig extends AbstractJdbcConfiguration {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Override
    public JdbcCustomConversions jdbcCustomConversions() {
        List<Object> converters = new ArrayList<>(
                getConvertersToRegister()
        );

        return new JdbcCustomConversions(converters);
    }

    public static Collection<Converter<?, ?>> getConvertersToRegister(Converter<?, ?>... converter) {
        List<Converter<?, ?>> converters = new ArrayList<>(5 + converter.length);
        converters.add(Jsr310TimestampBasedConverters.LocalDateTimeToTimestampConverter.INSTANCE);
        converters.add(Jsr310TimestampBasedConverters.TimestampToLocalDateTimeConverter.INSTANCE);
        Collections.addAll(converters, converter);
        return converters;
    }

    @Bean
    NamedParameterJdbcOperations jdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}