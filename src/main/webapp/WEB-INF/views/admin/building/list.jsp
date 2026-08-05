<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingListURL" value="/admin/building-list"/>
<c:url var="buildingAPI" value="/api/building"/>

<html>
<head>
    <title>Danh sách toà nhà</title>
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
                <li class="active">Dashboard</li>
            </ul>
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Danh Sách Toà Nhà
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box ui-sortable-handle">
                        <div class="widget-header">
                            <h5 class="widget-title">Tìm Kiếm</h5>
                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>

                        <div class="widget-body" style="font-family: 'Times New Roman', Times, serif;">
                            <div class="widget-main" >
                                <form:form id="listForm" modelAttribute="modelSearch" action="${buildingListURL}" method="GET">
                                <%-- Hàng 1: Tên toà nhà | Diện tích sàn --%>
                                <div class="row">
                                    <div class="col-xs-6">
                                        <label class="name">Tên toà nhà</label>
                                        <form:input class="form-control" path="name"/>
                                    </div>
                                    <div class="col-xs-6">
                                        <label class="name">Diện tích sàn</label>
                                        <form:input class="form-control" path="floorArea"/>
                                    </div>
                                </div>

                                <%-- Hàng 2: Quận | Phường | Đường --%>
                                <div class="row">
                                    <div class="col-xs-2">
                                        <label class="name">Quận</label>
                                        <form:select class="form-control" path="district">
                                            <form:option value="">---Chọn Quận---</form:option>
                                            <form:options items="${districts}"/>

                                        </form:select>
                                    </div>
                                    <div class="col-xs-5">
                                        <label class="name">Phường</label>
                                        <form:input class="form-control" path="ward"/>
                                    </div>
                                    <div class="col-xs-5">
                                        <label class="name">Đường</label>
                                        <form:input class="form-control" path="street"/>
                                    </div>
                                </div>

                                <%-- Hàng 3: Số tầng hầm | Hướng | Hạng --%>
                                <div class="row">
                                    <div class="col-xs-4">
                                        <label class="name">Số tầng hầm</label>
                                        <form:input class="form-control" path="numberOfBasement"/>
                                    </div>
                                    <div class="col-xs-4">
                                        <label class="name">Hướng</label>
                                        <form:input class="form-control" path="direction"/>
                                    </div>
                                    <div class="col-xs-4">
                                        <label class="name">Hạng</label>
                                        <form:input class="form-control" path="level"/>
                                    </div>
                                </div>

                                <%-- Hàng 4: Diện tích từ | Diện tích đến | Giá thuê từ | Giá thuê đến --%>
                                <div class="row">
                                    <div class="col-xs-3">
                                        <label class="name">Diện tích từ</label>
                                        <form:input class="form-control" path="areaFrom"/>
                                    </div>
                                    <div class="col-xs-3">
                                        <label class="name">Diện tích đến</label>
                                        <form:input class="form-control" path="areaTo"/>
                                    </div>
                                    <div class="col-xs-3">
                                        <label class="name">Giá thuê từ</label>
                                        <form:input class="form-control" path="rentPriceFrom"/>
                                    </div>
                                    <div class="col-xs-3">
                                        <label class="name">Giá thuê đến</label>
                                        <form:input class="form-control" path="rentPriceTo"/>
                                    </div>
                                </div>

                                <%-- Hàng 5: Tên quản lý | SĐT Quản Lý | Nhân Viên --%>
                                <div class="row">
                                    <div class="col-xs-5">
                                        <label class="name">Tên quản lý</label>
                                        <form:input class="form-control" path="managerName"/>
                                    </div>
                                    <div class="col-xs-5">
                                        <label class="name">SĐT Quản Lý</label>
                                        <form:input class="form-control" path="managerPhone"/>
                                    </div>
                                    <div class="col-xs-2">
                                        <label class="name">Nhân Viên</label>
                                        <form:select class="form-control" path="staffId">
                                            <form:option value="">---Chọn Nhân Viên---</form:option>

                                            <form:options items="${listStaffs}" />

                                        </form:select>
                                    </div>
                                </div>

                                <%-- Hàng 6: Checkbox loại + Nút tìm kiếm --%>
                                <div class="row">
                                    <div class="col-xs-6">
                                        <form:checkboxes items="${typeCodes}" path="typeCode" />

                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6">
                                        <button type="button" class="btn btn-danger" id="btnSearchBuidling">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"></path>
                                            </svg>
                                            Tìm Kiếm
                                        </button>
                                    </div>
                                </div>
                                </form:form>


                            </div><%-- /.widget-main --%>
                        </div><%-- /.widget-body --%>

                        <div class="pull-right">
                            <a href="/admin/building-edit">

                                <button class="btn btn-info" title="Thêm toà nhà">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                        <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0" />
                                        <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z" />
                                        <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z" />
                                    </svg>
                                </button>
                            </a>

                            <button class="btn btn-danger" title="Xoá toà nhà" id="btnDeleteBuilding">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-dash" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1" />
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z" />
                                    <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z" />
                                </svg>
                            </button>
                        </div>
                    </div><%-- /.widget-box --%>
                </div>
            </div><%-- /.row --%>

            <%-- Bảng danh sách --%>
            <div class="col-xs-12">
                <table id="tableList" style="margin: 3em 0 0;" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="center">
                            <label class="pos-rel">
                                <input type="checkbox" name="checkList" value="" class="ace">
                                <span class="lbl"></span>
                            </label>
                        </th>
                        <th>Tên Toà Nhà</th>
                        <th>Địa Chỉ</th>
                        <th>Số Tầng Hầm</th>
                        <th>Tên Quản Lý</th>
                        <th>Số Điện Thoại Quản Lý</th>
                        <th>D.Tích Sàn</th>
                        <th>D.Tích Trống</th>
                        <th>D.Tích Thuê</th>
                        <th>Phí Dịch Vụ</th>
                        <th>Phí Môi Giới</th>
                        <th>Thao Tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var ="item" items="${buildingList}">
                 <tr>
                        <td class="center">
                            <label class="pos-rel">
                                <input type="checkbox" class="ace" name="checkList" value=${item.id}>
                                <span class="lbl"></span>
                            </label>
                        </td>
                        <td>${item.name}</td>
                        <td>${item.address}</td>
                        <td>${item.numberOfBasement}</td>
                        <td>${item.managerName}</td>
                        <td>${item.managerPhoneNumber}</td>
                        <td>${item.floorArea}</td>
                        <td>${item.emptyArea}</td>
                        <td>${item.rentArea}</td>
                        <td>${item.serviceFee}</td>
                        <td>${item.brokerageFee}</td>
                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <button class="btn btn-xs btn-success" title="Giao toà nhà" onclick="assignmentbuilding(${item.id})">
                                    <i class="ace-icon glyphicon glyphicon-list"></i>
                                </button>
                                <a class="btn btn-xs btn-info" title="Sửa toà nhà" href="/admin/building-edit-${item.id}">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </a>
                                <button class="btn btn-xs btn-danger" title="Xoá toà nhà" onclick="deleteBuilding(${item.id})">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>
                            </div>
                        </td>
                    </tr>
                    </c:forEach>


                    </tbody>
                </table>
                <div class="text-center" style="margin-top: 15px;">
    <c:forEach begin="1" end="${totalPages}" var="i">
        <a href="/admin/building-list?page=${i}"
           class="btn btn-sm ${i == currentPage ? 'btn-primary' : 'btn-default'}">
            ${i}
        </a>
    </c:forEach>
