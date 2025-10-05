package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeEvents;
import com.example.demo.service.EmployeeService;

public class EmployeeControllerTest {
	 private MockMvc mockMvc;

	    @InjectMocks
	    private EmployeeController employeeController;

	    @Mock
	    private EmployeeService employeeService;

	    @Mock
	    private KafkaTemplate<String, EmployeeEvents> kafkaTemplate;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	    }

	    @Test
	    void testGetEmployeeByIdAndPublishKafkaEvent() throws Exception {
	        // Arrange
	        Employee emp = new Employee();
	        emp.setId(1);
	        emp.setName("Alice");

	        when(employeeService.getEmployeeById(1)).thenReturn(emp);

	        // Act & Assert
	        mockMvc.perform(get("/getEmp")
	                .param("id", "1")
	                .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.id").value(1))
	            .andExpect(jsonPath("$.name").value("Alice"));

	        // Verify that KafkaTemplate.send was called with expected topic and data
	        ArgumentCaptor<EmployeeEvents> eventCaptor = ArgumentCaptor.forClass(EmployeeEvents.class);
	        verify(kafkaTemplate, times(1)).send(eq("employee-event-topic"), eventCaptor.capture());

	        EmployeeEvents capturedEvent = eventCaptor.getValue();
	        assertEquals("GET", capturedEvent.getKeyMap());
	        assertEquals("Alice", capturedEvent.getEmpList().getName());
	    }
}
