package com.example.mathvisualizer.controller;

import com.example.mathvisualizer.OpenFilePDF;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class ReportController {

    private static BaseFont baseFont = loadBaseFont("file:../../fonts/times.ttf");
    private static Font font = new Font(baseFont, 16, Font.BOLDITALIC, BaseColor.BLUE);
    private static Font font1 = new Font(baseFont, 16, Font.BOLDITALIC, BaseColor.BLACK);
    private static Font font2 = new Font(baseFont, 14, Font.BOLDITALIC, BaseColor.BLACK);

    /**
     * Загружаем шрифт из .ttf файла
     * @param fontName Путь к файлу
     * @return
     */
    private static BaseFont loadBaseFont(String fontName) {
        BaseFont baseFont= null;
        try {
            baseFont = BaseFont.createFont(fontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baseFont;
    }

    public static void createReport(String function, Map<Double, Double> resultMap)
            throws IOException, DocumentException {

        Document document = new Document();
        String fileName = "FunctionReport_" + System.currentTimeMillis() + ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        document.add(new Paragraph("Звіт: Обчислення функції " + function, font));
        document.add(new Paragraph(" ", font1));

        PdfPTable table = new PdfPTable(2);
        table.addCell("X");
        table.addCell("Y");
        for (Map.Entry<Double, Double> entry : resultMap.entrySet()) {
            table.addCell(String.format("%.3f", entry.getKey()));
            table.addCell(String.format("%.5f", entry.getValue()));
        }

        document.add(table);
        document.add(new Paragraph("\nДата створення: " + new java.util.Date(), font2));
        document.close();
        OpenFilePDF.openFile(fileName);
    }
}
