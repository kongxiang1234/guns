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
    var Feedback = {
        tableId: "feedbackTable",
        condition: {
            name: "",
            fullType: "",
            timeLimit: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Feedback.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: 'ID'},
            {field: 'feedbackType', sort: true, title: '意见类型', templet: function (d) {
                    if (d.feedbackType == '1') {
                        return "电力安全生产";
                    } else if (d.feedbackType == '2') {
                        return "安全用电";
                    }else if (d.feedbackType == '3') {
                        return "电力设施保护";
                    }
                }},
            {field: 'feedbackSuggestions', sort: true, title: '意见建议'},
            {field: 'feedbackContent', sort: true, title: '意见反馈'},
            {field: 'createUser', sort: true, title: '反馈人'},
            {field: 'createTime', sort: true, title: '反馈时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };
    //渲染时间选择框
    laydate.render({
        elem: '#timeLimit',
        range: true,
        max: Feng.currentDate()
    });
    /**
     * 点击查询按钮
     */
    Feedback.search = function () {
        var queryData = {};
        queryData['type'] = $("#fullType").val();
        queryData['name'] = $("#name").val();
        queryData['timeLimit'] = $("#timeLimit").val();
        table.reload(Feedback.tableId, {
            where: queryData, page: {curr: 1}
        });
    };


    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    Feedback.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/feedback/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    Feedback.exportExcel = function () {
        var checkRows = table.checkStatus(Feedback.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Feedback.tableId,
        url: Feng.ctxPath + '/feedback/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Feedback.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Feedback.search();
    });
    $(function(){
        var htmls = '<option value="">请选择意见建议</option>';
        var ajax = new $ax(Feng.ctxPath + "/dict/listDictsByName?dictTypeName="+'意见', function (data) {
            for (var i = 0; i < data.data.length; i++) {
                var code = data.data[i].code;
                var name = data.data[i].name;
                htmls += '<option value = "' + code+ '">' + name + '</option>'
            }
            $("#fullType").html(htmls);
            form.render('select');
        }, function (data) {
        });
        ajax.start();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Feedback.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Feedback.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Feedback.jumpEditPage(data);
        }
    });
});
