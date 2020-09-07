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
        var serviceTypeArr = new Array();
        if(reactChild!=undefined){
            serviceTypeArr = reactChild.split(',');
            var constomerGradeArray = $("input[name='Multiple']");
            $.each(serviceTypeArr,function(i,json){
                //获取所有复选框对象的value属性，用json数组和他们匹配，如果有，则说明他应被选中
                $.each(constomerGradeArray,function(j,checkbox){
                    //获取复选框的value属性
                    var checkValue=$(checkbox).val();
                    if(json==checkValue){
                        $(checkbox).attr("checked",true);
                    }
                })
                //重新渲染(很重要：因为页面是用layui画的)
                form.render();
            })
        }

    });


    $('#close').click(function () {
        window.location.href = Feng.ctxPath + '/management';
    });
//表单提交事件
    form.on('submit(btnSubmitDaan)', function (data) {
        var result = [];
        $("[name='Multiple']:checkbox").each(function () {
            if ($(this).is(":checked")) {
                result.push($(this).attr("title"));
            }
        });
        data.field.Multiple=result.join(",")
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