package utils;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExcelUtils {

    private static Logger logger = Logger.getLogger(ExcelUtils.class);
    private static Pattern pCells = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");


    public static List<String[]> readExcel(String fileName, InputStream is, int start, int totalCells) {
        List<String[]> dataList = new ArrayList<String[]>();
        boolean isExcel2003 = true;
        if (isExcel2007(fileName)) {
            isExcel2003 = false;
        }
        Workbook wb = null;
        try {
            wb = isExcel2003 ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
        } catch (IOException ex) {
            logger.error(ex);
        }
        int sheets = wb.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            Sheet sheet = wb.getSheetAt(i);
            Iterator<Row> iterable = sheet.rowIterator();
            int totalRows = sheet.getPhysicalNumberOfRows();
            if (totalRows >= 1 && sheet.getRow(0) != null) {
                if (totalCells == 0) {
                    totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
                }
            }
            int rowNum = 0;
            while (iterable.hasNext()) {
                Row row = iterable.next();
                rowNum++;
                if (rowNum < start) {
                    continue;
                }
                String[] rowList = new String[totalCells];
                for (int c = 0; c < totalCells; c++) {
                    Cell cell = row.getCell(c);
                    String cellValue = "";
                    if (cell == null) {
                        rowList[c] = (cellValue);
                        continue;
                    }
                    cellValue = ConvertCellStr(cell, cellValue);
                    rowList[c] = (cellValue);
                }
                dataList.add(rowList);
            }
        }
        return dataList;
    }

    private static String ConvertCellStr(Cell cell, String cellStr) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                // 读取String
                cellStr = cell.getStringCellValue().toString();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                // 得到Boolean对象的方法
                cellStr = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                // 先看是否是日期格式
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 读取日期格式
                    cellStr = formatTime(cell.getDateCellValue().toString());
                } else {
                    // 读取数字
                    cellStr = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_FORMULA:
                // 读取公式
                cellStr = cell.getCellFormula().toString();
                break;
        }
        return cellStr;
    }

    private static boolean isExcel2007(String fileName) {
        return fileName.matches("^.+\\.(?i)(xlsx)$");
    }

    private static String formatTime(String s) {
        SimpleDateFormat sf = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = sf.parse(s);
        } catch (ParseException ex) {
            logger.error(ex);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = sdf.format(date);
        return result;
    }

    public static List<String[]> readCsv(String csv) {
        InputStreamReader fr = null;
        try {
            fr = new InputStreamReader(new FileInputStream(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String rec = null;// 一行
        String str;// 一个单元格
        List<String[]> listFile = new ArrayList<String[]>();
        try {
            // 读取一行
            while ((rec = br.readLine()) != null) {
                int count = 0;

                Matcher mCells = pCells.matcher(rec);
                String[] cells = new String[10];// 每行记录一个list
                // 读取每个单元格
                while (mCells.find()) {
                    str = mCells.group();
                    str = str.replaceAll("(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
                    str = str.replaceAll("(?sm)(\"(\"))", "$2");
                    if (count == cells.length) {
                        cells = Arrays.copyOf(cells, cells.length + 1);
                    }
                    cells[count++] = str;
                }
                listFile.add(cells);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listFile;
    }


}
