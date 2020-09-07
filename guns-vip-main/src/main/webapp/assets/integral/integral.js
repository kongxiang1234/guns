
function num(date){
    if(!/^\d+$/.test(date.value)) {

        Feng.error("只能是整数哦" );
        return false;
    }
}

layui.use(['table', 'admin', 'ax', 'func','form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;


    form.on('submit(btnSubmit)', function (data) {
        var date=data.field;
        if(date.mrcs==""){
            Feng.error("请输入练习次数" );
            return false;
        }
        if(date.mrjf==""){
            Feng.error("请输入积分" );
            return false;
        }
        if(date.mrdk==""){
            Feng.error("请输入积分 ：积分抵扣违章记分一分" );
            return false;
        }
        if(date.mzcs==""){
            Feng.error("请输入测试次数" );
            return false;
        }
        if(date.mzjf==""){
            Feng.error("请输入积分" );
            return false;
        }
        if(date.mzdk==""){
            Feng.error("请输入累计排名：前" );
            return false;
        }
        if(date.mycs==""){
            Feng.error("请输入练习次数" );
            return false;
        }
        if(date.myjf==""){
            Feng.error("请输入积分" );
            return false;
        }
        if(date.mydk==""){
            Feng.error("请输入积分 ：积分抵扣违章记分一分" );
            return false;
        }

        if(date.id!=""){
            var ajax = new $ax(Feng.ctxPath + "/integral/editItem", function (data) {
                Feng.success("更新成功！");
                window.location.href = Feng.ctxPath + '/integral/integ'
            }, function (data) {
                Feng.error("添加失败！" + data.responseJSON.message)
            });
            ajax.set(data.field);
            ajax.start();
        }else {
            var ajax = new $ax(Feng.ctxPath + "/integral/addItem", function (data) {
                Feng.success("添加成功！");
                window.location.href = Feng.ctxPath + '/integral/integ'
            }, function (data) {
                Feng.error("添加失败！" + data.responseJSON.message)
            });
            ajax.set(data.field);
            ajax.start();
        }



        return false;
    });
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
            {field: 'name', sort: true, title: '员工名字'},
            {field: 'userId', sort: true,hide:true, title: '员工id'},
            {field: 'ssdw', sort: true, title: '所属单位'},
            {field: 'yjIntegral', sort: true, title: '有奖积分'},
            {field: 'mrIntegral', sort: true, title: '每日积分'},
            {field: 'mzIntegral', sort: true, title: '每周积分'},
            {field: 'myIntegral', sort: true, title: '每月积分'},
            {field: 'dkwzIntegral', sort: true, title: '可抵扣违章记分'},
            {field: 'sdkwzIntegral', sort: true, title: '实际抵扣违章记分'},
            {field: 'syIntegral', sort: true, title: '剩余积分'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Integral.search = function () {
        var queryData = {};

        queryData['name'] = $("#name").val();
        table.reload(Integral.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    Integral.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/integral/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    Integral.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/integral/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    Integral.exportExcel = function () {
        var checkRows = table.checkStatus(Integral.tableId);
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
    Integral.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/integral/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Integral.tableId);
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
        elem: '#' + Integral.tableId,
        url: Feng.ctxPath + '/integral/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Integral.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Integral.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Integral.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Integral.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Integral.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Integral.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            Integral.onDeleteItem(data);
        }
    });
});
