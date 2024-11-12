package com.techgeeknext.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.techgeeknext.model.Employee;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PDFGeneratorUtility {

    public static void employeeDetailReport(HttpServletResponse response, List<Employee> employees) throws IOException {

        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDocument;
        pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        try {
            document.add(new Paragraph("TechGeekNext Example").setBold().setPaddingLeft(200f));

            Table table = new Table(new float[]{20f, 50f, 30F});
            table.setWidthPercent(100)
                    .setPadding(0)
                    .setFontSize(9);

            Cell cell1 = new Cell(1,3);
            cell1.setTextAlignment(TextAlignment.CENTER);
            cell1.add("Employee Details").setBold();
            table.addCell(cell1);

            table.addCell(new Cell().add("Id").setBold());
            table.addCell(new Cell().add("Name").setBold());
            table.addCell(new Cell().add("Role").setBold());

            for(Employee emp:employees) {
                table.addCell(new Cell().add(String.valueOf(emp.getId())));
                table.addCell(new Cell().add(emp.getName()));
                table.addCell(new Cell().add(emp.getRole()));
            }

            document.add(table);

            document.close();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
