package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private Logger log = LoggerFactory.getLogger(AuditController.class);

	@GetMapping("/run-now")
	public String runAuditNow() {
		auditService.runAudit();
		log.info("in run audit now method");
		return "✅ Manual audit triggered.";
	}

	@GetMapping("/status")
	public AuditResult getLastAuditResult() {
		log.info("in getLastAuditResult method");
		return auditService.getLastAuditSummary();
	}
}
