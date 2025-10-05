package com.yourorg.subscription;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


@SpringBootApplication

public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
    @Bean
    public CommandLineRunner checkBeans(ApplicationContext context) {
        return args -> {

            System.out.println("---- Checking for MySQL JDBC Driver ----");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("✅ MySQL JDBC Driver is loaded.");
            } catch (ClassNotFoundException e) {
                System.out.println("❌ MySQL JDBC Driver NOT found!");
            }

            System.out.println("---- Checking for Spring Data JPA Components ----");

            if (context.getBeansOfType(LocalContainerEntityManagerFactoryBean.class).isEmpty()) {
                System.out.println("❌ EntityManagerFactory bean NOT found.");
            } else {
                System.out.println("✅ EntityManagerFactory bean is present.");
            }

            String[] jpaRepos = context.getBeanNamesForType(JpaRepository.class);
            if (jpaRepos.length == 0) {
                System.out.println("❌ No JpaRepository beans found.");
            } else {
                System.out.println("✅ JpaRepository beans found:");
                for (String name : jpaRepos) {
                    System.out.println("   - " + name);
                }
            }
        };
    }
}
