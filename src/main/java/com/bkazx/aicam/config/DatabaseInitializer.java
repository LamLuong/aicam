package com.bkazx.aicam.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DatabaseInitializer {
   private final DataSource dataSource;

   public DatabaseInitializer(@Autowired DataSource dataSource) {
      this.dataSource = dataSource;
   }

   @PostConstruct
   public void initialize() {
      try (Connection connection = dataSource.getConnection()) {
         Statement statement = connection.createStatement();
         InputStream inputStream = this.getClass().getResourceAsStream("/initial_db.sql");
         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
         String line;
         StringBuilder script = new StringBuilder();
         while ((line = reader.readLine()) != null) {
            script.append(line).append("\n");
         }
         reader.close();
         statement.executeUpdate(script.toString());
         statement.close();
         connection.close();
         log.info("The SQL script was executed successfully.");
      } catch (Exception e) {
         log.error("Error executing SQL script", e);
      }

   }
}
