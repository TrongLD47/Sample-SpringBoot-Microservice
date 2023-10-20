package com.dailycodebuffer.department.service.impl;

import com.dailycodebuffer.common.common.CommonConstants;
import com.dailycodebuffer.common.util.ExcelPoiUtils;
import com.dailycodebuffer.common.util.FileUtils;
import com.dailycodebuffer.department.command.DepartmentCommand;
import com.dailycodebuffer.department.dto.DepartmentDTO;
import com.dailycodebuffer.department.entity.DepartmentEntity;
import com.dailycodebuffer.department.respository.DepartmentRepository;
import com.dailycodebuffer.department.service.DepartmentService;
import com.dailycodebuffer.department.service.mapper.DepartmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileDeleteStrategy;
import org.apache.poi.ss.usermodel.*;
import org.springframework.context.support.ApplicationObjectSupport;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Slf4j
public class DepartmentServiceImpl extends ApplicationObjectSupport implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO department) {
        log.info("Inside saveDepartment of DepartmentService");
        DepartmentEntity entity = departmentMapper.toEntity(department);
        return departmentMapper.toDto(departmentRepository.save(entity));
    }
    @Override
    public DepartmentDTO findDepartmentById(Long departmentId) {
        log.info("Inside findDepartmentById of DepartmentService");
        return departmentMapper.toDto(departmentRepository.findByDepartmentId(departmentId));
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String code) {
        DepartmentEntity department = departmentRepository.findByDepartmentCode(code);

        DepartmentDTO departmentDto = departmentMapper.toDto(department);

        return departmentDto;
    }

    @Override
    public String exportExcel() throws IOException {
        // Tạo một Workbook mới
        Workbook workbook = new XSSFWorkbook();

        // Tạo một Sheet mới
        Sheet sheet = workbook.createSheet("Data");

        // Tạo dữ liệu mẫu (điều này có thể được thay thế bằng dữ liệu thực tế của bạn)
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Tên");
        headerRow.createCell(2).setCellValue("Tuổi");

        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(1);
        dataRow.createCell(1).setCellValue("John");
        dataRow.createCell(2).setCellValue(30);

        // Lưu Workbook vào một tệp Excel]
        SimpleDateFormat df = new SimpleDateFormat(CommonConstants.DATE_FORMAT_VI_EXPORT);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String fileName = "data-export-" + df.format(now) + "_" + System.currentTimeMillis() + ".xlsx";

        String currentPath = System.getProperty("user.dir") + CommonConstants.FILE_TEMPLATE_PATH + "temp/";
        Path filePath = Paths.get(currentPath, fileName);
        try {
            // Trả về URL để tải tệp Excel
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            Resource resource = new FileSystemResource(filePath.toString());
            if (resource.exists() && resource.isReadable()) {
                String fileDownloadUrl = "/download/" + filePath.getFileName(); // Lấy tên tệp
                return "Tệp Excel đã được tạo và có thể được tải về tại đường dẫn: " + fileDownloadUrl;
            } else {
                return "Không thể tạo tệp Excel hoặc có lỗi khi truy cập tệp.";
            }
        } catch (Exception e) {
            log.error(" AN ERROR OCURR WHEN TRING EXPORT FILE " + e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public String ExportData(List<DepartmentDTO> dtoList, HttpServletRequest request) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat(CommonConstants.DATE_FORMAT_VI_EXPORT);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String fileName = "data-export-" + df.format(now) + "_" + System.currentTimeMillis() + ".xlsx";
        String currentPath = System.getProperty("user.dir") + CommonConstants.FILE_TEMPLATE_PATH;

        String report_template = currentPath + "template-export/nodata.xlsx";
        String export2FileName = currentPath + "temp/" + fileName ;

//        String report_template = request.getSession().getServletContext().getRealPath("/file/template-export/" + locale + "/import_template.xlsx");
//        String export2FileName = request.getSession().getServletContext().getRealPath(fileName);

//        File files = Files.createTempDirectory(null).toFile();
//        command.getFileUpload().transferTo(files); dataType: MultipartFile

        FileInputStream fileInputStream = new FileInputStream(report_template); // or replace files == new File(report_template)
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        // Tạo một Sheet mới
//        Sheet sheet = workbook.createSheet("Data");
        Sheet sheet = workbook.getSheetAt(0);
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(false);

        CellStyle cellStyleHeader = workbook.createCellStyle();
        cellStyleHeader.setBorderLeft(BorderStyle.THIN);
        cellStyleHeader.setBorderRight(BorderStyle.THIN);
        cellStyleHeader.setBorderTop(BorderStyle.THIN);
        cellStyleHeader.setBorderBottom(BorderStyle.THIN);
        cellStyleHeader.setFillForegroundColor(IndexedColors.LIME.getIndex());
        cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
        cellStyleHeader.setLocked(true);

        Cell cellWarehouse = sheet.createRow(0).createCell(0);
        cellWarehouse.setCellValue("Dong dau tien");

        Integer rowIndex = 1;
        Row row = sheet.createRow(rowIndex++);

        //set headers to excel
        ExcelPoiUtils.addCell(row, 0, "Name", cellStyleHeader);
        ExcelPoiUtils.addCell(row, 1, "Phone", cellStyleHeader);
        ExcelPoiUtils.addCell(row, 2, "Email", cellStyleHeader);
        ExcelPoiUtils.addCell(row, 3, "Address", cellStyleHeader);

//        ExcelPoiUtil.addCell(row, 0, this.getMessageSourceAccessor().getMessage("label.user.name"), cellStyleHeader);
//        ExcelPoiUtil.addCell(row, 1, this.getMessageSourceAccessor().getMessage("label.user.phoneNumber"), cellStyleHeader);
//        ExcelPoiUtil.addCell(row, 2, this.getMessageSourceAccessor().getMessage("label.user.email"), cellStyleHeader);
//        ExcelPoiUtil.addCell(row, 3, this.getMessageSourceAccessor().getMessage("label.user.address"), cellStyleHeader);

        //data
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(false);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);

        //write content
        int cellData;
        for(DepartmentDTO dto : dtoList) {
            cellData = 0;
            Row rowData = sheet.createRow(rowIndex++);
            ExcelPoiUtils.addCell(rowData, cellData++, dto.getDepartmentName(), cellStyle);
            ExcelPoiUtils.addCell(rowData, cellData++, dto.getDepartmentCode(), cellStyle);
            ExcelPoiUtils.addCell(rowData, cellData++, dto.getDepartmentAddress(), cellStyle);
            ExcelPoiUtils.addCell(rowData, cellData++, dto.getDepartmentDescription(), cellStyle);
            ExcelPoiUtils.addCell(rowData, cellData++, dto.getCreateDate() != null ? df.format(dto.getCreateDate()) : null, cellStyle);
        }
        // set auto width
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        ExcelPoiUtils.outputStream2File(workbook, export2FileName, fileName, " === Export error!!! === ");
        workbook.close();
        return export2FileName;
    }


    private File extractToZipFile(DepartmentCommand command, HttpServletRequest request) throws IOException {
        long kilobyte = 1024;
        long megabyte = kilobyte * 1024;

        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HHmmss");
        String date = sdf.format(System.currentTimeMillis());
        String folder = "import_stockout_outlet_" + date;
        String jbossTempFolderPath = System.getProperty("jboss.server.temp.dir");
        File file = new File(jbossTempFolderPath + File.separator + folder);
        if (!file.exists()) {
            file.mkdirs();
        }
        File stockinFolder = new File(file.getPath() + File.separator + "stockout");
        if (!stockinFolder.exists()) {
            stockinFolder.mkdirs();
        }
        String tempFolder = stockinFolder.getPath() + File.separator + request.getSession().getId();
        FileUtils.writeMultipartFileToTempDir(tempFolder, command.getFileUpLoad());

        long bytes = Files.size(Paths.get(tempFolder + File.separator + command.getFileUpLoad().getOriginalFilename()));
        if (bytes / megabyte > CommonConstants.MAX_FILE_UPLOAD_SIZE_MB) {
            FileDeleteStrategy.FORCE.delete(file);
            return null;
        } else {
            String fileName = command.getFileUpLoad().getOriginalFilename();
            String importFilePath = tempFolder + File.separator + fileName;
            File excelFile = new File(importFilePath);
            if (excelFile.exists()) {
                return excelFile;
            } else {
                FileDeleteStrategy.FORCE.delete(file);
            }
        }
        return null;
    }

}
