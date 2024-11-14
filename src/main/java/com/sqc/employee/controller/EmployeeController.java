package com.sqc.employee.controller;

import com.sqc.employee.dto.ApiResponse;
import com.sqc.employee.exception.ErrorCode;
import com.sqc.employee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(UUID.randomUUID(), "Nguyen Van A", LocalDate.of(1990, 1, 2), Employee.Gender.MALE, 15555555, "0123456789"),
                    new Employee(UUID.randomUUID(), "Nguyen Van B", LocalDate.of(2000, 2, 3), Employee.Gender.FEMALE, 1000000, "0987654321"),
                    new Employee(UUID.randomUUID(), "Nguyen Van C", LocalDate.of(1998, 1, 4), Employee.Gender.OTHER, 200000, "012987789")
            )
    );

    // get all
    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> employeeAll() {
        return ResponseEntity.ok(new ApiResponse<>(200, "thành cong", employees));
    }


    // get theo id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> employeeId(@PathVariable("id") UUID id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))  // Tìm nhân viên theo id
                .findFirst()  // Lấy nhân viên đầu tiên tìm được
                .map(employee -> ResponseEntity.ok(
                        ApiResponse.<Employee>builder()  // Sử dụng builder để tạo ApiResponse
                                .code(200)  // Mã trạng thái
                                .message("Thành công")  // Thông báo
                                .data(employee)  // Truyền đối tượng Employee vào data
                                .build()  // Xây dựng đối tượng ApiResponse
                ))
                .orElse(ResponseEntity.status(ErrorCode.STUDENT_NOT_EXIT.getStatus()).body(
                        ApiResponse.<Employee>builder()
                                .code(ErrorCode.STUDENT_NOT_EXIT.getCode())
                                .message(ErrorCode.STUDENT_NOT_EXIT.getMessage())
                                .build()
                )); 
    }


    // create
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }


    //update
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") UUID id, @RequestBody Employee employee) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    e.setName(employee.getName());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    e.setBirthDate(employee.getBirthDate());
                    e.setPhoneNumber(employee.getPhoneNumber());
                    return ResponseEntity.ok(e);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") UUID id) {
        if (employees.removeIf(e -> e.getId().equals(id))) {
            return ResponseEntity.ok("Đã xóa thành công");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
