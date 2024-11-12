package com.techgeeknext.controller;

import com.techgeeknext.repository.EmployeeRepository;
import com.techgeeknext.util.PDFGeneratorUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;


        @GetMapping(
                value = "/pdf",
                produces = MediaType.APPLICATION_PDF_VALUE
        )
    public void employeeDetailsReport(HttpServletResponse response) throws IOException {

            DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
            String headerKey = "Content-Disposition";
            String headerVal = "attachment; filename=employee_details_" + dateFormat.format(new Date()) + ".pdf";
            response.setHeader(headerKey, headerVal);

            PDFGeneratorUtility.employeeDetailReport(response,employeeRepository.findAll());
    }

}
