package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AuditResult;
import com.example.demo.service.AuditService;

@RestController
@RequestMapping("/audit")
public class AuditController {
	 @Autowired
	    private AuditService auditService;

	    @GetMapping("/run-now")
	    public String runAuditNow() {
	        auditService.runAudit();
	        return "✅ Manual audit triggered.";
	    }

	    @GetMapping("/status")
	    public AuditResult getLastAuditResult() {
	        return auditService.getLastAuditSummary();
	    }
}
