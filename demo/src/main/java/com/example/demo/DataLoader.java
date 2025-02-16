package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create roles
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleRepository.save(adminRole);

        Role managerRole = new Role();
        managerRole.setName("MANAGER");
        roleRepository.save(managerRole);

        Role employeeRole = new Role();
        employeeRole.setName("EMPLOYEE");
        roleRepository.save(employeeRole);

        // Create an admin employee
        Employee admin = new Employee();
        admin.setName("Admin User");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRoles(Set.of(adminRole));
        employeeRepository.save(admin);

        // Create a manager employee
        Employee manager = new Employee();
        manager.setName("Manager User");
        manager.setUsername("manager");
        manager.setPassword(passwordEncoder.encode("manager123"));
        manager.setRoles(Set.of(managerRole));
        employeeRepository.save(manager);

        // Create an employee
        Employee employee = new Employee();
        employee.setName("Employee User");
        employee.setUsername("employee");
        employee.setPassword(passwordEncoder.encode("employee123"));
        employee.setRoles(Set.of(employeeRole));
        employeeRepository.save(employee);
    }
}
