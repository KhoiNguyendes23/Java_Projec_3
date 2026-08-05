package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;
    // Trong BuildingAPI, lấy real path
    @PostMapping(consumes = "multipart/form-data")
    public BuildingDTO addOrUpdateBuilding(
            BuildingDTO buildingDTO,
            @RequestParam(value = "avatar", required = false) MultipartFile avatarFile,
            HttpServletRequest request) {

        String uploadDir = request.getServletContext().getRealPath("/static/images/");
        return buildingService.save(buildingDTO, avatarFile, uploadDir);
    }


    @DeleteMapping
    public void deleteBuilding(@RequestBody List<Long>ids){
        //Xuống DB để xoá building theo danh sach id gui về

       buildingService.deleteBuilding(ids);
    }
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadstaffs(@PathVariable Long id){
        ResponseDTO result = buildingService.listStaffs(id);
        return  result;
    }
    @PostMapping("/assignment")
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        buildingService.updateAssignment(assignmentBuildingDTO);
    }
}
