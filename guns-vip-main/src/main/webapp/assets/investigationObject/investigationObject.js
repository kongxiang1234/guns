layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;


    $(document).ready(function(){
        $.ajax({

            // url: "/investigationContent/getinvestigationInfoList",
            url: "/investigationObject/investigationObjectListByCompId",
            type: "POST",
            data:{},
            dataType: "json",
            success: function(data){
                debugger;
                searchRequestMethod(data);
            },
            error:function(err){
                console.log(err.statusText);
                console.log('查询出错，请联系管理员');
            }
        });
    });


    searchRequestMethod = function(data){
        var html = "";
        for (var i = 0; i <data.length ; i++) {

            html += '<div class="lsqtypebox" style="height: 20vh;" onmouseleave="hideButton('+i+')"  onmouseover="showButton('+i+')">' +

                '<div class="typecont">' +
                '<h3>'+data[i].infoList[0].Documents_number+'&nbsp;&nbsp;&nbsp;&nbsp;<label style="float: right;margin-right: 12px;" class="span2">催办<span>5</span></label></h3>' +

                '<div style="padding-top: 14px;padding-right: 5px;">申请查询：';
                for (var j = 0; j <data[i].infoList.length ; j++) {
                    html +=data[i].infoList[j].name_company;
                    if(j<data[i].infoList.length-1){
                        html += '、';
                    }
                }
             html +='</div><hr>' +

                 '<div style="padding-top: 0px;">' +
                '截止时间:'+data[i].infoList[0].deadLine+'<div class="buttonStyle'+i+'" style="display: none;"><button style="float: right;width:70px;margin-right: 12px;border-radius:8px;border:1px solid #000;text-align: center;" onclick="showDetail('+data[i].info_id+','+data[i].infoList[0].unit_id+')">受理</button></div>'+
                '</div>' +
                 '</div>' +
                '</div>';
        }
        $(".sqtypebox").html(html);
        // alert(JSON.stringify(data));
    };
    //显示按钮
    showButton=function (i) {
        $('.buttonStyle'+i).show();
    };
    //隐藏按钮
    hideButton=function (i) {
        $('.buttonStyle'+i).hide();
    };
    showDetail = function (infoId,unitId) {
        debugger
        window.location.href = Feng.ctxPath + '/investigationObject/add?unitId=' + unitId+"&infoId="+infoId
    };
    /**
     * 管理
     */
    var InvestigationObject = {
        tableId: "investigationObjectTable"
    };

    /**
     * 初始化表格的列
     */
    InvestigationObject.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'objectId', hide: true, title: '主键id'},
            {field: 'objectNotice', sort: true, title: '协查通知书'},
            {field: 'nameCompany', sort: true, title: '姓名（单位）'},
            {field: 'cardNumber', sort: true, title: '身份证号（信用代码，银行卡号） '},
            {field: 'remarks', sort: true, title: '备注'},
            {field: 'employeeCard', sort: true, title: '工作证_操作人'},
            {field: 'employeeCardSelect', sort: true, title: '工作证_选择'},
            {field: 'infoId', sort: true, title: '协查信息表id'},
            {field: 'createTime', sort: true, title: '操作时间'},
            {field: 'createBy', sort: true, title: '操作人'},
            {field: 'updateTime', sort: true, title: '更新时间'},
            {field: 'updateBy', sort: true, title: '更新人'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    InvestigationObject.search = function () {
        var queryData = {};


        table.reload(InvestigationObject.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    InvestigationObject.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/investigationObject/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    InvestigationObject.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/investigationObject/edit?objectId=' + data.objectId
    };

    /**
     * 导出excel按钮
     */
    InvestigationObject.exportExcel = function () {
        var checkRows = table.checkStatus(InvestigationObject.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    InvestigationObject.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/investigationObject/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(InvestigationObject.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("objectId", data.objectId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + InvestigationObject.tableId,
        url: Feng.ctxPath + '/investigationObject/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: InvestigationObject.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        InvestigationObject.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    InvestigationObject.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        InvestigationObject.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + InvestigationObject.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            InvestigationObject.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            InvestigationObject.onDeleteItem(data);
        }
    });
    showDeal=function () {
        
    }
});
