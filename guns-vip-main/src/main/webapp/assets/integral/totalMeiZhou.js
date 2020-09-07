var nowDate = new Date();
var cloneNowDate = new Date();
var fullYear = nowDate.getFullYear();
var month = nowDate.getMonth() + 1; // getMonth 方法返回 0-11，代表1-12月
var endOfMonth = new Date(fullYear, month, 0).getDate(); // 获取本月最后一天
function getFullDate(targetDate) {
    var D, y, m, d;
    if (targetDate) {
        D = new Date(targetDate);
        y = D.getFullYear();
        m = D.getMonth() + 1;
        d = D.getDate();
    } else {
        y = fullYear;
        m = month;
        d = date;
    }
    m = m > 9 ? m : '0' + m;
    d = d > 9 ? d : '0' + d;
    return y + '-' + m + '-' + d;
};
var endDate = getFullDate(cloneNowDate.setDate(endOfMonth));//当月最后一天
var starDate = getFullDate(cloneNowDate.setDate(1));//当月第一天
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
            {field: 'user', sort: true, title: '员工名字'},
            {field: 'depart', sort: true, title: '所属单位'},
            {field: 'ytotal', sort: true, title: '应练习次数'},
            {field: 'stotal', sort: true, title: '实练习次数'},
            {field: 'qtotal', sort: true, title: '缺练习次数'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Integral.search = function () {
        var queryData = {};
        queryData['etime'] = $("#etime").val();
        queryData['btime'] = $("#btime").val();
        queryData['name'] = $("#name").val();
        table.reload(Integral.tableId, {
            where: queryData, page: {curr: 1}
        });
    };


    //渲染时间选择框
    var laydate = layui.laydate;
    laydate.render({
        elem: '#btime' //指定元素
        ,type: 'date',
        value: starDate
    });
    //渲染时间选择框
    var laydate = layui.laydate;
    laydate.render({
        elem: '#etime' //指定元素
        ,type: 'date',
        value: endDate
    });
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
        url: Feng.ctxPath + '/practice/listMeizhou',
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
