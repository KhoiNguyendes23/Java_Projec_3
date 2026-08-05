package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class BuildingService implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs= userRepository.findByStatusAndRoles_Code(1,"STAFF");
        List<UserEntity> staffAssignment = building.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for(UserEntity it: staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if(staffAssignment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public List<BuildingSearchResponse> findBuildings(BuildingSearchRequest request) {
        List<BuildingEntity> buildingEntities=buildingRepository.findAll(request);
        List<BuildingSearchResponse> responses = new ArrayList<>();
        for (BuildingEntity it: buildingEntities){
            BuildingSearchResponse buildingSearchResponse= new BuildingSearchResponse();
            buildingSearchResponse.setId(it.getId());
            buildingSearchResponse.setName(it.getName());
            buildingSearchResponse.setAddress(it.getStreet() + "," + it.getWard()+","+it.getDistrict());
            buildingSearchResponse.setNumberOfBasement(it.getNumberOfBasement());
            buildingSearchResponse.setManagerName(it.getManagerName());
            buildingSearchResponse.setManagerPhoneNumber(it.getManagerPhone());
            buildingSearchResponse.setFloorArea(it.getFloorArea());
            List<RentAreaEntity> areaEntities = it.getRentAreaEntities();
            String rentArea = areaEntities.stream().map(a -> String.valueOf(a.getValue())).collect(Collectors.joining(","));
            buildingSearchResponse.setRentArea(rentArea);
            buildingSearchResponse.setRentPrice(it.getRentPrice());
            buildingSearchResponse.setServiceFee(it.getServiceFee());
            buildingSearchResponse.setBrokerageFee(it.getBrokerageFee());
            responses.add(buildingSearchResponse);
        }
        return responses;
    }

    @Override
    public BuildingDTO save(BuildingDTO dto, MultipartFile avatarFile, String upLoadDir) {
        BuildingEntity entity ;

        if(dto.getId()!=null){

            entity= buildingRepository.findById(dto.getId()).get();
        }else{
            entity=new BuildingEntity();
        }
        entity.setName(dto.getName());
        entity.setStreet(dto.getStreet());
        entity.setWard(dto.getWard());
        entity.setDistrict(dto.getDistrict());
        entity.setStructure(dto.getStructure());
        entity.setDirection(dto.getDirection());
        entity.setRentPriceDescription(dto.getRentPriceDescription());
        entity.setServiceFee(dto.getServiceFee());
        entity.setDeposit(dto.getDeposit());
        entity.setPayment(dto.getPayment());
        entity.setRentTime(dto.getRentTime());
        entity.setDecorationTime(dto.getDecorationTime());
        entity.setManagerName(dto.getManagerName());
        entity.setManagerPhone(dto.getManagerPhone());
        entity.setNote(dto.getNote());

// --- 2. Các trường cùng kiểu dữ liệu số (Long, Double) ---
        entity.setNumberOfBasement(dto.getNumberOfBasement());
        entity.setFloorArea(dto.getFloorArea());
        entity.setRentPrice(dto.getRentPrice());
        entity.setBrokerageFee(dto.getBrokerageFee());

// --- 3. Xử lý các trường LỆCH KIỂU DỮ LIỆU (Kiểm tra null an toàn) ---

// level: DTO (Long) -> Entity (String)
        if (dto.getLevel() != null) {
            entity.setLevel(String.valueOf(dto.getLevel()));
        }

// carFee: DTO (Long) -> Entity (String)
        if (dto.getCarFee() != null) {
            entity.setCarFee(String.valueOf(dto.getCarFee()));
        }

// motoFee: DTO (Long) -> Entity (String)
        if (dto.getMotoFee() != null) {
            entity.setMotoFee(String.valueOf(dto.getMotoFee()));
        }

// overtimeFee: DTO (Long) -> Entity (String)
        if (dto.getOvertimeFee() != null) {
            entity.setOvertimeFee(String.valueOf(dto.getOvertimeFee()));
        }

// electricityFee: DTO (Long) -> Entity (String)
        if (dto.getElectricityFee() != null) {
            entity.setElectricityFee(String.valueOf(dto.getElectricityFee()));
        }

// waterFee: DTO hiện tại không có trường này, tạm thời để trống hoặc lấy mặc định nếu cần
// entity.setWaterFee(...);

// --- 4. Xử lý chuỗi loại tòa nhà (TypeCode) ---
        if (dto.getTypeCode() != null && !dto.getTypeCode().isEmpty()) {
            entity.setTypeCode(String.join(",", dto.getTypeCode()));
        }
        if (avatarFile != null && !avatarFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + avatarFile.getOriginalFilename();
            // Bỏ dòng: String uploadDir = "src/main/webapp/static/images/";

            File dir = new File(upLoadDir);  // ← dùng upLoadDir từ tham số
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File dest = new File(upLoadDir + fileName);  // ← dùng upLoadDir
            try {
                avatarFile.transferTo(dest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            entity.setAvatar("/static/images/" + fileName);
        }



        buildingRepository.save(entity);
        return dto;
    }

    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity entity = buildingRepository.findById(id).get();
        BuildingDTO dto = new BuildingDTO();

        // String fields
        dto.setName(entity.getName());
        dto.setStreet(entity.getStreet());
        dto.setWard(entity.getWard());
        dto.setDistrict(entity.getDistrict());
        dto.setStructure(entity.getStructure());
        dto.setDirection(entity.getDirection());
        dto.setRentPriceDescription(entity.getRentPriceDescription());
        dto.setServiceFee(entity.getServiceFee());
        dto.setDeposit(entity.getDeposit());
        dto.setPayment(entity.getPayment());
        dto.setRentTime(entity.getRentTime());
        dto.setDecorationTime(entity.getDecorationTime());
        dto.setManagerName(entity.getManagerName());
        dto.setManagerPhone(entity.getManagerPhone());
        dto.setNote(entity.getNote());

        // Number fields
        dto.setId(entity.getId());
        dto.setNumberOfBasement(entity.getNumberOfBasement());
        dto.setFloorArea(entity.getFloorArea());
        dto.setRentPrice(entity.getRentPrice());
        dto.setBrokerageFee(entity.getBrokerageFee());

        // String -> Long (ngược lại với save)
        if (entity.getLevel() != null) {
            dto.setLevel(Long.valueOf(entity.getLevel()));
        }

        // typeCode: "tang-tret,nguyen-can" -> List<String>
        if (entity.getTypeCode() != null && !entity.getTypeCode().isEmpty()) {
            dto.setTypeCode(Arrays.asList(entity.getTypeCode().split(",")));
        }

        return dto;
    }

    @Override
    @Transactional
    public void deleteBuilding(List<Long> ids) {
        for (Long id : ids) {

            buildingRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void updateAssignment(AssignmentBuildingDTO dto) {
        BuildingEntity building = buildingRepository.findById(dto.getBuildingId()).get();
        List<UserEntity> newStaffs = userRepository.findByIdIn(dto.getStaffs());
        building.setUserEntities(newStaffs);
        buildingRepository.save(building);
    }

    @Override
    public int countBuildings(BuildingSearchRequest request) {

        return   buildingRepository.countBuildings(request);
    }


}
