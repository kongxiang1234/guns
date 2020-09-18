layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var AuditUser = {
        tableId: "auditUserTable"
    };

    /**
     * 初始化表格的列
     */
    AuditUser.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'type', sort: true, title: '类型'},
            {field: 'unitName', sort: true, title: '单位名称'},
            {field: 'name', sort: true, title: '姓名'},
            {field: 'mobile', sort: true, title: '手机号'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'createBy', sort: true, title: '创建人'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    AuditUser.search = function () {
        var queryData = {};


        table.reload(AuditUser.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    AuditUser.openAddDlg = function () {
        func.open({
            title: '添加',
            content: Feng.ctxPath + '/auditUser/add',
            tableId: AuditUser.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      AuditUser.openEditDlg = function (data) {
          if (data.type=="变更审核"){
              func.open({
                  title: '审核',
                  content: Feng.ctxPath + '/auditUser/add?id=' + data.id,
                  tableId: AuditUser.tableId
              });
          }else {
              func.open({
                  title: '审核',
                  content: Feng.ctxPath + '/auditUser/edit?id=' + data.id,
                  tableId: AuditUser.tableId
              });
          }
      };


    /**
     * 导出excel按钮
     */
    AuditUser.exportExcel = function () {
        var checkRows = table.checkStatus(AuditUser.tableId);
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
    AuditUser.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/auditUser/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(AuditUser.tableId);
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
        elem: '#' + AuditUser.tableId,
        url: Feng.ctxPath + '/auditUser/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: AuditUser.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        AuditUser.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    AuditUser.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        AuditUser.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + AuditUser.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            AuditUser.openEditDlg(data);
        } else if (layEvent === 'delete') {
            AuditUser.onDeleteItem(data);
        }
    });
});
