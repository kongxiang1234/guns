layui.use(['table', 'admin', 'ax', 'func','laydate','upload', 'ztree', 'treeTable'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;
    var func = layui.func;
    var laydate = layui.laydate;
    var treeTable = layui.treeTable;
    var upload = layui.upload;
    /**
     * 管理
     */
    var Break = {
        tableId: "breakTable"
    };

    /**
     * 初始化表格的列
     */
    Break.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'wzUnit', sort: true, title: '违章单位', templet: function (d) {
                    var url = Feng.ctxPath + '/break/breakDan?wzUnit=' + d.wzUnit;
                    return '<a style="color: #01AAED;" href="' + url + '">' + d.wzUnit + '</a>';
                }},
            {field: 'num', sort: true, title: '违章总计分'},
            {field: 'dcTime', sort: true, title: '违章时间'},
            {field: 'wzPeople', sort: true, title: '违章总人员'},
        ]];
    };

    /**
     * 点击查询按钮
     */
    Break.search = function () {
        var queryData = {};
        queryData['time'] = $('#time').val();
        queryData['wzUnit'] = $('#wzUnit').val();
        table.reload(Break.tableId, {
            where: queryData, page: {curr: 1}
        });
    };
    /**
     * 导出excel按钮
     */
    Break.exportExcel = function () {
        var checkRows = table.checkStatus(Break.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Break.tableId,
        url: Feng.ctxPath + '/break/listTotal',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Break.initColumn()
    });

    //渲染时间选择框
    laydate.render({
        elem: '#time',
        range: true,
        max: Feng.currentDate()
    });
    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Break.search();
    });




    // 导出excel
    $('#btnExp').click(function () {
        Break.exportExcel();
    });


});
