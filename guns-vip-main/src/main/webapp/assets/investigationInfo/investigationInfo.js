layui.use(['table', 'admin', 'ax', 'func', 'laydate', 'form', 'upload'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var laydate = layui.laydate;
    var form = layui.form;
    var upload = layui.upload;

    /**
     * 管理
     */
    var InvestigationInfo = {
        tableId: "investigationInfoTable"
    };

    /**
     * 初始化表格的列
     */
    InvestigationInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'infoId', hide: true, title: '主键id'},
            {field: 'documentsNumber', sort: true, title: '协查文书号'},
            {field: 'stauts', sort: true, title: '协查申请状态'},
            {field: 'undertaker', sort: true, title: '承办人'},
            {field: 'deadLine', sort: true, title: '最迟反馈时间'},
            {field: 'userId', sort: true, title: '申请人'},
            {field: 'applyTime', sort: true, title: '申请时间'},
            {field: 'createTime', sort: true, title: '操作时间'},
            {field: 'createBy', sort: true, title: '操作人'},
            {field: 'updateTime', sort: true, title: '更新时间'},
            {field: 'updateBy', sort: true, title: '更新人'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };
    showUnit = function (j) {
        $(".xcdetail").find("#detailpic" + j + ",#deltailtxtbox" + j + "").show();
    };
    hideUnit = function (j) {
        $(".xcdetail").find("#detailpic" + j + ",#deltailtxtbox" + j + "").hide();
    };
    /**
     * 点击查询按钮
     */
    InvestigationInfo.search = function () {
        var queryData = {};


        table.reload(InvestigationInfo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    let uploadFilesIndex = new Map()
    //导入协查通知书
    var uploadInst = upload.render({
        elem: '#fileBtn0'
        , bindAction: '#pdfUpload'
        , url: Feng.ctxPath + '/investigationInfo/uploadPdf'
        , accept: 'file'
        , multiple: true
        , exts: "pdf"
        , auto: false
        , data: {
            "docNum": function () {
                return $("#Documents_number").val();
            }
        }
        , choose: function (obj) {
            if (JSON.stringify(excelData) == "{}") {
                Feng.error("请先导入协查清单")
                return
            }
            var files = obj.pushFile();

            obj.preview(function (index, file, result) {
                let fileName = file.name.slice(0, -4);
                let orgFileName = file.name;

                let ok = 0;
                let parentId = "deltailtxtbox"
                for (var j = 0; j < excelData.length; j++) {
                    var key = Object.keys(excelData[j])[0];
                    if (key === fileName) {
                        if (uploadFilesIndex.get(key) > 0) {
                            Feng.error(orgFileName + "重复，请删除后再重新添加")
                            delete files[index]
                            return
                        }
                        ok++
                        uploadFilesIndex.set(fileName, 1)
                        parentId = parentId + j
                    }
                }

                if (ok === 0) {
                    Feng.error(orgFileName + "命名非法，请按照协查机构命名后再重新添加")
                    delete files[index]
                    return
                }

                html =
                    '<div id="pdf_' + index + '"><ul class="dwtype dwtypets">' +
                    '<li>' +
                    '<div class="dwtypebox">' +
                    '<img src="/assets/investigationInfo/img/icon27.png" />' +
                    '<h1>' + orgFileName + '</h1>' +
                    '<span class="operate">' +
                    '<img src="/assets/investigationInfo/img/icon26.png" id="del_' + index + '"/>' +
                    '</span>' +
                    '</div>' +
                    '</li>' +
                    '<li class="add">' +
                    '<img src="/assets/investigationInfo/img/icon28.png" />' +
                    '<div>新增</div>' +
                    '</li>' +
                    '</ul></div>'

                $("#" + parentId).append(html);

                // ------ 删除预选文件
                $('#del_' + index).bind('click', function () {// 删除指定预上传图片
                    delete files[index];
                    uploadFilesIndex.delete(fileName)
                    $("#pdf_" + index).remove();
                })
                // ------ 删除预选文件

                Feng.success("协查文件已添加")
            });
        }
        , before: function (obj) {
        }
        , done: function (res) {
            Feng.success(res.message);
        }
        , error: function () {
            Feng.error("上传图片失败！");
        }

    });

    var excelData = {};

    //导入协查清单
    upload.render({
        elem: '#fileExcel'
        , url: Feng.ctxPath + '/mgr/uploadExcel'
        , accept: 'file'
        , done: function (res) {
            if (res.success) {
                var ajax = new $ax(Feng.ctxPath + "/investigationInfo/getUploadData", function (data) {
                    if (data.success) {
                        Feng.success("导入成功！");
                        excelData = data.data;
                        randerInvestingationObj();
                    } else {
                        Feng.error(data.message);
                    }
                }, function (data) {
                    Feng.error("导入失败！" + data.message)
                });
                ajax.start();
            } else {
                Feng.error(res.message)
            }
        }
        , error: function () {
            //请求异常回调
        }
    });
    var pdfData = [];
    randerInvestingationObj = function () {
        // alert(JSON.stringify(excelData));
        var html = '';
        for (var j = 0; j < excelData.length; j++) {
            var key = Object.keys(excelData[j])[0];
            html += '<div class="xcdetail" onmouseover="showUnit(' + j + ')" onmouseout="hideUnit(' + j + ')"><img src="/assets/investigationInfo/img/icon20.png" />' +
                '<h1>' + key + '账单数据协查&nbsp;&nbsp;&nbsp;<span class="span1">材料齐全</span></h1>' +
                '<div class="deltailtxt">' +
                '<span>被协查对象：<label>' + excelData[j][key].length + '</label></span>' +
                '<span>法律文书：<label>1</label></span>' +
                '</div>' +
                '<img src="/assets/investigationInfo/img/icon29.png" onclick="deleteUnit(' + j + ')" class="detailpic" id="detailpic' + j + '"/>' +
                '<div class="deltailtxtbox" id="deltailtxtbox' + j + '">' +
                '<ul class="dwtype" id="investigation_unit">';

            for (var i = 0; i < excelData[j][key].length; i++) {
                excelData[j][key][i].unit = key;
                html +=
                    '<li>' +
                    '<div class="dwtypebox">' +
                    '<img src="/assets/investigationInfo/img/icon23.png" />' +
                    '<h1>' + excelData[j][key][i].nameCompany + '</h1>' +
                    '<p>' + excelData[j][key][i].cardNumber + '</p>' +
                    '<span class="operate">' +
                    '<img src="/assets/investigationInfo/img/icon25.png" onclick=""/>' +
                    '<img src="/assets/investigationInfo/img/icon26.png" onclick="deleteUnitChild(' + j + "," + i + ')"/>' +
                    '</span>' +
                    '</div>' +
                    '</li>';
            }
            html +=
                '<li class="add">' +
                '<img src="/assets/investigationInfo/img/icon24.png" />' +
                '<div>新增</div>' +
                '</li>' +
                '</ul>' +
                '</div></div>';
        }
        // debugger
        // $("#investigation_unit").html(html);
        $("#xcdetailDiv").html(html);
    };
    deleteUnitChild = function (j, i) {
        var key = Object.keys(excelData[j])[0];
        excelData[j][key].splice(1, 1);
        randerInvestingationObj();
        showUnit(j);
    },
        deleteUnit = function (j) {
            var key = Object.keys(excelData[j])[0];
            delete excelData.splice(j, 1);
            randerInvestingationObj();
        },
        closeDialog = function (j) {
            $("#pubcover").hide();
            window.location.href = Feng.ctxPath + '/investigationInfo'
        },

        submit = function () {

            if (JSON.stringify(excelData) == "{}") {
                Feng.error("请导入协查清单")
                return
            }

            for (var i = 0; i < excelData.length; i++) {
                var key = Object.keys(excelData[i])[0];
                console.log("uploadFilesIndex.get(key) === " + uploadFilesIndex.get(key))
                if (uploadFilesIndex.get(key) === null || uploadFilesIndex.get(key) === undefined || uploadFilesIndex.get(key) === "undefined" || uploadFilesIndex.get(key) === 0) {
                    Feng.error(key + "账单数据协查尚未添加协查通知书，请添加后再再尝试发起协查！");
                    return
                }
            }

            var Documents_number = $("#Documents_number").val();
            var deadLine = $("#deadLine").val();
            var takePerson = $("#takePersion").val();
            if (deadLine===null||deadLine === ""||deadLine===undefined ) {
                Feng.error("请选择最迟反馈时间!");
            }

            var execldataTemp = [];
            for (var i = 0; i < excelData.length; i++) {
                var key = Object.keys(excelData[i])[0];
                execldataTemp.push(excelData[i][key]);
            }
            $.ajax({
                url: "/investigationInfo/addItem",
                type: "POST",
                data: {
                    excelData: JSON.stringify(execldataTemp),
                    deadLine: deadLine,
                    documentsNumber: Documents_number,
                    undertaker: takePerson,
                },
                dataType: "json",
                success: function (data) {
                    $("#pdfUpload").click();
                    $("#pubcover").show();
                },
                error: function (err) {
                    console.log(err.statusText);
                    console.log('异常');
                }
            });
        };
    /**
     * 弹出添加对话框
     */
    InvestigationInfo.openAddDlg = function () {
        func.open({
            title: '添加',
            content: Feng.ctxPath + '/investigationInfo/add',
            tableId: InvestigationInfo.tableId
        });
    };
    InvestigationInfo.deleteUnit = function () {

    }
    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    InvestigationInfo.openEditDlg = function (data) {
        func.open({
            title: '修改',
            content: Feng.ctxPath + '/investigationInfo/edit?infoId=' + data.infoId,
            tableId: InvestigationInfo.tableId
        });
    };

    laydate.render({
        elem: '#deadLine',
        // range: true,
        min: Feng.currentDate()
    });

    /**
     * 导出excel按钮
     */
    InvestigationInfo.exportExcel = function () {
        var checkRows = table.checkStatus(InvestigationInfo.tableId);
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
    InvestigationInfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/investigationInfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(InvestigationInfo.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("infoId", data.infoId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + InvestigationInfo.tableId,
        url: Feng.ctxPath + '/investigationInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: InvestigationInfo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        InvestigationInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

        InvestigationInfo.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        InvestigationInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + InvestigationInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            InvestigationInfo.openEditDlg(data);
        } else if (layEvent === 'delete') {
            InvestigationInfo.onDeleteItem(data);
        }
    });
});
