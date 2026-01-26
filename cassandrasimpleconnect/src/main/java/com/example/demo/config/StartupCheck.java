package com.example.demo.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.stereotype.Component;

@Component
public class StartupCheck {

    public StartupCheck(CqlSession session) {
        System.out.println("✅ Cassandra connected, keyspace = " + session.getKeyspace());
    }
}
