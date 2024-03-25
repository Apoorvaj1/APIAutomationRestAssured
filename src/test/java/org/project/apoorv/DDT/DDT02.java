package org.project.apoorv.DDT;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class DDT02 {
    public static String FILE_NAME = "src/test/java/resources/Book1.xlsx";

    public static Object[][] getTestData(String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(FILE_NAME);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        //XSSFRow row = sheet.getRow(1);
        //XSSFCell cell = row.getCell(1);
        //String val1 = cell.getStringCellValue();
        //System.out.println(val1);
        System.out.println(sheet.getLastRowNum()); //2

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows - 1][cols];
        /*for(int i=1;i<rows;i++){
            for(int j=0;j<cols;j++){
                System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
            }
            System.out.println();
        }*/

        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = sheet.getRow(i + 1).getCell(j).getStringCellValue();
            }
            System.out.println();
        }
        return data;
        /*for(Object[] arr:data){
            System.out.println(Arrays.toString(arr));
        }*/

    }
    @DataProvider
    public Object[][] getData() throws IOException {
        return getTestData("Sheet1");
    }
}
