package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuditResult {
    private LocalDateTime timestamp;
    private List<String> warnings = new ArrayList<>();

    public AuditResult() {
        this.timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void addWarning(String warning) {
        this.warnings.add(warning);
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}
