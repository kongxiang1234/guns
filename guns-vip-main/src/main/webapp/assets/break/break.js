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
            {field: 'dcNoumenon', sort: true, title: '督查主体'},
            {field: 'dcNumber', sort: true, title: '通知书编号'},
            {field: 'dcTime', sort: true, title: '日期'},
            {field: 'wzStatus', sort: true, title: '违章级别'},
            {field: 'oneSecondary', sort: true, title: '违章一级分类'},
            {field: 'secondary', sort: true, title: '违章二级分类'},
            {field: 'siteCategory', sort: true, title: '现场类别'},
            {field: 'supervision', sort: true, title: '督察形式'},
            {field: 'construction', sort: true, title: '施工单位'},
            {field: 'score', sort: true, title: '计分'},
            {field: 'nature', sort: true, title: '人员性质'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Break.search = function () {
        var queryData = {};
        queryData['dcTime'] = $('#dcTime').val();
        queryData['dcType'] = $('#dcType').val();
        queryData['wzTitle'] = $('#wzTitle').val();
        queryData['wzType'] = $('#wzType').val();
        queryData['wzStatus'] = $('#wzStatus').val();
        table.reload(Break.tableId, {
            where: queryData, page: {curr: 1}
        });
    };
    $(function(){
        var url= "/dict/listDictsByName?dictTypeName="+'违章级别';
        func.initDictSelect(url,"dcType","code","name");
        var url= "/dict/listDictsByName?dictTypeName="+'违章一级分类';
        func.initDictSelect(url,"wzType","code","name");
        var url= "/dict/listDictsByName?dictTypeName="+'违章二级分类';
        func.initDictSelect(url,"wzStatus","code","name");
    });
    /**
     * 跳转到添加页面
     */
    Break.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/break/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    Break.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/break/edit?id=' + data.id
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

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Break.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/break/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Break.tableId);
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
        elem: '#' + Break.tableId,
        url: Feng.ctxPath + '/break/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Break.initColumn()
    });
    //渲染时间选择框
    laydate.render({
        elem: '#dcTime',
        range: true,
        max: Feng.currentDate()
    });
    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Break.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Break.jumpAddPage();

    });
    $('#btngaoSearch').click(function () {
        $('#seach').removeClass('layui-hide');
        $('#seach1').removeClass('layui-hide');
        $('#seach2').removeClass('layui-hide');
        $('#seach3').removeClass('layui-hide');

    });


    // 导出excel
    $('#btnExp').click(function () {
        Break.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Break.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Break.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            Break.onDeleteItem(data);
        }
    });

    upload.render({
        elem: '#btnExr'
        , url:Feng.ctxPath +'/break/uploadExcel'
        ,accept: 'file'
        , done: function (res) {
            if(res.success){
                var ajax = new $ax(Feng.ctxPath + "/break/getUploadData", function (data) {
                    if(data.success){
                        Feng.success("导入成功！");

                        table.reload(Break.tableId);
                    }else {
                        Feng.error(data.message);
                    }
                }, function (data) {
                    Feng.error("导入失败！" + data.message)
                });
                ajax.start();
            }else {
                Feng.error( res.message)
            }
        }
        , error: function () {
            //请求异常回调
        }
    });
});
