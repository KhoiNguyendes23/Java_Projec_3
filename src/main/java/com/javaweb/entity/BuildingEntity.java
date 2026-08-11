package com.javaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "district")
    private String district;

    @Column(name = "structure")
    private String structure;

    @Column(name = "numberofbasement")
    private Long numberOfBasement;

    @Column(name = "floorarea")
    private Long floorArea;

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private String level;

    @Column(name = "rentprice")
    private Long rentPrice;

    @Column(name = "rentpricedescription")
    private String rentPriceDescription;

    @Column(name = "servicefee")
    private String serviceFee;

    @Column(name = "carfee")
    private String carFee;

    @Column(name = "motofee")
    private String motoFee;

    @Column(name = "overtimefee")
    private String overtimeFee;

    @Column(name = "waterfee")
    private String waterFee;

    @Column(name = "electricityfee")
    private String electricityFee;

    @Column(name = "deposit")
    private String deposit;

    @Column(name = "payment")
    private String payment;

    @Column(name = "renttime")
    private String rentTime;

    @Column(name = "decorationtime")
    private String decorationTime;

    @Column(name = "brokeragefee")
    private Double brokerageFee;

    @Column(name = "type")
    private String typeCode; // Lưu các loại như TANG_TRET, NGUYEN_CAN...

    @Column(name = "note")
    private String note;



    @Column(name = "map")
    private String map;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "managername")
    private String managerName;

    @Column(name = "managerphone")
    private String managerPhone;



    // Quan hệ 1-N tới RentAreaEntity (bảng rentarea)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buildingEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
    // QUAN HỆ NHIỀU - NHIỀU ĐƯỢC THAY THẾ Ở ĐÂY
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> userEntities = new ArrayList<>();
//    // Quan hệ 1-N tới AssignBuildingEntity (bảng assignmentbuilding)
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buildingEntity")
//    private List<AssignBuildingEntity> assignBuildingEntities = new ArrayList<>();

    // --- GETTERS AND SETTERS ---

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getWard() { return ward; }
    public void setWard(String ward) { this.ward = ward; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getStructure() { return structure; }
    public void setStructure(String structure) { this.structure = structure; }

    public Long getNumberOfBasement() { return numberOfBasement; }
    public void setNumberOfBasement(Long numberOfBasement) { this.numberOfBasement = numberOfBasement; }

    public Long getFloorArea() { return floorArea; }
    public void setFloorArea(Long floorArea) { this.floorArea = floorArea; }

    public String getDirection() { return direction; }
    public void setDirection(String direction) { this.direction = direction; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public Long getRentPrice() { return rentPrice; }
    public void setRentPrice(Long rentPrice) { this.rentPrice = rentPrice; }

    public String getRentPriceDescription() { return rentPriceDescription; }
    public void setRentPriceDescription(String rentPriceDescription) { this.rentPriceDescription = rentPriceDescription; }

    public String getServiceFee() { return serviceFee; }
    public void setServiceFee(String serviceFee) { this.serviceFee = serviceFee; }

    public String getCarFee() { return carFee; }
    public void setCarFee(String carFee) { this.carFee = carFee; }

    public String getMotoFee() { return motoFee; }
    public void setMotoFee(String motoFee) { this.motoFee = motoFee; }

    public String getOvertimeFee() { return overtimeFee; }
    public void setOvertimeFee(String overtimeFee) { this.overtimeFee = overtimeFee; }

    public String getWaterFee() { return waterFee; }
    public void setWaterFee(String waterFee) { this.waterFee = waterFee; }

    public String getElectricityFee() { return electricityFee; }
    public void setElectricityFee(String electricityFee) { this.electricityFee = electricityFee; }

    public String getDeposit() { return deposit; }
    public void setDeposit(String deposit) { this.deposit = deposit; }

    public String getPayment() { return payment; }
    public void setPayment(String payment) { this.payment = payment; }

    public String getRentTime() { return rentTime; }
    public void setRentTime(String rentTime) { this.rentTime = rentTime; }

    public String getDecorationTime() { return decorationTime; }
    public void setDecorationTime(String decorationTime) { this.decorationTime = decorationTime; }

    public Double getBrokerageFee() { return brokerageFee; }
    public void setBrokerageFee(Double brokerageFee) { this.brokerageFee = brokerageFee; }

    public String getTypeCode() { return typeCode; }
    public void setTypeCode(String typeCode) { this.typeCode = typeCode; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }



    public String getMap() { return map; }
    public void setMap(String map) { this.map = map; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }

    public String getManagerPhone() { return managerPhone; }
    public void setManagerPhone(String managerPhone) { this.managerPhone = managerPhone; }



    public List<RentAreaEntity> getRentAreaEntities() { return rentAreaEntities; }
    public void setRentAreaEntities(List<RentAreaEntity> rentAreaEntities) { this.rentAreaEntities = rentAreaEntities; }

//    public List<AssignBuildingEntity> getAssignBuildingEntities() { return assignBuildingEntities; }
//    public void setAssignBuildingEntities(List<AssignBuildingEntity> assignBuildingEntities) { this.assignBuildingEntities = assignBuildingEntities; }
}