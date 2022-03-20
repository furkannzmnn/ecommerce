package com.base.ecommerce.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class ExcelUtil {
    private static final DataFormatter formatter = new DataFormatter(Locale.forLanguageTag("tr"));

    public static Map<Integer,String> getExcelHeaderMapper(Iterator<Row> rowIterator){

        Map<Integer,String> keyIndex = new HashMap<>();

        final Row headerRow = rowIterator.next();
        final Iterator<Cell> cellIterator = headerRow.cellIterator();

        int index = 0;

        while (cellIterator.hasNext()){
            final Cell cell = cellIterator.next();
            keyIndex.put(index++, cell.getStringCellValue());
        }
        return keyIndex;
    }

    public static Map<String, Object> mapRowData(Map<Integer, String> keysIndex, Row row){

        Map<String, Object> dataHolder = new HashMap<>();

        for (Map.Entry<Integer, String> entry : keysIndex.entrySet()) {
            final String value = formatter.formatCellValue(row.getCell(entry.getKey()));

            if (StringUtils.isNotBlank(value)) {
                dataHolder.put(entry.getValue(), value);
            } else {
                dataHolder.put(entry.getValue(), null);
            }

        }
        return dataHolder;
    }


}
