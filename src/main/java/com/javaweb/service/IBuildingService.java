package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBuildingService {
    ResponseDTO listStaffs(Long buildingId);
    List<BuildingSearchResponse> findBuildings(BuildingSearchRequest request);
    BuildingDTO save(BuildingDTO dto, MultipartFile avatarFile, String uploadDir);

    BuildingDTO findById(Long id);
    public void deleteBuilding(List<Long> ids);
    void updateAssignment(AssignmentBuildingDTO dto);
    int countBuildings(BuildingSearchRequest request);

}
