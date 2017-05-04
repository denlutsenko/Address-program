package com.fileservice;

import com.entity.StoreAddress;
import com.handler.GpsHandler;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by lutsenko.d on 04.05.2017.
 */
public class ExcelWriter {
    private final Logger LOG = Logger.getLogger(ExcelWriter.class);
    private final String FILE_PATH = "C:\\Users\\lutsenko.d\\Documents\\IdeaProjects\\Address-program\\data\\out\\storesCoordinates.xlsx";
    private final String[] HEADER = new String[]{
            "АДРЕС",
            "ШИРОТА",
            "ДОЛГОТА"
    };

    public void writeToExcel(List<StoreAddress> addressList) {
        try {
            FileOutputStream output = new FileOutputStream(FILE_PATH);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet worksheet = workbook.createSheet("sheet1");

            setExcelHeader(worksheet);
            setExcelRows(worksheet,addressList);

            final FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            evaluator.evaluateAll();

            for (int col = 0; col < HEADER.length; col++) {
                worksheet.autoSizeColumn(col);
            }

            workbook.write(output);
            output.close();
        } catch (IOException e) {
            LOG.error("Can't write to xlsx" + e);
        }
    }


    private void setExcelRows(XSSFSheet worksheet, List<StoreAddress> addressList) {
        int record = 1;
        for (StoreAddress address : addressList) {
            int cellIndex = 0;
            XSSFRow row = worksheet.createRow(record++);

            XSSFCell id = row.createCell(cellIndex++);
            id.setCellValue(address.getStoreAddress());

            XSSFCell name = row.createCell(cellIndex++);
            name.setCellValue(address.getLatitude());

            XSSFCell legalAddr = row.createCell(cellIndex++);
            legalAddr.setCellValue(address.getLongitude());
        }
    }


    private void setExcelHeader(XSSFSheet worksheet) {
        Font font = worksheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        XSSFRow excelHeader = worksheet.createRow(0);
        excelHeader.setHeightInPoints(45);

        int cellIndex = 0;
        XSSFRow row = worksheet.createRow(0);
        for (String cellValue : HEADER) {
            XSSFCell cell = row.createCell(cellIndex++);
            cell.setCellValue(cellValue);
        }
    }
}
