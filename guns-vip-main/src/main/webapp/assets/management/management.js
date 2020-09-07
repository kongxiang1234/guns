layui.use(['table', 'admin', 'ax', 'func','laydate','form','upload'], function () {
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
    var Management = {
        tableId: "managementTable"
    };
    upload.render({
        elem: '#btnExr'
        , url:Feng.ctxPath +'/break/uploadExcel'
        ,accept: 'file'
        , done: function (res) {
            if(res.success){
                var ajax = new $ax(Feng.ctxPath + "/management/getUploadData", function (data) {
                    if(data.success){
                        Feng.success("导入成功！");

                        table.reload(Management.tableId);
                    }else {
                        Feng.error(data.message);
                    }
                }, function (data) {
                    Feng.error("导入失败！" + data.message)
                });
                ajax.start();
            }else {
                Feng.error( res.message)
            }
        }
        , error: function () {
            //请求异常回调
        }
    });


    /**
     * 初始化表格的列
     */
    Management.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id',  title: 'ID',hide: true},
            {field: 'number', sort: true, title: '试题编号'},
            {field: 'title', sort: true, title: '标题'},
            {field: 'questionsType', sort: true, title: '试题类型', templet: function (d) {
                    if (d.questionsType == '1') {
                        return "单选题";
                    } else if (d.questionsType == '2') {
                        return "多选题";
                    }else if (d.questionsType == '3') {
                        return "判断题";
                    }
                }},
            {field: 'specialty', sort: true, title: '所属专业'},
            {field: 'type', sort: true, title: '难易程度', templet: function (d) {
                    if (d.type == '1') {
                        return "难度高";
                    } else if (d.type == '2') {
                        return "难度中";
                    }else if (d.type == '3') {
                        return "难度低";
                    }
                }},
            {field: 'status', sort: true, title: '启用状态', templet: function (d) {
              
                    if (d.status == 'ENABLE') {
                        return "启用";
                    } else if (d.status == 'DISABLE') {
                        return "禁用";
                    }
                }},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'createUser', sort: true, title: '创建人'},

            {align: 'center', minWidth: 200, toolbar: '#tableBar', title: '操作'}
        ]];
    };
//渲染时间选择框
    laydate.render({
        elem: '#createTime',
        range: true,
        max: Feng.currentDate()
    });
    $(function(){
        var url= "/dict/listDictsByName?dictTypeName="+'难易';
        func.initDictSelect(url,"type","code","name");
        var url= "/dict/listDictsByName?dictTypeName="+'所属';
        func.initDictSelect(url,"specialty","code","name");
        var url= "/dict/listDictsByName?dictTypeName="+'试题';
        func.initDictSelect(url,"questionsType","code","name");
    });
    /**
     * 点击查询按钮
     */
    Management.search = function () {
        var queryData = {};
        queryData['title'] = $('#title').val();
        queryData['questionsType'] = $('#questionsType').val();
        queryData['specialty'] = $('#specialty').val();
        queryData['type'] = $('#type').val();
        queryData['createTime'] = $('#createTime').val();

        table.reload(Management.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Management.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/management/add';

    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      Management.openEditDlg = function (data) {
          debugger
          window.location.href = Feng.ctxPath + '/management/edit?Id=' + data.id;

      };


    /**
     * 导出excel按钮
     */
    Management.exportExcel = function () {
        var checkRows = table.checkStatus(Management.tableId);
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
    Management.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/management/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Management.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Management.tableId,
        url: Feng.ctxPath + '/management/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Management.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Management.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Management.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Management.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Management.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Management.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Management.onDeleteItem(data);
        } else if (layEvent === 'daan') {
            Management.onDaanItem(data);
        }
    });

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    Management.onDaanItem = function (data) {
        debugger
        if (data.questionsType==1){
            window.location.href = Feng.ctxPath + '/management/setDaan?Id=' + data.id;
        }else if (data.questionsType==2){
            window.location.href = Feng.ctxPath + '/management/setDaanDuo?Id=' + data.id;
        }else if (data.questionsType==3){
            window.location.href = Feng.ctxPath + '/management/setDaanPan?Id=' + data.id;
        }
    };


    // 修改user状态
    form.on('switch(status)', function (obj) {

        var userId = obj.elem.value;
        var checked = obj.elem.checked ? true : false;

        Management.changeUserStatus(userId, checked);
    });
    /**
     * 修改用户状态
     *
     * @param userId 用户id
     * @param checked 是否选中（true,false），选中就是解锁用户，未选中就是锁定用户
     */
    Management.changeUserStatus = function (userId, checked) {
        if (checked) {
            var ajax = new $ax(Feng.ctxPath + "/mgr/unfreeze", function (data) {
                Feng.success("解除禁用成功!");
            }, function (data) {
                Feng.error("解除禁用失败!");
                table.reload(Management.tableId);
            });
            ajax.set("userId", userId);
            ajax.start();
        } else {
            var ajax = new $ax(Feng.ctxPath + "/mgr/freeze", function (data) {
                Feng.success("禁用成功!");
            }, function (data) {
                Feng.error("禁用失败!" + data.responseJSON.message + "!");
                table.reload(Management.tableId);
            });
            ajax.set("userId", userId);
            ajax.start();
        }
    };
});
