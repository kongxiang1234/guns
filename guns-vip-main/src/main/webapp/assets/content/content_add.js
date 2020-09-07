/**
 * 添加或者修改页面
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

            $("#pic").val(res.data.fileId);
            Feng.success(res.message);
        }
        , error: function () {
            Feng.error("上传图片失败！");
        }
    });


    var ue = UE.getEditor('content', {
        enableAutoSave: false,
        autoHeightEnabled: true,
        autoFloatEnabled: false,
        scaleEnabled: true,         //滚动条
        initialFrameHeight: 400     //默认的编辑区域高度
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
        var url= "/dict/listDictsByName?dictTypeName="+'专题';
        func.initDictSelect(url,"ztype","code","name");
    });
    form.on("select(type)", function (data) {
       if(data.value=="6"){
           $('#seach').removeClass('layui-hide');
       }

    });
    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {

        var ajax = new $ax(Feng.ctxPath + "/content/addItem", function (data) {
            Feng.success("添加成功！");
            window.location.href = Feng.ctxPath + '/content'
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/content'
    });

});