</div>

            </div>
            <%-- /Bảng danh sách --%>


        </div><%-- /.page-content --%>
    </div>
</div><%-- /.main-content --%>

<%-- Modal giao toà nhà --%>
<div class="modal fade" id="assignmentbuildingModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh Sách Nhân Viên</h4>
            </div>
            <div class="modal-body">
                <table id="staffList" style="margin: 3em 0 0;" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>Chọn</th>
                        <th>Tên Nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnassignmentBuilding">Giao Toà Nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<script>
    function assignmentbuilding(buildingId) {
       $('#assignmentbuildingModal').modal();
        loadStaff(buildingId);

           $('#buildingId').val(buildingId);
    }
    function loadStaff(buildingId){
    $.ajax({
        type: "GET",
        url: "${buildingAPI}/" + buildingId + '/staffs',
        contentType: "application/json",
        dataType: "JSON",
        success: function (response) {
            var row = '';
            $.each(response.data, function (index, item){
                row += '<tr>';
                row += '<td class="text-center"><input type="checkbox" value="' + item.staffId + '" id="checkbox_' + item.staffId + '" class="check-box-elemet"' + item.checked + ' /></td>';
                row += '<td class="text-center">' + item.fullName + '</td>';
                row += '</tr>';
            });
            $('#staffList tbody').html(row);
            console.info("Succcess");
        },
        error: function(response){
            console.log("failed");
            window.location.href = "<c:url value='/admin/building-list?message=error'/>";
            console.log(response);
        }
    });
}
    $('#btnassignmentBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        data['buildingId'] = $('#buildingId').val();
        var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;

            assignment(data)


    });
   function assignment(data){
    $.ajax({
        url: "${buildingAPI}/assignment",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        dataType: 'json',
        success: function (response) {
    alert("Giao toà nhà thành công!");
    $('#assignmentbuildingModal').modal('hide');  // đóng modal
}
,
        error: function(response){
            console.info("Giao Không Thành Công!")
            window.location.href = "<c:url value='/admin/building-list?message=erro'/>";
            console.log(response);
        }
    });
}
    $('#btnSearchBuidling').click(function (e){
        e.preventDefault();
        $('#listForm').submit()
    });
     function deleteBuilding(id) {
         var buildingId = [id];
       deleteBuildings(buildingId);
    }
     $('#btnDeleteBuilding').click(function (e) {
        e.preventDefault();
        var data = {};

        var buildingIds = $('#tableList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();

       deleteBuildings(buildingIds);
    });
    function deleteBuildings(data){
        $.ajax({
            type: "DELETE",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (respond) {
                alert("Xóa thành công!");
                window.location.reload();
            },
            error: function (respond) {
                alert("Xóa thất bại: " + respond.status + " " + respond.statusText);
                console.log(respond);
            }
        })
     }
     // Tích/bỏ tất cả checkbox
$('thead input[type=checkbox]').click(function () {
    var checked = $(this).is(':checked');
    $('tbody input[type=checkbox]').prop('checked', checked);
});

// Khi bỏ tích 1 dòng → bỏ tích checkbox header
$('tbody').on('change', 'input[type=checkbox]', function () {
    var allChecked = $('tbody input[type=checkbox]').length ===
                     $('tbody input[type=checkbox]:checked').length;
    $('thead input[type=checkbox]').prop('checked', allChecked);
});

</script>

</body>
</html>
