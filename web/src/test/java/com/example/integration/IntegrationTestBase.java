package com.example.integration;

import com.example.InstagramApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(classes = {InstagramApplication.class})
abstract public class IntegrationTestBase {

   private static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:14:1");

    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
    }
}
