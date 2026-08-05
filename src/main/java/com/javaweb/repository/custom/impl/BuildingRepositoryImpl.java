package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    public static void joinTable(BuildingSearchRequest buildingSearchRequest,StringBuilder sql){
        Long staffId = buildingSearchRequest.getStaffId();
        if(staffId!=null){
            sql.append(" JOIN assignmentbuilding ass ON b.id=ass.buildingid");
        }
    }
    public static  void queryNormal(BuildingSearchRequest buildingSearchRequest,StringBuilder where){
        try {
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();

                // Bỏ qua các trường xử lý đặc biệt
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode")
                        && !fieldName.startsWith("rentArea") && !fieldName.startsWith("rentPrice") && !fieldName.equals("areaFrom") && !fieldName.equals("areaTo") && !fieldName.equals("page") && !fieldName.equals("pageSize")
                ) {

                    Object valueObj = item.get(buildingSearchRequest);
                    if (valueObj != null) { // Kiểm tra null từ Reflection trước khi chuyển đổi String
                        String value = valueObj.toString();
                        if (StringUtils.check(value)) {
                            if (NumberUtils.isNumber(value)) {
                                where.append(" AND b.").append(fieldName.toLowerCase()).append(" = ").append(value);
                            } else {
                                where.append(" AND b.").append(fieldName.toLowerCase()).append(" LIKE '%").append(value).append("%'");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void querySpecial(BuildingSearchRequest buildingSearchRequest, StringBuilder where) {
        // 1. Xử lý logic staffId
        Long staffId = buildingSearchRequest.getStaffId();
        if (staffId != null) {
            where.append(" AND ass.staffid = ").append(staffId);
        }

        // 2. Xử lý logic rentArea
        Long rentAreaFrom = buildingSearchRequest.getAreaFrom(); // Chú ý chuẩn đặt tên camelCase (getRentAreaFrom)
        Long rentAreaTo = buildingSearchRequest.getAreaTo();

        if (rentAreaFrom != null || rentAreaTo != null) {
            where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE b.id = r.buildingid ");
            if (rentAreaFrom != null) {
                where.append(" AND r.value >= ").append(rentAreaFrom);
            }
            if (rentAreaTo != null) {
                where.append(" AND r.value <= ").append(rentAreaTo);
            }
            where.append(") ");
        }

        // 3. Xử lý logic rentPrice
        Long rentPriceFrom = buildingSearchRequest.getRentPriceFrom();
        Long rentPriceTo = buildingSearchRequest.getRentPriceTo();

        if (rentPriceFrom != null || rentPriceTo != null) {
            if (rentPriceFrom != null) {
                where.append(" AND b.rentprice >= ").append(rentPriceFrom);
            }
            if (rentPriceTo != null) {
                where.append(" AND b.rentprice <= ").append(rentPriceTo);
            }
        }
        List<String> typeCode = buildingSearchRequest.getTypeCode();
        if (typeCode != null && !typeCode.isEmpty()) {
            where.append(" AND (");
            String sql = typeCode.stream()
                    .map(it -> "b.type LIKE '%" + it + "%'") // Thay đổi renttype.code thành rt.code (alias đã định nghĩa ở hàm joinTable)
                    .collect(Collectors.joining(" OR "));
            where.append(sql);
            where.append(") ");
        }

    }
    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT b.* FROM building b ");
        joinTable(buildingSearchRequest,sql);
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        queryNormal(buildingSearchRequest, where);
        querySpecial(buildingSearchRequest, where);
        int offset = (buildingSearchRequest.getPage()-1) * buildingSearchRequest.getPageSize();
        where.append(" GROUP BY b.id ");
        where.append( " LIMIT "+buildingSearchRequest.getPageSize() + " OFFSET "+offset);
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);

        return query.getResultList();
    }

    @Override
    public int countBuildings(BuildingSearchRequest request) {
        StringBuilder sql = new StringBuilder(" SELECT COUNT(DISTINCT b.id) FROM building b ");
        joinTable(request,sql);
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        queryNormal(request, where);
        querySpecial(request, where);



        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString());

        return ((Number) query.getSingleResult()).intValue()
                ;
    }
}

