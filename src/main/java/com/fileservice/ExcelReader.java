package com.fileservice;

import com.entity.StoreAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lutsenko.d on 04.05.2017.
 */
public class ExcelReader {
    private final String FILE_PATH = "C:\\Users\\lutsenko.d\\Documents\\IdeaProjects\\Address-program\\data\\in\\stores.xlsx";
    private List<StoreAddress> addressList;

    public ExcelReader(){
        addressList = new ArrayList<>();
    }


    public void readFile() throws IOException {
        List<StoreAddress> tmpAddressesList = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_PATH));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            List<String> tmpCells = new ArrayList<>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cell.setCellType(Cell.CELL_TYPE_STRING);
                tmpCells.add(cell.getStringCellValue());
            }
            tmpAddressesList.add(new StoreAddress(tmpCells.get(0)));
        }
        workbook.close();
        fileInputStream.close();
        addressList = tmpAddressesList;
    }


    public List<StoreAddress> getAddressList() {
        return addressList;
    }
}
