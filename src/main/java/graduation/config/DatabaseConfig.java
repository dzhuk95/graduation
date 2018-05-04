package graduation.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

import static org.hibernate.cfg.AvailableSettings.FORMAT_SQL;
import static org.hibernate.cfg.AvailableSettings.USE_SQL_COMMENTS;

@Configuration
@PropertySources({@PropertySource("classpath:database/database.properties")})
@EnableTransactionManagement
@ComponentScan(basePackages = "graduation.model")
@EnableJpaRepositories(basePackages = "graduation.repository")
public class DatabaseConfig {
    @Autowired
    private Environment environment;


    @Bean
    public DataSource dataSource() {
        String username = environment.getProperty("database.username");
        String password = environment.getProperty("database.password");
        String url = environment.getProperty("database.url");

        DataSource dataSource = new DataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        String formatSql = environment.getProperty("jpa.format.sql");
        String comments = environment.getProperty("jpa.show.sql.comment");
        String showSql = environment.getProperty("jpa.show.sql");

        Map<String, Object> properties = new HashMap<>();
        properties.put(FORMAT_SQL, formatSql);
        properties.put(USE_SQL_COMMENTS, comments);
        boolean aTrue = showSql.equalsIgnoreCase("true");

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaPropertyMap(properties);
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(aTrue);
        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        entityManagerFactory.setPackagesToScan("graduation.model.orm");

        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
