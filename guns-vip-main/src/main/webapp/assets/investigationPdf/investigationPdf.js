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
    var InvestigationPdf = {
        tableId: "investigationPdfTable"
    };

    /**
     * 初始化表格的列
     */
    InvestigationPdf.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'infoId', hide: true, title: '协查信息ID'},
            {type: 'numbers', width: 150, sort: true, title: '序号'},
            {field: 'documentName', sort: true, title: '协查文书号'},
            {field: 'undertakerName', sort: true, title: '承办人'},
            {field: 'applyTime', sort: true, title: '协查日期'},
            {field: 'fileNum', sort: true, title: '文件数量'}
        ]];
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + InvestigationPdf.tableId,
        url: Feng.ctxPath + '/investigationPdf/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: InvestigationPdf.initColumn()
    });

});
