package com.quokka.map.service.impl;

import com.quokka.map.doa.EmployeeRepository;
import com.quokka.map.domain.Employee;
import com.quokka.map.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class EmployeeServiceImpl implements EmployeeService {
@Autowired
private EmployeeRepository employeeRepository;

    @Autowired
    @Qualifier(value="domain")
    public JdbcTemplate jdbcTemplate;

    @Override
    public Employee save(Employee employee) {
        Map<String,Object> map= new HashMap<>();
        map.put("id",employee.getId());
        map.put("employeeName",employee.getEmployeeName());
        String sql ="insert into employee (id,employeeName) values (:id,:employeeName)";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate= new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        int n =namedParameterJdbcTemplate.update(sql,map);
        return (n==1?employee:new Employee());
    }

    @Override
    public Employee getById(Long empId) {
        String sql ="select id ,employeeName from employee where id = :ids";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate= new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        Map<String ,Object> map= new HashMap<>();
        map.put("ids",empId);
       return namedParameterJdbcTemplate.query(sql,map,rs->{
           Employee employee = new Employee();
             if(rs.next()){
                 employee.setId(rs.getLong("id"));
                 employee.setEmployeeName(rs.getString("employeeName"));
             }
            return employee!=null?employee:new Employee();
        });
      //  return employeeRepository.getById(empId);
    }

    @Override
    public List<Employee> list() {
        String sql="select * from employee";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate= new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
       List<Employee> employeeList= namedParameterJdbcTemplate.query(sql,(rs ,rowNum)->{
            Employee employee= new Employee();
            employee.setId(rs.getLong("id"));
          employee.setEmployeeName(rs.getString("employeeName"));
          return employee;
        });
        return employeeList;
    }
}
