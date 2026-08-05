<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Thêm / Sửa Toà Nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) {}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li><a href="/admin/building-list">Danh sách toà nhà</a></li>
                <li class="active">Thêm / Sửa</li>
            </ul>
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Thêm / Sửa Toà Nhà
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div>

            <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                <div class="col-xs-12">
                    <form:form modelAttribute="buildingEdit" id="formEdit" method="GET" class="form-horizontal" role="form">

                        <div class="form-group">
                            <label class="col-xs-3">Tên toà nhà</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Quận</label>
                            <div class="col-xs-2">
                                <form:select class="form-control" path="district">
                                    <form:option value="">---Chọn Quận---</form:option>
                                    <form:options items="${districts}"/>

                                </form:select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Phường</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="ward"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Đường</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="street"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Kết cấu</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="structure"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Số tầng hầm</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="numberOfBasement"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Diện tích sàn</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="floorArea"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Hướng</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="direction"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Hạng</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="level"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Diện tích thuê</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="rentArea"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Giá thuê</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="rentPrice"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Mô tả giá</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="rentPriceDescription"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Phí dịch vụ</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="serviceFee"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Phí ô tô</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="carFee"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Phí mô tô</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="motoFee"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Phí ngoài giờ</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="overtimeFee"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Tiền điện</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="electricityFee"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Đặt cọc</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="deposit"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Thanh toán</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="payment"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Thời hạn thuê</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="rentTime"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Thời gian trang trí</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="decorationTime"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Tên quản lý</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="managerName"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">SĐT quản lý</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="managerPhone"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Phí môi giới</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="brokerageFee"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Loại toà nhà</label>
                            <div class="col-xs-9">
                                <label class="checkbox-inline">
                                    <form:checkbox path="typeCode" value="NOI_THAT"/>Nội thất
                                </label>
                                <label class="checkbox-inline">
                                    <form:checkbox path="typeCode" value="TANG_TRET"/>Tầng Trệt
                                </label>
                                <label class="checkbox-inline">
                                   <form:checkbox path="typeCode" value="NGUYEN_CAN"/>Nguyên Căn
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Ghi chú</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="note"/>
                            </div>
                        </div>
                        <div class="form-group">
    <label class="col-xs-3">Ảnh toà nhà</label>
    <div class="col-xs-9">
        <input type="file" id="avatar" name="avatar" accept="image/*" class="form-control"/>
    </div>
</div>

                        <div class="form-group">
                            <div class="col-xs-offset-3 col-xs-9">
                            <c:if test="${not empty buildingEdit.id}">
                             <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">
                                    Cập nhật toà nhà
                                </button>
                                <a href="/admin/building-list" class="btn btn-default" id="btnCancel">
                                    Hủy thao tác
                                </a>
                            </c:if>
                             <c:if test="${ empty buildingEdit.id}">
                             <button type="button" class="btn btn-primary" id="btnAddBuilding">
                                    Thêm mới toà nhà
                             </button>
                                <a href="/admin/building-list" class="btn btn-default" id="btnCancel">
                                    Hủy thao tác
                                </a>
                            </c:if>
                            </div>
                        </div>
                    <form:hidden path="id"  id="buildingId"/>
                    </form:form>
                </div>
            </div>

        </div><%-- /.page-content --%>
    </div>
</div><%-- /.main-content --%>

<script>
    $('#btnAddOrUpdateBuilding,#btnAddBuilding').click(function () {
        var data = {};
        var typeCode = [];
        var formData = $('#formEdit').serializeArray();

        $.each(formData, function (i, v) {
            if (v.name != 'typeCode') {
                data["" + v.name + ""] = v.value;
            } else {
                typeCode.push(v.value);
            }
        });

        data['typeCode'] = typeCode;
        if(typeCode!=''){
            addOrUpdateBuidling(data);
        }else {
            window.location.href = '/admin/building-edit?typeCode=require';

        }
    });
   function addOrUpdateBuidling(data) {
    var formData = new FormData();

    // Append từng field vào FormData
    $.each(data, function(key, value) {
        if (key === 'typeCode') {
            $.each(value, function(i, v) {
                formData.append('typeCode', v);
            });
        } else if (key !== 'avatar') {  // bỏ qua avatar string từ DTO
            formData.append(key, value);
        }
    });

    // Append file nếu có
    var avatarFile = $('#avatar')[0].files[0];
    if (avatarFile) {
        formData.append('avatar', avatarFile);
    }

    $.ajax({
        type: "POST",
        url: "${buildingAPI}",
        data: formData,
        processData: false,   // quan trọng - không để jQuery xử lý data
        contentType: false,   // quan trọng - để browser tự set multipart boundary
        success: function(respond) {
            window.location.href = "/admin/building-list";
        },
        error: function(respond) {
            console.log("Fail");
        }
    });
}




</script>

</body>
</html>
