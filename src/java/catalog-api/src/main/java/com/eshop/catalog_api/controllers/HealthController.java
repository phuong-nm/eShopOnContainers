package com.eshop.catalog_api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HealthController {

    @GetMapping("/hc")
    public String getHealth() {
        return new String("{\r\n" + //
                        "    \"status\": \"Healthy\",\r\n" + //
                        "    \"totalDuration\": \"00:00:00.0022111\",\r\n" + //
                        "    \"entries\": {\r\n" + //
                        "        \"self\": {\r\n" + //
                        "            \"data\": {},\r\n" + //
                        "            \"duration\": \"00:00:00.0000060\",\r\n" + //
                        "            \"status\": \"Healthy\",\r\n" + //
                        "            \"tags\": []\r\n" + //
                        "        }\r\n" + //
                        "    }\r\n" + //
                        "}");
    }

    @GetMapping("/liveness")
    public String getLiveness() {
        return new String("Healthy");
    }
}
