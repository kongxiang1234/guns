/**
 * 详情对话框
 */
var InvestigationObjectInfoDlg = {
    data: {
        objectId: "",
        objectNotice: "",
        nameCompany: "",
        cardNumber: "",
        remarks: "",
        employeeCard: "",
        employeeCardSelect: "",
        infoId: "",
        createTime: "",
        createBy: "",
        updateTime: "",
        updateBy: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;





























    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/investigationObject/detail?objectId=" + Feng.getUrlParam("objectId"));
    var result = ajax.start();
    form.val('investigationObjectForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/investigationObject/editItem", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/investigationObject'
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/investigationObject'
    });
});