/**
 * 详情对话框
 */
var ContentInfoDlg = {
    data: {
        id: "",
        title: "",
        content: "",
        pic: "",
        createTime: "",
        createUser: "",
        updateTime: "",
        updateUser: "",
        status: "",
        type: "",
        reading: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects','upload','func'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var func = layui.func;
    var upload = layui.upload;
//普通图片上传
    upload.render({
        elem: '#picBtn'
        , url: Feng.ctxPath + '/system/upload'
        , before: function (obj) {
            obj.preview(function (index, file, result) {
                debugger
                $('#img1').attr('src', result);
            });
        }
        , done: function (res) {
            debugger
            $("#pic").val(res.data.fileId);
            Feng.success(res.message);
        }
        , error: function () {
            Feng.error("上传图片失败！");
        }
    });
    //实例化编辑器
    var ue = UE.getEditor('content', {
        enableAutoSave: false,
        autoHeightEnabled: true,
        autoFloatEnabled: false,
        scaleEnabled: true,//滚动条
        initialFrameHeight: 400 //默认的编辑区域高度
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

















    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/content/detail?id=" + $("#contentId").val());
    debugger
    var result = ajax.start();

    var url = "/rest/system/preview/"+result.data.pic+"";
    $('#img1').attr('src', url);
    form.val('contentForm', result.data);
    $(function(){
        var reactChild=result;
        var url= "/dict/listDictsByName?dictTypeName="+'专题';
        func.initDictSelect(url,"ztype","code","name");
        if (reactChild.data.type==6){
            $('#seach').removeClass('layui-hide');
        }
        form.val('contentForm', reactChild.data);
    });
    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/content/editItem", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/content'
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/content'
    });
});