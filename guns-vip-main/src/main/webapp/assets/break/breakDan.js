layui.use(['table', 'admin', 'ax', 'func','laydate','upload'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var laydate = layui.laydate;
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
            {field: 'wzPeople', sort: true, title: '违章人员'},
            {field: 'xmUnit', sort: true, title: '违章单位'},
            {field: 'score', sort: true, title: '违章计分'},
            {field: 'dcTime', sort: true, title: '违章时间'},
        ]];
    };
    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Break.tableId,
        url: Feng.ctxPath + '/break/listDan',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        where: {wzUnit: $("#wzUnit").val()},
        cols: Break.initColumn()
    });


    /**
     * 点击查询按钮
     */
    Break.search = function () {
        var queryData = {};
        queryData['wzPeople'] = $('#wzPeople').val();
        table.reload(Break.tableId, {
            where: queryData, page: {curr: 1}
        });
    };
    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Break.search();
    });
});
