/**
 * 角色详情对话框
 */
var DeptInfoDlg = {
    data: {
        pid: "",
        pName: ""
    }
};

layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    $(function(){
        $("#fullName").html('<option value="">请选择部门性质</option>');
        var ajax = new $ax(Feng.ctxPath + "/dict/listDictsByName?dictTypeName="+'部门', function (data) {
            for (var i = 0; i < data.data.length; i++) {
                var code = data.data[i].code;
                var name = data.data[i].name;
                $("#fullName").append('<option value="' + code + '">' + name + '</option>');
            }
            form.render();
        }, function (data) {
        });
        ajax.start();
    });
    //获取部门信息
    var ajax = new $ax(Feng.ctxPath + "/dept/detail/" + Feng.getUrlParam("deptId"));
debugger
    var result = ajax.start();
    $("#pidsV").val(result.pidsV);
    form.val('deptForm', result);

    // 点击上级角色时
    $('#pName').click(function () {
        debugger
        var pids= $("#pidsV").val();
        if (pids!=null){
            var treeUrl = encodeURIComponent("/dept/tree?pid="+pids);
        }else {
            var treeUrl = encodeURIComponent("/dept/tree");
        }
        var formName = encodeURIComponent("parent.DeptInfoDlg.data.pName");
        var formId = encodeURIComponent("parent.DeptInfoDlg.data.pid");


        layer.open({
            type: 2,
            title: '父级部门',
            area: ['300px', '400px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                $("#pidsV").val(DeptInfoDlg.data.pid);
                $("#pName").val(DeptInfoDlg.data.pName);
            }
        });
    });

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/dept/update", function (data) {
            Feng.success("修改成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();

        }, function (data) {
            Feng.error("修改失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        //添加 return false 可成功跳转页面
        return false;
    });

});