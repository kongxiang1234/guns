layui.use(['table', 'admin', 'ax', 'func','laydate','form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var laydate = layui.laydate;
    var form = layui.form;
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
            {type: 'radio'},
            {field: 'id', hide: true, title: ''},
            {field: 'name', sort: true, title: '姓名'},
            {field: 'specialtys', sort: true, title: '单位'},
            {field: 'pic', sort: true, title: '工作证',width: 200, unresize: true, templet: function (d) {
                    var url = "/rest/system/preview/"+d.pic+"";
                    return '<img src="' + url + '" class="tdImg" width="50"/>';
                }},
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

        queryData['name'] = $("#name").val();
        table.reload(Content.tableId, {
            where: queryData, page: {curr: 1}
        });
    };
    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Content.tableId,
        url: Feng.ctxPath + '/mgr/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Content.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Content.search();
    });
    form.on('submit(btnSubmit)', function () {
        debugger
        var da=layui.table.checkStatus(Content.tableId).data;
        var parent$ = window.parent.layui.jquery;
        var url = "/rest/system/preview/"+da[0].pic+"";
        parent$("#img2").attr('src', url);
        parent$("#img2val").val(da[0].pic);
        admin.closeThisDialog();
        return false;
    });

    // 工具条点击事件
    table.on('tool(' + Content.tableId + ')', function (obj) {
        debugger
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Content.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            Content.onDeleteItem(data);
        }
    });
});
