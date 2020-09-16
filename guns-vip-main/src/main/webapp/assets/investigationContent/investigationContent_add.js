/**
 * 添加或者修改页面
 */
var InvestigationContentInfoDlg = {
    data: {
        contentId: "",
        nameCompany: "",
        cardNumber: "",
        undertaker: "",
        unitId: "",
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









    sendInvestionUpdate = function(){
        $.ajax({
            url: "/investigationContent/editInvestigationContent",
            type: "POST",
            data:{
                infoId:info_id,
                stauts : '2',
            },
            dataType: "json",
            success: function(data){
                Feng.success("发送成功!");
                window.location.href = Feng.ctxPath + '/investigationContent'
            },
            error:function(err){
                Feng.error("发送出错，请联系管理员");
            }
        });
    },













    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/investigationContent/addItem", function (data) {
            Feng.success("添加成功！");
            window.location.href = Feng.ctxPath + '/investigationContent'
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/investigationContent'
    });

});