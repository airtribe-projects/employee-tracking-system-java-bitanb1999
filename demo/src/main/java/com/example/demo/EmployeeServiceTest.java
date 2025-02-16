package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testGetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(new Employee(), new Employee()));
        assertEquals(2, employeeService.getAllEmployees().size());
    }
}
