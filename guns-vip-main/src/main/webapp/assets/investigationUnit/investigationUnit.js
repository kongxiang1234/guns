layui.use(['table', 'admin', 'ax', 'func'], function () {
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
    var InvestigationUnit = {
        tableId: "investigationUnitTable"
    };

    /**
     * 初始化表格的列
     */
    InvestigationUnit.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'unitId', hide: true, title: '主键id'},
            {field: 'unitName', sort: true, title: '单位名称'},
            {field: 'unitType', sort: true, title: '单位类型'},
            {field: 'unitAddr', sort: true, title: '单位地址'},
            {field: 'unitLogo', sort: true, title: '图片',width: 100, unresize: true, templet: function (d) {
                    var url = "/rest/system/preview/"+d.unitLogo+"";
                    return '<img src="' + url + '" class="tdImg" width="50"/>';
                }},
            {field: 'unitLeaderName', sort: true, title: '负责人姓名'},
            {field: 'unitLeaderIdcard', sort: true, title: '身份证号'},
            {field: 'unitLeaderPosition', sort: true, title: '负责人姓名'},
            {field: 'unitLeaderPhonenum', sort: true, title: '手机号码'},
            {field: 'unitLeaderEmail', sort: true, title: '邮箱'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    InvestigationUnit.search = function () {
        var queryData = {};


        table.reload(InvestigationUnit.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    InvestigationUnit.openAddDlg = function () {
        func.open({
            title: '添加',
            content: Feng.ctxPath + '/investigationUnit/add',
            tableId: InvestigationUnit.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      InvestigationUnit.openEditDlg = function (data) {
          func.open({
              title: '修改',
              content: Feng.ctxPath + '/investigationUnit/edit?unitId=' + data.unitId,
              tableId: InvestigationUnit.tableId
          });
      };


    /**
     * 导出excel按钮
     */
    InvestigationUnit.exportExcel = function () {
        var checkRows = table.checkStatus(InvestigationUnit.tableId);
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
    InvestigationUnit.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/investigationUnit/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(InvestigationUnit.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("unitId", data.unitId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + InvestigationUnit.tableId,
        url: Feng.ctxPath + '/investigationUnit/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: InvestigationUnit.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        InvestigationUnit.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    InvestigationUnit.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        InvestigationUnit.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + InvestigationUnit.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            InvestigationUnit.openEditDlg(data);
        } else if (layEvent === 'delete') {
            InvestigationUnit.onDeleteItem(data);
        }
    });
});
