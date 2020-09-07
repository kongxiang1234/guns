
layui.use(['table', 'admin', 'ax', 'func','form','laydate'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;
    /**
     * 管理
     */
    var Integral = {
        tableId: "integralTable"
    };

    /**
     * 初始化表格的列
     */
    Integral.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'date', sort: true, title: '缺练习日期'},
        ]];
    };
    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Integral.tableId,
        url: Feng.ctxPath + '/practice/queListMeiri',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        where: {"name":$("#account").val(),"btime":$("#btime").val(),"etime":$("#etime").val()},
        cols: Integral.initColumn()
    });


});
