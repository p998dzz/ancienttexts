package lt.ancienttexts;

import lt.ancienttexts.adapter.db.ImageDbAdapter;
import lt.ancienttexts.adapter.db.TabletsDbAdapter;
import lt.ancienttexts.adapter.ImageAdapter;
import lt.ancienttexts.adapter.TabletAdapter;
import lt.ancienttexts.service.util.DbEntityConverter;
import lt.ancienttexts.service.util.EntityConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.lob.DefaultLobHandler;

import javax.swing.text.html.parser.Entity;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(
            @Value("${db.host}") String dbHost,
            @Value("${db.user}") String dbUser,
            @Value("${db.password}") String dbPassword,
            @Value("${db.database}") String dbDatabase) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String dbUrl = "jdbc:postgresql://" + dbHost + "/" + dbDatabase;
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);

        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public ImageAdapter imageAdapter(NamedParameterJdbcTemplate jdbcTemplate) {
        return new ImageDbAdapter(jdbcTemplate, new DefaultLobHandler());
    }

    @Bean
    public TabletAdapter tabletAdapter(NamedParameterJdbcTemplate jdbcTemplate) {
        return new TabletsDbAdapter(jdbcTemplate);
    }

    @Bean
    public EntityConverter entityConverter() {
        return new DbEntityConverter();
    }
}
