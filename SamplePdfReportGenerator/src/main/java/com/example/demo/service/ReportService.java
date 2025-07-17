package com.example.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
@Service
public class ReportService {
	  @Autowired
	    private UserRepository userRepository;

	    public byte[] exportUsersReport() throws JRException {
	    	List<User> users = new ArrayList<>();
	    	User user1=new User("abc","abc@gmail.com");
	    	users.add(user1);
	    	System.out.println("Fetched users: " + users.size()); // assuming constructor exists

	    	    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);

	    	    JasperReport report = JasperCompileManager.compileReport(
	    	        getClass().getResourceAsStream("/users_report.jrxml")
	    	    );

	    	    Map<String, Object> parameters = new HashMap<>();
	    	    parameters.put("createdBy", "Spring Boot App");

	    	    JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
	    	    return JasperExportManager.exportReportToPdf(print);
	    }
	    
		/*
		 * public byte[] exportUsersReportAsExcel() throws JRException, IOException {
		 * List<User> users = userRepository.findAll(); boolean noData =
		 * users.isEmpty();
		 * 
		 * // If no users, inject a dummy object just to trigger the detail band if
		 * (noData) { users = List.of(new User("N/A", "N/A")); } // users.add(new
		 * User("Alice", "alice@example.com")); JRBeanCollectionDataSource dataSource =
		 * new JRBeanCollectionDataSource(users);
		 * 
		 * JasperReport report = JasperCompileManager.compileReport(
		 * getClass().getResourceAsStream("/users_report.jrxml") );
		 * 
		 * Map<String, Object> parameters = new HashMap<>(); parameters.put("createdBy",
		 * "Spring Boot App"); parameters.put("noData", noData);
		 * 
		 * JasperPrint print = JasperFillManager.fillReport(report, parameters,
		 * dataSource);
		 * 
		 * JRXlsxExporter exporter = new JRXlsxExporter(); ByteArrayOutputStream
		 * outputStream = new ByteArrayOutputStream();
		 * 
		 * exporter.setExporterInput(new SimpleExporterInput(print));
		 * exporter.setExporterOutput(new
		 * SimpleOutputStreamExporterOutput(outputStream));
		 * 
		 * SimpleXlsxReportConfiguration configuration = new
		 * SimpleXlsxReportConfiguration(); configuration.setOnePagePerSheet(false);
		 * configuration.setDetectCellType(true);
		 * configuration.setWhitePageBackground(false);
		 * configuration.setRemoveEmptySpaceBetweenColumns(true);
		 * configuration.setRemoveEmptySpaceBetweenRows(true);
		 * configuration.setWrapText(true); configuration.setCollapseRowSpan(false);
		 * 
		 * exporter.setConfiguration(configuration); exporter.exportReport();
		 * 
		 * return outputStream.toByteArray(); }
		 */

}
