layui.use(['table', 'admin', 'ax', 'func','laydate'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var laydate = layui.laydate;
    /**
     * 管理
     */
    var Content = {
        tableId: "contentTable"
    };

    /**
     * 初始化表格的列
     */
    Content.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'title', sort: true, title: '标题'},
            {field: 'content', sort: true, title: '内容'},
            {field: 'pic', sort: true, title: '图片',width: 100, unresize: true, templet: function (d) {
                    var url = "/rest/system/preview/"+d.pic+"";
                    return '<img src="' + url + '" class="tdImg" width="50"/>';
                }},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'createUser', sort: true, title: '创建人'},
            {field: 'status', sort: true, title: '发布类型', templet: function (d) {
                    if (d.status == '1') {
                        return "未发布";
                    } else if (d.status == '2') {
                        return "已发布";
                    }
                }},
            {field: 'type', sort: true, title: '信息类型', templet: function (d) {
                    if (d.type == '1') {
                        return "警告教育";
                    } else if (d.type == '2') {
                        return "安全释义";
                    }else if (d.type == '3') {
                        return "安全发布";
                    }else if (d.type == '4') {
                        return "电力设施保护";
                    }else if (d.type == '5') {
                        return "违章通报";
                    }else if (d.type == '6') {
                        return "专题测试";
                    }
                }},
            {field: 'reading', sort: true, title: '阅读量'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };
    //渲染时间选择框
    laydate.render({
        elem: '#time',
        range: true,
        max: Feng.currentDate()
    });
    /**
     * 点击查询按钮
     */
    Content.search = function () {
        var queryData = {};

        queryData['title'] = $('#title').val();
        queryData['time'] = $('#time').val();
        queryData['status'] = $('#status').val();
        queryData['type'] = $('#type').val();
        table.reload(Content.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    Content.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/content/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    Content.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/content/edit?Id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    Content.exportExcel = function () {
        var checkRows = table.checkStatus(Content.tableId);
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
    Content.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/content/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Content.tableId);
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
        elem: '#' + Content.tableId,
        url: Feng.ctxPath + '/content/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Content.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Content.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Content.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Content.exportExcel();
    });
    // 导出excel
    $('#btnFa').click(function () {
        Content.exportFa();
    });
    /**
     * 导出excel按钮
     */
    Content.exportFa = function () {
        var checkRows = table.checkStatus(Content.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要发布的类型");
        } else {
            var id="";
            for (let i = 0; i < checkRows.data.length; i++) {
                id=id+checkRows.data[i].id+","
            }
            var ajax = new $ax(Feng.ctxPath + "/content/piFabu", function (data) {
                Feng.success("发布成功!");
                table.reload(Content.tableId);
            }, function (data) {
                Feng.error("发布失败!" + data.responseJSON.message + "!");
            });
            ajax.set("title", id);
            ajax.start();
        }
    };
    // 工具条点击事件
    table.on('tool(' + Content.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Content.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            Content.onDeleteItem(data);
        }
    });
});
