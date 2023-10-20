package com.dailycodebuffer.department.controller;

import com.dailycodebuffer.department.dto.DepartmentDTO;
import com.dailycodebuffer.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public DepartmentDTO saveDepartment(@RequestBody DepartmentDTO department) {
        log.info("Inside saveDepartment method of DepartmentController");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public DepartmentDTO findDepartmentById(@PathVariable("id") Long departmentId) {
        System.out.println("okokokokokokokoko11111");
        log.info("Inside findDepartmentById method of DepartmentController");
        return departmentService.findDepartmentById(departmentId);
    }

    // Build get department rest api
    @GetMapping("/ok/{department-code}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("department-code") String departmentCode){
        DepartmentDTO departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportfile(HttpServletRequest request) throws Exception {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DepartmentDTO dto1 = new DepartmentDTO(1L, "Department1", "Da nag", "code-01",
                "De test", Timestamp.valueOf(sdf.format(calendar.getTime())));
        DepartmentDTO dto2 = new DepartmentDTO(2L, "Department2", "Da nag 02", "code-02",
                "De test 02", Timestamp.valueOf(sdf.format(calendar.getTime())));
        List<DepartmentDTO> listDto = new ArrayList<>(Arrays.asList(dto1, dto2));

        try {
            String filename = departmentService.ExportData(listDto, request);
            String filename1 = departmentService.exportExcel();
            return new ResponseEntity<>(filename +" ===  " + filename1, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("Error when try to export file@!!!", HttpStatus.BAD_REQUEST);
        }
    }

}
