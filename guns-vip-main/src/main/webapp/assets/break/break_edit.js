/**
 * 详情对话框
 */
var BreakInfoDlg = {
    data: {
        id: "",
        dcTime: "",
        dcType: "",
        wzTitle: "",
        pic: "",
        xmUnit: "",
        wzUnit: "",
        wzPeople: "",
        fzPeople: "",
        wzType: "",
        wzStatus: "",
        num: "",
        content: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects','func'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var upload = layui.upload;
    var func = layui.func;

    //渲染时间选择框
    var laydate = layui.laydate;
    laydate.render({
        elem: '#dcTime' //指定元素
    });





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
   /* var ue = UE.getEditor('content', {
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
    };*/





    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/break/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    var url = "/rest/system/preview/"+result.data.pic+"";
    $('#img1').attr('src', url);
    form.val('breakForm', result.data);
    $(function(){
        var reactChild=result;
        var url= "/mgr/userList";
        $("#wzPeople").html('<option value="">请选择</option>');
        var ajax = new $ax(Feng.ctxPath + url, function (data) {
            for (var i = 0; i < data.length; i++) {
                var optionValue = data[i].name;
                var optionName = data[i].name;
                $("#wzPeople").append('<option value="' + optionValue + '">' + optionName + '</option>');
            }
            form.render();
        }, function (data) {
        });
        ajax.start();


        var url= "/mgr/departList";
        $("#xmUnit").html('<option value="">请选择</option>');
        var ajax = new $ax(Feng.ctxPath + url, function (data) {
            for (var i = 0; i < data.length; i++) {
                var optionValue = data[i].simpleName;
                var optionName = data[i].simpleName;
                $("#xmUnit").append('<option value="' + optionValue + '">' + optionName + '</option>');
            }
            form.render();
        }, function (data) {
        });
        ajax.start();
        var url= "/dict/listDictsByName?dictTypeName="+'督查主体';
        func.initDictSelect(url,"dcNoumenon","code","name");
        var url= "/dict/listDictsByName?dictTypeName="+'违章级别';
        func.initDictSelect(url,"wzStatus","code","name");
        var url= "/dict/listDictsByName?dictTypeName="+'违章一级分类';
        func.initDictSelect(url,"oneSecondary","code","name");
        var url= "/dict/listDictsByName?dictTypeName="+'违章二级分类';
        func.initDictSelect(url,"secondary","code","name");
        var url= "/dict/listDictsByName?dictTypeName="+'现场类别';
        func.initDictSelect(url,"siteCategory","code","name");
        var url= "/dict/listDictsByName?dictTypeName="+'督察形式';
        func.initDictSelect(url,"supervision","code","name");
        var url= "/dict/listDictsByName?dictTypeName="+'人员性质';
        func.initDictSelect(url,"nature","code","name");
        form.val('breakForm', reactChild.data);

    });
    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/break/editItem", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/break'
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/break'
    });
});