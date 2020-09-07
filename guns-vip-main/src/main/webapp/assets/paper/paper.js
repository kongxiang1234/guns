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
    var Paper = {
        tableId: "paperTable"
    };
    $(function(){
        var url= "/dict/listDictsByName?dictTypeName="+'所属';
        func.initDictSelect(url,"specialty","code","name");
        var url= "/dict/listDictsByName?dictTypeName="+'试卷状态';
        func.initDictSelect(url,"type","code","name");
    });
    /**
     * 初始化表格的列
     */
    Paper.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'sjbh', sort: true, title: '试卷编号'},
            {field: 'sjname', sort: true, title: '试卷名称'},
            {field: 'sszy', sort: true, title: '所属专业'},
            {field: 'danxtd', sort: true, title: '单选题数'},

            {field: 'danxtValue', sort: true, title: '单选题分数'},

            {field: 'duoxtd', sort: true, title: '多选题数'},
            {field: 'duoxtValue', sort: true, title: '多选题分数'},

            {field: 'pdtd', sort: true, title: '判断题数'},
            {field: 'pdtValue', sort: true, title: '判断题分数'},
            {field: 'tmtotle', sort: true, title: '合计题目数'},
            {field: 'tmfs', sort: true, title: '题目分数'},
            {field: 'btime', sort: true, title: '考试开始时间'},
            {field: 'etime', sort: true, title: '考试结束时间'},
            {field: 'create_time', sort: true, title: '生成试卷时间'},
            {field: 'create_user', sort: true, title: '生成人'},
            {field: 'type', sort: true, title: '状态', templet: function (d) {
                    if (d.type == '1') {
                        return "未发布";
                    } else if (d.type == '2') {
                        return "已发布";
                    }else if (d.type == '3') {
                        return "考试中";
                    }else if (d.type == '4') {
                        return "已结束";
                    }
                }},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };
    $('#btngaoSearch').click(function () {
        $('#seach').removeClass('layui-hide');
        $('#seach1').removeClass('layui-hide');
        $('#seach2').removeClass('layui-hide');


    });
    /**
     * 点击查询按钮
     */
    Paper.search = function () {
        var queryData = {};
        queryData['sjbh'] = $('#sjbh').val();

        queryData['time'] = $('#time').val();
        queryData['specialty'] = $('#specialty').val();
        queryData['type'] = $('#type').val();

        table.reload(Paper.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Paper.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/paper/add';

    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      Paper.openEditDlg = function (data) {
          window.location.href = Feng.ctxPath + '/paper/edit?paperId='+ data.id+"&sszy="+ data.sszy;

      };


    /**
     * 导出excel按钮
     */
    Paper.exportExcel = function () {
        var checkRows = table.checkStatus(Paper.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };
//渲染时间选择框

    //渲染时间选择框
    laydate.render({
        elem: '#time',
        range: true,
        max: Feng.currentDate()
    });
    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Paper.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/paper/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Paper.tableId);
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
        elem: '#' + Paper.tableId,
        url: Feng.ctxPath + '/paper/list',
        page: true,
        cellMinWidth: 150 ,
        height: "full-158",

        cols: Paper.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Paper.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

          Paper.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Paper.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Paper.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Paper.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Paper.onDeleteItem(data);
        }
    });
});
