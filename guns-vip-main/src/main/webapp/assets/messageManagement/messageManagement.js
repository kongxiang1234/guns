layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var MessageManagement = {
        tableId: "messageManagementTable"
    };

    /**
     * 初始化表格的列
     */
    MessageManagement.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'title', sort: true, title: '标题'},
            {field: 'createUser', sort: true, title: '发送人'},
            {field: 'createTime', sort: true, title: '发送时间'},
            {field: 'status', sort: true, title: '状态'},
            {field: 'type', sort: true, title: '类型'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    MessageManagement.search = function () {
        var queryData = {};


        table.reload(MessageManagement.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    MessageManagement.openAddDlg = function () {
        func.open({
            title: '添加',
            content: Feng.ctxPath + '/messageManagement/add',
            tableId: MessageManagement.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      MessageManagement.openEditDlg = function (data) {
          func.open({
              title: '修改',
              content: Feng.ctxPath + '/messageManagement/edit?id=' + data.id,
              tableId: MessageManagement.tableId
          });
      };


    /**
     * 导出excel按钮
     */
    MessageManagement.exportExcel = function () {
        var checkRows = table.checkStatus(MessageManagement.tableId);
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
    MessageManagement.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/messageManagement/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(MessageManagement.tableId);
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
        elem: '#' + MessageManagement.tableId,
        url: Feng.ctxPath + '/messageManagement/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: MessageManagement.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        MessageManagement.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    MessageManagement.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        MessageManagement.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + MessageManagement.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            MessageManagement.openEditDlg(data);
        } else if (layEvent === 'delete') {
            MessageManagement.onDeleteItem(data);
        }
    });
});
