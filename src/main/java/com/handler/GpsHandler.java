package com.handler;

import com.entity.StoreAddress;
import com.fileservice.ExcelReader;
import com.fileservice.ExcelWriter;
import com.geocoding.GeoCoding;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Created by lutsenko.d on 04.05.2017.
 */
public class GpsHandler {
    private final Logger LOG = Logger.getLogger(GpsHandler.class);
    private List<StoreAddress> addressList;
    private ExcelReader exReader = new ExcelReader();
    private ExcelWriter exWriter = new ExcelWriter();
    private GeoCoding geoCoding = new GeoCoding();

    public GpsHandler() {
        exReader = new ExcelReader();
        exWriter = new ExcelWriter();
        geoCoding = new GeoCoding();
    }

    public void findAndSaveCoordinates() {
        try {
            exReader.readFile();
            addressList = geoCoding.findCoordinates(exReader.getAddressList());
            exWriter.writeToExcel(addressList);
        } catch (IOException e) {
            LOG.error("can't read xls file" + e + "\n");
        }
    }
}
