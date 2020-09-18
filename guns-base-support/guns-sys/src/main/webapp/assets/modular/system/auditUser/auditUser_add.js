/**
 * 添加或者修改页面
 */
var AuditUserInfoDlg = {
    data: {
        id: "",
        type: "",
        unitId: "",
        name: "",
        mobile: "",
        createTime: "",
        createBy: "",
        rank: "",
        auditBy: "",
        auditTime: "",
        auditContent: "",
        auditStatus: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;














    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/auditUser/details?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('auditUserForm', result.data.detail);
    form.val('auditUserForms', result.data.details);
    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        data.field.types="1"
        var ajax = new $ax(Feng.ctxPath + "/auditUser/editItem", function (data) {
            Feng.success("更新成功！");
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });
    form.on('submit(btnSubmits)', function (data) {
        data.field.types="2"
        var ajax = new $ax(Feng.ctxPath + "/auditUser/editItem", function (data) {
            Feng.success("更新成功！");
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });


});