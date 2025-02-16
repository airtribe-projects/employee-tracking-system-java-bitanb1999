package com.example.demo;

import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"employees", "departments", "projects"})
public class EmployeeService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    private PasswordEncoder _passwordEncoder;

    @Autowired
    private VerificationTokenRepository _verificationTokenRepository;

    public Employee registerUser(UserPojo userPojo) {
        Employee databaseUser = new Employee();
        databaseUser.setEmail(userPojo.getEmail());
        databaseUser.setName(userPojo.getName());
        databaseUser.setPassword(_passwordEncoder.encode(userPojo.getPassword()));
        databaseUser.setRole("USER");
        databaseUser.setEnabled(false);
        return employeeRepository.save(databaseUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = employeeRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                Collections.emptyList());
    }

    public void createVerificationToken(Employee registeredUser, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setEmployee(registeredUser);
        long expiryDate = (new Date().getTime() + 1000 * 60 * 60 * 24);
        verificationToken.setExpiryDate(new Date(expiryDate));
        _verificationTokenRepository.save(verificationToken);

    }

    public boolean validateToken(String token) {
        VerificationToken storedToken = _verificationTokenRepository.findByToken(token);
        if (storedToken == null) {
            return false;
        }

        long expiryTime = storedToken.getExpiryDate().getTime();
        if (expiryTime < System.currentTimeMillis()) {
            return false;
        }

        return true;
    }

    public void enableUser(String token) {
        VerificationToken storedToken = _verificationTokenRepository.findByToken(token);
        Employee storedUser = storedToken.getEmployee();
        storedUser.setEnabled(true);
        employeeRepository.save(storedUser);
        _verificationTokenRepository.delete(storedToken);
    }

    public String loginUser(String email, String password) {
        Employee fetchedUser = employeeRepository.findByEmail(email);

        if (fetchedUser == null || !fetchedUser.isEnabled()) {
            return "Employee not found or not enabled";
        }

        boolean isPasswordMatched = _passwordEncoder.matches(password, fetchedUser.getPassword());

        if (!isPasswordMatched) {
            return "Invalid Password";
        }

        return JwtUtil.generateToken(email);
    }
    @Cacheable("employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @CacheEvict(value = "employees", allEntries = true)
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

}
