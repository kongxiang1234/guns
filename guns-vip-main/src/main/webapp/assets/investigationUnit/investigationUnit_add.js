/**
 * 添加或者修改页面
 */
var InvestigationUnitInfoDlg = {
    data: {
        unitId: "",
        unitName: "",
        unitType: "",
        unitAddr: "",
        unitLogo: "",
        unitLeaderName: "",
        unitLeaderIdcard: "",
        unitLeaderPosition: "",
        unitLeaderPhonenum: "",
        unitLeaderEmail: "",
        createTime: "",
        createBy: "",
        updateTime: "",
        updateBy: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects','func'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var upload = layui.upload;
    var func = layui.func;
//普通图片上传
    upload.render({
        elem: '#picBtn'
        , url: Feng.ctxPath + '/system/upload'
        , before: function (obj) {
            obj.preview(function (index, file, result) {
                $('#img1').attr('src', result);
            });
        }
        , done: function (res) {
            $("#unitLogo").val(res.data.fileId);
            Feng.success(res.message);
        }
        , error: function () {
            Feng.error("上传图片失败！");
        }
    });

































    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/investigationUnit/addItem", function (data) {
            Feng.success("添加成功！");
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });


});