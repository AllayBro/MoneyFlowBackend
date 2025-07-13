package com.moneyflow.controller;

import com.moneyflow.model.Model;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    private final JdbcTemplate jdbcTemplate;

    public Controller(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/ping")
    public String ping() {
        return "<html><body><h1>PONG</h1></body></html>";
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "HEALTHY");
    }

    @GetMapping("/list")
    public String list() {
        List<Model> list = jdbcTemplate.query(
                "SELECT city, temperature FROM weather",
                (rs, rowNum) -> {
                    Model m = new Model();
                    m.setCity(rs.getString("city"));
                    m.setTemperature(rs.getInt("temperature"));
                    return m;
                }
        );
        StringBuilder sb = new StringBuilder("<html><body><ul>");
        for (Model m : list) {
            sb.append("<li>")
                    .append(m.getCity())
                    .append(": ")
                    .append(m.getTemperature())
                    .append("°C</li>");
        }
        sb.append("</ul></body></html>");
        return sb.toString();
    }

    @PostMapping("/add")
    public String add(@RequestParam String city, @RequestParam int temperature) {
        jdbcTemplate.update(
                "INSERT INTO weather(city, temperature) VALUES (?, ?)",
                city,
                temperature
        );
        return "Added " + city;
    }
}
