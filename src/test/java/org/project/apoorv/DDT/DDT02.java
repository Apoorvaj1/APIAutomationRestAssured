package org.project.apoorv.DDT;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DDT02{
    public static String FILE_NAME = "src/test/java/resources/Book1.xlsx";
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream(FILE_NAME);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        XSSFRow row = sheet.getRow(1);
        XSSFCell cell = row.getCell(1);
        String val1 = cell.getStringCellValue();
        System.out.println(val1);

    }
}
