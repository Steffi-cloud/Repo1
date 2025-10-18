package com.example.demo.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.controller.AuditController;
import com.example.demo.model.AuditResult;
@Service
public class AuditService {
	   @Value("${audit.prometheus-url}")
	    private String prometheusUrl;
		private Logger log = LoggerFactory.getLogger(AuditService.class);

	    private final RestTemplate restTemplate = new RestTemplate();

	    private AuditResult lastAuditResult = new AuditResult();

	    @Scheduled(fixedRate = 60000)
	    public void runAudit() {
	        AuditResult result = new AuditResult();
	        log.info("in runAudit method");
	        checkMemoryUsage(result);
	        checkCPUUsage(result);
	        checkConnectionPoolLeaks(result);

	        lastAuditResult = result;
	    }

	    public AuditResult getLastAuditSummary() {
	        return lastAuditResult;
	    }

	    private void checkMemoryUsage(AuditResult result) {
	        String query = "sum by(instance) (process_resident_memory_bytes)";
	        String url = prometheusUrl + "/api/v1/query?query=" + query;
	        JSONObject response = new JSONObject(restTemplate.getForObject(url, String.class));
	        log.info("in checkMemoryUsage method");
	        JSONArray results = response.getJSONObject("data").getJSONArray("result");
	        for (int i = 0; i < results.length(); i++) {
	            JSONObject metricObj = results.getJSONObject(i).getJSONObject("metric");
	            String instance = metricObj.optString("instance"); // Use actual key present in your Prometheus response

	            double value = results.getJSONObject(i).getJSONArray("value").getDouble(1) / (1024 * 1024); // Convert to MB

	            if (value > 500) {
	                result.addWarning("⚠️ High memory usage on " + instance + ": " + value + " MB");
	            }
	        }

	    }
	    private void checkCPUUsage(AuditResult result) {
	        String query = "rate(process_cpu_seconds_total[1m])";
	        String url = prometheusUrl + "/api/v1/query?query=" + query;
	        JSONObject response = new JSONObject(restTemplate.getForObject(url, String.class));
	        log.info("in checkCPUUsage method");
	        JSONArray results = response.getJSONObject("data").getJSONArray("result");
	        for (int i = 0; i < results.length(); i++) {
	            JSONObject metricObj = results.getJSONObject(i).getJSONObject("metric");
	            String instance = metricObj.optString("instance");

	            double value = results.getJSONObject(i).getJSONArray("value").getDouble(1);
	            if (value > 0.8) {
	                result.addWarning("⚠️ High CPU usage on " + instance + ": " + value);
	            }
	        }
	    }


	    private void checkConnectionPoolLeaks(AuditResult result) {
	        String query = "db_connection_pool_active_connections";
	        String url = prometheusUrl + "/api/v1/query?query=" + query;
	        JSONObject response = new JSONObject(restTemplate.getForObject(url, String.class));
	        log.info("in checkConnectionPoolLeaks method");
	        JSONArray results = response.getJSONObject("data").getJSONArray("result");
	        for (int i = 0; i < results.length(); i++) {
	            String instance = results.getJSONObject(i).getJSONArray("metric").optString(0);
	            double activeConnections = results.getJSONObject(i).getJSONArray("value").getDouble(1);
	            if (activeConnections > 50) {
	                result.addWarning("⚠️ Possible DB connection leak on " + instance);
	            }
	        }
	    }}
