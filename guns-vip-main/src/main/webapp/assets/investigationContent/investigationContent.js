layui.use(['table', 'admin', 'ax', 'func','upload'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var upload = layui.upload;

    /**
     * 管理
     */
    var InvestigationContent = {
        tableId: "investigationContentTable"
    };

    jumpToInvestigation = function () {
        window.location.href = Feng.ctxPath + '/investigationInfo'
    };
    $(document).ready(function(){
        $.ajax({
            url: "/investigationContent/getinvestigationInfoList",
            type: "POST",
            data:{},
            dataType: "json",
            success: function(data){

                var html = "";
                for (var i = 0; i <data.length ; i++) {

                    html += '<div class="lsqtypebox" onclick="showInvestigationDetail('+data[i].info_Id+');">' +
                        '<div>' +
                        '<div class="typecont">' +
                        '<h1>'+data[i].infoList[0].Documents_number+'&nbsp;&nbsp;<label class="span2">未受理</label></h1>' +
                        '<div class="typeview">' +
                        '<span class="viewnum">申请人：'+data[i].infoList[0].user_id+'</span>'+
                        '<span class="pubtime">申请时间：'+data[i].infoList[0].apply_time.substring(0,10)+'</span>' +
                        '</div>' +
                        '<table>' +
                        '<tr>' +
                        '<th>协查内容：</th><td>';
                    for (var j = 0; j <data[i].infoList.length ; j++) {
                        html +=data[i].infoList[j].name_company;
                            if(j<data[i].infoList.length-1){
                                html += ',';
                            }
                    }
                    html +='</td>' +
                        '</tr>' +
                        '<tr>' +
                        '<th>协查反馈：</th>' +
                        '<td class="red">' +
                        '<div class="process"><span><i></i></span></div>100%' +
                        '</td>' +
                        '</tr>' +
                        '</table>' +
                        '<span class="editbox">' +
                        '<img src="/assets/investigationInfo/img/icon31.png" onmousemove="editbox(this)" onmouseout="noeditbox(this)" />' +
                        '<div class="editcover" onmousemove="editcover(this)" onmouseout="noeditcover(this)">' +
                        '<img src="/assets/investigationInfo/img/icon32.png" />'+
                        '<ul>' +
                        '<li><span></span>修改</li>' +
                        '<li><span></span>删除</li>' +
                        '<li><span></span>下载</li>' +
                        '<li><span></span>催办</li>' +
                        '</ul>' +
                        '</div>' +
                        '</span>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                }
                debugger;
                $(".sqtypebox").html(html);
                // alert(JSON.stringify(data));
            },
            error:function(err){
                console.log(err.statusText);
                console.log('异常');
            }
        });
    });

    /**
     * 初始化表格的列
     */
    InvestigationContent.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'contentId', hide: true, title: '主键id'},
            {field: 'nameCompany', sort: true, title: '姓名（单位）'},
            {field: 'cardNumber', sort: true, title: '身份证号（信用代码，银行卡号） '},
            {field: 'undertaker', sort: true, title: '备注'},
            {field: 'unitId', sort: true, title: '协查单位id'},
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
    InvestigationContent.search = function () {
        var queryData = {};


        table.reload(InvestigationContent.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    InvestigationContent.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/investigationContent/add'
    };
    showInvestigationDetail = function (val) {
        window.location.href = Feng.ctxPath + '/investigationContent/add?unitId=' + val
    };
    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    InvestigationContent.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/investigationContent/edit?contentId=' + data.contentId
    };


    /**
     * 导出excel按钮
     */
    InvestigationContent.exportExcel = function () {
        var checkRows = table.checkStatus(InvestigationContent.tableId);
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
    InvestigationContent.onDeleteItem = function (data) {
        alert();
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/investigationContent/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(InvestigationContent.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("contentId", data.contentId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };
    // 渲染表格
    var tableResult = table.render({
        elem: '#' + InvestigationContent.tableId,
        url: Feng.ctxPath + '/investigationContent/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: InvestigationContent.initColumn()
    });


    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        InvestigationContent.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    InvestigationContent.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        InvestigationContent.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + InvestigationContent.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            InvestigationContent.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            InvestigationContent.onDeleteItem(data);
        }
    });

    //导入协查清单
    upload.render({
        elem: '#btnUploads'
        , url: Feng.ctxPath + '/investigationContent/uploadInvestigationResults'
        , multiple: true
        , accept: 'file'
        , done: function (res) {
            if (res.success) {
                Feng.success("导入成功！");
            } else {
                Feng.error(res.message)
            }
        }
        , error: function () {
            Feng.error(res.message)
        }
    });

    downFiles = function(){
        window.location.href = Feng.ctxPath + '/investigationContent/downFiles'
    }
});
