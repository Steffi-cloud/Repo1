package com.yourorg.auth.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api-docs")
public class OpenApiController {

    @GetMapping(value = "/auth", produces = MediaType.APPLICATION_YAML_VALUE)
    public ResponseEntity<String> getOpenApiYaml() throws IOException {
        Resource resource = new ClassPathResource("AuthServiceOpenAPISpec.yaml");
        String yaml = new String(Files.readAllBytes(resource.getFile().toPath()));
        return ResponseEntity.ok(yaml);
    }
}
