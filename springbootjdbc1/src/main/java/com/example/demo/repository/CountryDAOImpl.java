package com.example.demo.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
@Component
public class CountryDAOImpl {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, Object> execute() {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("get_countries");

		// Map<String, Object> inParamMap = new HashMap<String, Object>();
		// inParamMap.put("firstName", "FValue");
		// inParamMap.put("lastName", "LValue");
		SqlParameterSource in = new MapSqlParameterSource(null);

		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		System.out.println(simpleJdbcCallResult);

		return simpleJdbcCallResult;
	}
}
