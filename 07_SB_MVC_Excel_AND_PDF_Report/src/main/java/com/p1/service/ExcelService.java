package com.p1.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.p1.entity.Employee;
import com.p1.repo.EmployeeRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ExcelService {

    private final EmployeeRepository employeeRepository;

    public ExcelService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    
    

    // Method to read Excel file and save data to database
    public void saveExcelData(MultipartFile file) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Employee> employees = new ArrayList<>();

            // Skip the header row
            if (rows.hasNext()) {
                rows.next();
            }

            // Read each row and map it to the Employee entity
            while (rows.hasNext()) {
                Row row = rows.next();
                Employee employee = new Employee();
                employee.setName(row.getCell(0).getStringCellValue());
                employee.setEmail(row.getCell(1).getStringCellValue());
                employee.setDepartment(row.getCell(2).getStringCellValue());
                employees.add(employee);
            }

            // Save all employees to the database
            employeeRepository.saveAll(employees);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }
    
    
    
 // Generate Excel file from database data
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<Employee> employees = employeeRepository.findAll();  // Fetch all employees

        // Create a new Excel Workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Email");
        headerRow.createCell(3).setCellValue("Department");

        // Fill data rows
        int rowIndex = 1;
        for (Employee employee : employees) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(employee.getId());
            row.createCell(1).setCellValue(employee.getName());
            row.createCell(2).setCellValue(employee.getEmail());
            row.createCell(3).setCellValue(employee.getDepartment());
        }

        // Set the Content Type and Headers for the HTTP response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employees.xlsx";
        response.setHeader(headerKey, headerValue);

        // Write the Excel data to the HTTP response output stream
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}

