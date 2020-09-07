/**
 * 添加或者修改页面
 */
var ManagementInfoDlg = {
    data: {
        id: "",
        title: "",
        questionsType: "",
        specialty: "",
        type: "",
        status: "",
        createTime: "",
        createUser: "",
        updateTime: "",
        updateUser: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects','func'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var func = layui.func;
    var ue = UE.getEditor('title', {
        enableAutoSave: false,
        autoHeightEnabled: true,
        autoFloatEnabled: false,
        scaleEnabled: true,         //滚动条
        initialFrameHeight: 200     //默认的编辑区域高度
    });

    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function (action) {
        if (action === 'uploadimage' || action === 'uploadscrawl' || action === 'uploadimage') {
            return Feng.ctxPath + '/ueditor/imgUpdate';
        } else if (action === 'uploadfile') {
            return Feng.ctxPath + '/ueditor/uploadfile';
        } else if (action === 'uploadvideo') {
            return Feng.ctxPath + '/ueditor/uploadvideo';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    };




    $(function(){
        var url= "/dict/listDictsByName?dictTypeName="+'难易';
        func.initDictSelect(url,"type","code","name");
        var url= "/dict/listDictsByName?dictTypeName="+'所属';
        func.initDictSelect(url,"specialty","code","name");
        var url= "/dict/listDictsByName?dictTypeName="+'试题';
        func.initDictSelect(url,"questionsType","code","name");
        var url= "/dict/listDictsByName?dictTypeName="+'专题';
        func.initDictSelect(url,"ztype","code","name");
    });

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/management/addItem", function (data) {
            Feng.success("添加成功！");
            window.location.href = Feng.ctxPath + '/management';
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });
    $('#close').click(function () {
        window.location.href = Feng.ctxPath + '/management';
    });
//表单提交事件
    form.on('submit(btnSubmitDaan)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/management/addAnswer", function (data) {
            Feng.success("更新成功！");
            //传给上个页面，刷新table用
            window.location.href = Feng.ctxPath + '/management';
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });
});