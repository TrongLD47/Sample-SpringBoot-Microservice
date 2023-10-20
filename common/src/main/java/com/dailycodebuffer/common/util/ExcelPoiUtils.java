package com.dailycodebuffer.common.util;


import com.dailycodebuffer.common.dto.CellValue;
import com.dailycodebuffer.common.dto.CellDataType;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
public class ExcelPoiUtils {
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        switch (cell.getCellType()) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                cellValue = Boolean.toString(cell.getBooleanCellValue());
                break;
            case NUMERIC:
                cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
                break;
            case FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case STRING:
                        cellValue = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
                        break;
                }
        }
        return cellValue;
    }

    /**
     * Helps in obtaining cell for given row, if it does not exits it will create
     *
     * @param row - Row
     * @param cellNo - cell number
     * @return Cell
     */
    public static Cell getCell(final Row row, final int cellNo) {
        Cell cell = row.getCell(cellNo);
        if (null == cell) {
            cell = row.createCell(cellNo);
        }
        return cell;
    }

    /**
     * Helps in obtaining row for given sheet, if it does not exits it will create
     *
     * @param sheet - Sheet
     * @param rowNo - row number
     * @return Row
     */
    public static Row getRow(final Sheet sheet, final int rowNo) {
        Row row = sheet.getRow(rowNo);
        if (null == row) {
            row = sheet.createRow(rowNo);
        }
        return row;
    }

    /**
     * Helps in obtaining the next available header cell
     *
     * @param sheet - Sheet
     * @return Cell
     */
    public static Cell getNextAvailableHeaderCell(final Sheet sheet) {
        final Row headerRow = getRow(sheet, 0);
        Cell headerCell = null;
        int i = 0;
        do {
            headerCell = getCell(headerRow, i++);
        } while (!StringUtils.isEmpty(headerCell.getStringCellValue()));
        return headerCell;
    }

    /**
     * Helps in obtaining the next available row
     *
     * @param sheet
     * @return Row
     */
    public static Row getNextAvailableRow(final Sheet sheet) {
        Row row = null;
        int i = 0;
        do {
            row = getRow(sheet, i++);
        } while (!StringUtils.isEmpty(getCell(row, 0).getStringCellValue()));
        return row;
    }

    /**
     * Helps in populating the column of the given cell with the given data and style
     *
     * @param cell - Cell which will be taken as reference
     * @param columnData - List<String> is the data that needs to be populated
     * @param cellStyle - style to be applied
     */
    public static void populateColumnOfCell(final Cell cell, final List<String> columnData, final CellStyle cellStyle) {
        final Sheet sheet = cell.getSheet();
        final int columnNumber = cell.getColumnIndex();
        for (int i = cell.getRowIndex(), j = 0; j < columnData.size(); i++, j++) {
            final Cell tempCell = getCell(getRow(sheet, i), columnNumber);
            tempCell.setCellStyle(cellStyle);
            tempCell.setCellValue(columnData.get(i));
        }
        sheet.autoSizeColumn(columnNumber);
    }

    /**
     * Helps in populating the row of the given Row with the given data and style
     *
     * @param row - Row which will be populated
     * @param data - List<String> is the data that needs to be populated
     * @param style - style to be applied
     */
    public static void populateRowWithList(final Row row, final List<String> data, final CellStyle style) {
        for (int i = 0; i < data.size(); i++) {
            final Cell tempCell = getCell(row, i);
            tempCell.setCellValue(data.get(i));
            tempCell.setCellStyle(style);
            tempCell.getSheet().autoSizeColumn(i);
        }
    }

    /**
     * Helps in marking the Named Range with the given details with reference to the given Cell
     *
     * @param cell - Reference of the cell from where the coordinates are considered
     * @param rows - Number of rows to be covered
     * @param columns - Number of columns to be covered
     * @param rangeName - Name to be given for the Named Range
     * @param skipfirst - Should skip the given Cell row or not
     */
    public static void markNamedRange(final Cell cell, final int rows, final int columns, final String rangeName,
                                      final boolean skipfirst) {
        final CellReference cellReference = new CellReference(cell);
        final String[] cellPosition = cellReference.getCellRefParts();

        final String cellRowRef = cellPosition[1];
        final int startCellRowRef = skipfirst ? Integer.parseInt(cellRowRef) + 1 : Integer.parseInt(cellRowRef);
        final int endCellRowRef = startCellRowRef + (skipfirst ? rows - 1 : rows);

        final String cellColRef = cellPosition[2];
        final int endCellColRef = CellReference.convertColStringToIndex(cellColRef) + columns;
        final String endCellColRefS = CellReference.convertNumToColString(endCellColRef);

        final String reference =
                cell.getSheet().getSheetName() + "!$" + cellColRef + "$" + startCellRowRef + ":$" + endCellColRefS + "$" + endCellRowRef;

        final Workbook workbook = cell.getSheet().getWorkbook();
        Name name = workbook.getName(rangeName);
        if (null == name) {
            name = workbook.createName();
            name.setNameName(rangeName);
        }
        name.setRefersToFormula(reference);
    }

    /**
     * Helps in obtaining the common style used in dynamic sheets
     *
     * @param workbook
     * @return CellStyle
     */
    public static CellStyle getCellStyle(final Workbook workbook) {
        final CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }

    public static CellRangeAddress mergeCell(XSSFSheet sheet, int firstRow, int lastRow, int firstCol, int lastCol){
        CellRangeAddress cellRangeAddress = new CellRangeAddress(
                firstRow, //first row (0-based)
                lastRow, //last row  (0-based)
                firstCol, //first column (0-based)
                lastCol  //last column  (0-based)
        );
        sheet.addMergedRegion(cellRangeAddress);
        return cellRangeAddress;
    }

    public static void setBorderRegion(BorderStyle borderStyle, CellRangeAddress region, Sheet sheet){
        RegionUtil.setBorderBottom(borderStyle, region, sheet);
        RegionUtil.setBorderTop(borderStyle, region, sheet);
        RegionUtil.setBorderLeft(borderStyle, region, sheet);
        RegionUtil.setBorderRight(borderStyle, region, sheet);
    }

    public static Cell addCell(final Row row, final int cellNo, String cellValue, CellStyle styleCell){
        Cell cell = row.createCell(cellNo);
        cell.setCellValue(cellValue);
        cell.setCellStyle(styleCell);
        return cell;
    }

    public static Cell addRegion(final Row row, final int cellNo, String cellValue, CellStyle styleCell, BorderStyle border, XSSFSheet sheet,
                                 int firstRow, int lastRow, int firstCol, int lastCol) {
        Cell cell = row.createCell(cellNo);
        cell.setCellValue(cellValue);
        cell.setCellStyle(styleCell);
        CellRangeAddress cellRangeAddress = new CellRangeAddress(
                firstRow, //first row (0-based)
                lastRow, //last row  (0-based)
                firstCol, //first column (0-based)
                lastCol  //last column  (0-based)
        );
        sheet.addMergedRegion(cellRangeAddress);
        RegionUtil.setBorderBottom(border, cellRangeAddress, sheet);
        RegionUtil.setBorderTop(border, cellRangeAddress, sheet);
        RegionUtil.setBorderLeft(border, cellRangeAddress, sheet);
        RegionUtil.setBorderRight(border, cellRangeAddress, sheet);
        return cell;
    }

    public static void addRowAtCol(XSSFSheet sheet, int startCol, int startRow, CellValue[] cellValues, CellStyle cellStyleString, CellStyle cellStyleInteger, CellStyle cellStyleDouble, CellStyle cellStyleDate) {
        Row row = sheet.createRow(startRow);
        Cell cell;
        int col = startCol;
        for (int i = 0; i < cellValues.length; i++) {
            cell = row.createCell(col++);
            if (cellValues[i] != null && cellValues[i].getValue() != null) {
                if (cellValues[i].getType().equals(CellDataType.STRING)) {
                    cell.setCellValue(String.valueOf(cellValues[i].getValue()));
                    cell.setCellStyle(cellStyleString);
                }else if (cellValues[i].getType().equals(CellDataType.INT)) {
                    cell.setCellValue((Integer)cellValues[i].getValue());
                    cell.setCellStyle(cellStyleInteger);
                } else if (cellValues[i].getType().equals(CellDataType.DOUBLE)) {
                    cell.setCellValue((Double)cellValues[i].getValue());
                    cell.setCellStyle(cellStyleDouble);
                } else if (cellValues[i].getType().equals(CellDataType.DATE)) {
                    Date now = new Date(((Timestamp)cellValues[i].getValue()).getTime());
                    cell.setCellValue(now);
                    cell.setCellStyle(cellStyleDate);
                }
            }else{
                cell.setCellValue("");
                cell.setCellStyle(cellStyleString);
            }
        }
    }

    public static String getCellValueImportString(Cell cell) {
        return StringUtils.defaultString(getCellValue(cell));
    }

    public static Long getCellValueImportLong(Cell cell) {
        if (StringUtils.isNotBlank(getCellValue(cell))) {
            try {
                return Long.parseLong(getCellValue(cell).replace(",", ""));
            } catch (NumberFormatException ex) {
                return -999L;
            }
        } else {
            return 0L;
        }
    }

    public static Double getCellValueImportDouble(Cell cell) {
        if (StringUtils.isNotBlank(getCellValue(cell))) {
            try {
                return Double.parseDouble(getCellValue(cell).replace(",", ""));
            } catch (NumberFormatException ex) {
                return -999D;
            }
        } else {
            return 0D;
        }
    }

    public static Integer getCellValueImportInteger(Cell cell) {
        if (StringUtils.isNotBlank(getCellValue(cell)) ) {
            if (StringUtils.isNumeric(getCellValue(cell))) {
                return Integer.parseInt(getCellValue(cell).replace(",", ""));
            } else {
                return -999;
            }
        } else {
            return 0;
        }
    }
    public static XSSFCellStyle getXSSFCellStyle(final XSSFWorkbook workbook) {
        final XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }

    public static Boolean notIsEndRow(Row row) {
        Boolean result = Boolean.FALSE;
        for (int i=1 ; i < 15; i++) {
            String val = ExcelPoiUtils.getCellValueImportString(row.getCell(i));
            if (StringUtils.isNotBlank(val)) {
                result = Boolean.TRUE;
            }
        }
        return result;
    }

    public static String outputStream2File(Workbook workbook, String export2FileName, String fileName, String error) {
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(export2FileName));
            workbook.write(outputStream);
            outputStream.close();
//            return fileName; // code mẫu
            return export2FileName;
        } catch (Exception ex) {
            System.out.println(error + ex.getMessage());
            return "error";
        }
    }
}
