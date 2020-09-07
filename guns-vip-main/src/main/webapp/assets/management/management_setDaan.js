/**
 * 添加或者修改页面
 */
var ManagementInfoDlg = {
    data: {

    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects','func'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    $(function(){
        var reactChild=$("#type").val();
        form.val('managementForm', {
            "single":reactChild,
            "flag":reactChild,
        });
    });


    $('#close').click(function () {
        window.location.href = Feng.ctxPath + '/management';
    });
//表单提交事件
    form.on('submit(btnSubmitDaan)', function (data) {
        debugger
        var ajax
        if (data.field.Id==null || data.field.Id==''){
            ajax = new $ax(Feng.ctxPath + "/management/addAnswer", function (data) {
                Feng.success("新增成功！");
                //传给上个页面，刷新table用
                window.location.href = Feng.ctxPath + '/management';
            }, function (data) {
                Feng.error("更新失败！" + data.responseJSON.message)
            });
        }else {
            ajax = new $ax(Feng.ctxPath + "/management/updateAnswer", function (data) {
                Feng.success("更新成功！");
                //传给上个页面，刷新table用
                window.location.href = Feng.ctxPath + '/management';
            }, function (data) {
                Feng.error("更新失败！" + data.responseJSON.message)
            });
        }
        ajax.set(data.field);
        ajax.start();

        return false;
    });
});