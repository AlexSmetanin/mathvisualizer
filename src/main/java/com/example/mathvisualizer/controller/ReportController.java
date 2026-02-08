package com.example.mathvisualizer.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class ReportController {
    public static void createReport(String function, Map<Double, Double> resultMap)
            throws IOException, DocumentException {

        Document document = new Document();
        String fileName = "FunctionReport_" + System.currentTimeMillis() + ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        document.add(new Paragraph("Звіт: Обчислення функції " + function));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(2);
        table.addCell("X");
        table.addCell("Y");
        for (Map.Entry<Double, Double> entry : resultMap.entrySet()) {
            table.addCell(String.format("%.3f", entry.getKey()));
            table.addCell(String.format("%.5f", entry.getValue()));
        }

        document.add(table);
        document.add(new Paragraph("\nДата створення: " + new java.util.Date()));
        document.close();
    }
}
