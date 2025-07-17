package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ReportService;

import net.sf.jasperreports.engine.JRException;
@RestController
@RequestMapping("/report")
public class UserController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/users")
    public ResponseEntity<byte[]> generateUserReport() throws JRException {
        byte[] report = reportService.exportUsersReport();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users_report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(report);
    }
    
    
	/*
	 * @GetMapping("/report/excel") public ResponseEntity<byte[]>
	 * downloadExcelReport() throws JRException, IOException { byte[] excelBytes =
	 * reportService.exportUsersReportAsExcel();
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.set(HttpHeaders.CONTENT_DISPOSITION,
	 * "attachment; filename=users_report.xlsx");
	 * headers.setContentType(MediaType.parseMediaType(
	 * "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
	 * 
	 * return ResponseEntity.ok().headers(headers).body(excelBytes); }
	 */
}
