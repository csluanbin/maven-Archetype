package com.maven.luanbin.archetype.util;

/**
 * Created by luanbin on 17/6/23.
 */

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
    public static int MAX_BLANK_Line = 100;

    public ExcelUtil() {
    }

    private static Workbook createWorkBook(ExcelUtil.ExcelFileType fileType, String sheetName, List<LinkedHashMap<String, Object>> list) {
        return createWorkBook(fileType, sheetName, false, list);
    }

    private static Workbook createWorkBook(ExcelUtil.ExcelFileType fileType, String sheetName, boolean useStream, List<LinkedHashMap<String, Object>> list) {
        if(list.size() == 0) {
            throw new RuntimeException("无任何数据,无法生成excel");
        } else {
            Object workbook;
            if(fileType != null && fileType != ExcelUtil.ExcelFileType.XLSX) {
                workbook = new HSSFWorkbook();
            } else {
                workbook = useStream?new SXSSFWorkbook():new XSSFWorkbook();
            }

            String[] keys = (String[])((LinkedHashMap)list.get(0)).keySet().toArray(new String[((LinkedHashMap)list.get(0)).keySet().size()]);
            String[] columnNames = keys;
            Sheet sheet = ((Workbook)workbook).createSheet(sheetName);

            for(int row = 0; row < keys.length; ++row) {
                sheet.setColumnWidth(row, getColumnWidth(keys[row]));
            }

            Row var21 = sheet.createRow(0);
            CellStyle headStyle = ((Workbook)workbook).createCellStyle();
            CellStyle textColumStyle = ((Workbook)workbook).createCellStyle();
            CellStyle numberColumStyle = ((Workbook)workbook).createCellStyle();
            Font headFont = ((Workbook)workbook).createFont();
            Font columnFont = ((Workbook)workbook).createFont();
            headFont.setFontHeightInPoints((short)10);
            headFont.setColor(IndexedColors.BLACK.getIndex());
            headFont.setBoldweight((short)700);
            columnFont.setFontHeightInPoints((short)10);
            columnFont.setColor(IndexedColors.BLACK.getIndex());
            headStyle.setFont(headFont);
            headStyle.setBorderLeft((short)1);
            headStyle.setBorderRight((short)1);
            headStyle.setBorderTop((short)1);
            headStyle.setBorderBottom((short)1);
            headStyle.setAlignment((short)2);
            textColumStyle.setFont(columnFont);
            textColumStyle.setBorderLeft((short)1);
            textColumStyle.setBorderRight((short)1);
            textColumStyle.setBorderTop((short)1);
            textColumStyle.setBorderBottom((short)1);
            textColumStyle.setAlignment((short)2);
            numberColumStyle.setFont(columnFont);
            numberColumStyle.setBorderLeft((short)1);
            numberColumStyle.setBorderRight((short)1);
            numberColumStyle.setBorderTop((short)1);
            numberColumStyle.setBorderBottom((short)1);
            numberColumStyle.setAlignment((short)2);
            numberColumStyle.setDataFormat((short)BuiltinFormats.getBuiltinFormat("0.00"));

            int i;
            for(i = 0; i < columnNames.length; ++i) {
                Cell row1 = var21.createCell(i);
                row1.setCellValue(columnNames[i]);
                row1.setCellStyle(headStyle);
            }

            for(i = 0; i < list.size(); ++i) {
                Row var22 = sheet.createRow(i + 1);

                for(int j = 0; j < keys.length; ++j) {
                    Cell cell = var22.createCell(j);
                    String columnName = keys[j];
                    String cellValue = ((LinkedHashMap)list.get(i)).get(columnName) == null?"":((LinkedHashMap)list.get(i)).get(columnName).toString();
                    if(!columnName.contains("金额") && !columnName.contains("手续费")) {
                        cell.setCellValue(cellValue);
                        cell.setCellStyle(textColumStyle);
                    } else {
                        BigDecimal number = new BigDecimal(cellValue);
                        cell.setCellValue(number.doubleValue());
                        cell.setCellStyle(numberColumStyle);
                    }
                }
            }

            return (Workbook)workbook;
        }
    }

    private static int getColumnWidth(String key) {
        int width = 0;
        String[] var2 = key.split("");
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String b = var2[var4];
            if(b.getBytes().length == 1) {
                width += 280;
            } else {
                width += 525;
            }
        }

        if((double)width < 3570.0000000000005D) {
            width = 3500;
        }

        if((double)width > 7140.000000000001D) {
            width = 7500;
        }

        return width;
    }

    private static ExcelUtil.ExcelFileType getFileType(String fileName) {
        ExcelUtil.ExcelFileType fileType;
        if(fileName.toLowerCase().endsWith(".xls")) {
            fileType = ExcelUtil.ExcelFileType.XLS;
        } else {
            fileType = ExcelUtil.ExcelFileType.XLSX;
        }

        return fileType;
    }

    public static void saveExcel(String filePath, String sheetName, List<LinkedHashMap<String, Object>> list) {
        saveExcel(filePath, sheetName, false, list);
    }

    public static void saveExcel(String filePath, String sheetName, boolean useStream, List<LinkedHashMap<String, Object>> list) {
        try {
            FileOutputStream e = new FileOutputStream(filePath);

            try {
                createWorkBook(getFileType(filePath), sheetName, useStream, list).write(e);
            } finally {
                e.flush();
                e.close();
            }

        } catch (Exception var9) {
            throw new RuntimeException(var9);
        }
    }

    public static void downLoadExcel(HttpServletResponse response, List<LinkedHashMap<String, Object>> list) {
        String fileName = System.currentTimeMillis() + ".xlsx";
        downLoadExcel(response, fileName, "sheet1", list);
    }

    public static void downLoadExcel(HttpServletResponse response, String fileName, String sheetName, List<LinkedHashMap<String, Object>> list) {
        try {
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            BufferedOutputStream ex = new BufferedOutputStream(response.getOutputStream());

            try {
                createWorkBook(getFileType(fileName), sheetName, list).write(ex);
            } finally {
                ex.flush();
                ex.close();
            }

        } catch (Exception var9) {
            throw new RuntimeException(var9);
        }
    }

    public static List<String> getSheetNames(String path) {
        try {
            Workbook e = WorkbookFactory.create(new File(path));
            return getSheetNames(e);
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    public static List<Map<String, String>> readExcelSheet(String path, int sheetIndex) {
        return readExcelSheet((String)path, sheetIndex, 0);
    }

    public static List<Map<String, String>> readExcelSheet(String path, int sheetIndex, int headLine) {
        try {
            return readExcelSheet((InputStream)(new FileInputStream(new File(path))), sheetIndex, headLine);
        } catch (FileNotFoundException var4) {
            throw new RuntimeException(var4.getMessage());
        }
    }

    public static List<Map<String, String>> readExcelSheet(String path, String sheetName) {
        return readExcelSheet((String)path, sheetName, 0);
    }

    public static List<Map<String, String>> readExcelSheet(String path, String sheetName, int headLine) {
        try {
            return readExcelSheet((InputStream)(new FileInputStream(new File(path))), sheetName, headLine);
        } catch (FileNotFoundException var4) {
            throw new RuntimeException(var4.getMessage());
        }
    }

    public static List<Map<String, String>> readExcelSheet(InputStream inputStream, String sheetName, int headLine) {
        try {
            Workbook e = WorkbookFactory.create(inputStream);

            List var5;
            try {
                List sheetMapData = getSheetData(getSheet(e, sheetName), headLine);
                var5 = sheetMapData;
            } finally {
                inputStream.close();
            }

            return var5;
        } catch (Exception var10) {
            throw new RuntimeException(var10);
        }
    }

    public static Map<String, List<Map<String, String>>> readExcel(String path) {
        return readExcel((String)path, 0);
    }

    public static Map<String, List<Map<String, String>>> readExcel(InputStream inputStream, int headLine) {
        try {
            Workbook e = WorkbookFactory.create(inputStream);

            try {
                List sheetNames = getSheetNames(e);
                HashMap sheetMap = new HashMap();
                Iterator var5 = sheetNames.iterator();

                while(var5.hasNext()) {
                    String sheetName = (String)var5.next();
                    List sheetMapData = getSheetData(getSheet(e, sheetName), headLine);
                    sheetMap.put(sheetName, sheetMapData);
                }

                HashMap var13 = sheetMap;
                return var13;
            } finally {
                inputStream.close();
            }
        } catch (Exception var12) {
            throw new RuntimeException(var12);
        }
    }

    public static List<Map<String, String>> readExcelSheet(InputStream inputStream, int sheetIndex, int headLine) {
        try {
            Workbook e = WorkbookFactory.create(inputStream);

            List var4;
            try {
                var4 = getSheetData(getSheet(e, e.getSheetName(sheetIndex)), headLine);
            } finally {
                inputStream.close();
            }

            return var4;
        } catch (Exception var9) {
            throw new RuntimeException(var9);
        }
    }

    public static Map<String, List<Map<String, String>>> readExcel(String path, int headLine) {
        try {
            Workbook e = WorkbookFactory.create(new File(path));

            try {
                List sheetNames = getSheetNames(e);
                HashMap sheetMap = new HashMap();
                Iterator var5 = sheetNames.iterator();

                while(var5.hasNext()) {
                    String sheetName = (String)var5.next();
                    List sheetMapData = getSheetData(getSheet(e, sheetName), headLine);
                    sheetMap.put(sheetName, sheetMapData);
                }

                HashMap var13 = sheetMap;
                return var13;
            } finally {
            }
        } catch (Exception var12) {
            throw new RuntimeException(var12);
        }
    }

    private static List<Map<String, String>> getSheetData(Sheet sheet, int headLine) {
        if(sheet == null) {
            throw new RuntimeException("sheet不存在");
        } else {
            try {
                ArrayList e = new ArrayList();
                Row blankLine;
                int mapList;
                if(headLine >= 0) {
                    blankLine = sheet.getRow(headLine);

                    for(mapList = blankLine.getFirstCellNum(); mapList < blankLine.getLastCellNum(); ++mapList) {
                        Cell rowNum = blankLine.getCell(mapList);
                        String row = getCellValue(rowNum);
                        if(e.contains(row)) {
                            row = row + " column" + mapList;
                        }

                        e.add(row);
                    }
                } else {
                    headLine = -1;
                    blankLine = sheet.getRow(sheet.getFirstRowNum());

                    for(mapList = blankLine.getFirstCellNum(); mapList < blankLine.getLastCellNum(); ++mapList) {
                        e.add("column" + mapList);
                    }
                }

                int var13 = 0;
                ArrayList var14 = new ArrayList(sheet.getLastRowNum());

                for(int var15 = headLine + 1; var15 <= sheet.getLastRowNum(); ++var15) {
                    Row var16 = sheet.getRow(var15);
                    if(var16 != null) {
                        HashMap map = new HashMap();
                        StringBuilder sb = new StringBuilder(128);

                        for(int cellNum = 0; cellNum < e.size(); ++cellNum) {
                            Cell cell = var16.getCell(cellNum);
                            String value = getCellValue(cell);
                            if(value == null) {
                                value = "";
                            }

                            map.put(e.get(cellNum), value);
                            sb.append(value);
                        }

                        if(sb.length() > 0) {
                            var14.add(map);
                        } else {
                            ++var13;
                            if(var13 > MAX_BLANK_Line) {
                                return var14;
                            }
                        }
                    }
                }

                return var14;
            } catch (Exception var12) {
                throw new RuntimeException(var12);
            }
        }
    }

    private static List<String> getSheetNames(Workbook workbook) {
        try {
            ArrayList e = new ArrayList(workbook.getNumberOfSheets());

            for(int i = 0; i < workbook.getNumberOfSheets(); ++i) {
                e.add(workbook.getSheetName(i));
            }

            return e;
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    private static String getCellValue(Cell cell) {
        try {
            if(cell == null) {
                return null;
            } else {
                String e;
                switch(cell.getCellType()) {
                    case 0:
                        DecimalFormat df = new DecimalFormat("#.##");
                        e = df.format(cell.getNumericCellValue());
                        break;
                    case 1:
                        e = cell.getStringCellValue();
                        break;
                    case 2:
                        try {
                            e = String.valueOf(cell.getNumericCellValue());
                        } catch (Exception var3) {
                            e = cell.getRichStringCellValue().getString();
                        }
                        break;
                    case 3:
                    default:
                        e = cell.getRichStringCellValue().getString();
                        break;
                    case 4:
                        e = String.valueOf(cell.getBooleanCellValue());
                }

                return e;
            }
        } catch (Exception var4) {
            throw new RuntimeException(String.format("第%d行数据异常:%s", Integer.valueOf(cell.getRowIndex() + 1), var4.getMessage()));
        }
    }

    private static Sheet getSheet(Workbook workbook, String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if(sheet == null) {
            throw new RuntimeException("sheet:" + sheetName + "不存在");
        } else {
            return sheet;
        }
    }

    public static enum ExcelFileType {
        XLS,
        XLSX;

        private ExcelFileType() {
        }
    }
}


