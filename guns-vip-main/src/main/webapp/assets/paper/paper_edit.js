/**
 * 详情对话框
 */
var PaperInfoDlg = {
    data: {
        id: "",
        sjbh: "",
        sjname: "",
        sszy: "",
        danxt: "",
        danxtValue: "",
        duoxt: "",
        duoxtValue: "",
        pdt: "",
        pdtValue: "",
        tmtotle: "",
        tmfs: "",
        btime: "",
        etime: "",
        createTime: "",
        createUser: "",
        type: ""
    }
};
var danxtg=0;
var danxtz=0;
var danxtd=0;
var dduoxtg=0;
var dduoxtz=0;
var dduoxtd=0;
var pdtg=0;
var pdtz=0;
var pdtd=0;

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects','func'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var func = layui.func;
    var formSelects = layui.formSelects;

    $(function(){
        var url= "/dict/listDictsByName?dictTypeName="+'所属';
        func.initDictSelect(url,"sszy","code","name");
        /*  var url= "/dict/listDictsByName?dictTypeName="+'试卷状态';
          func.initDictSelect(url,"type","code","name");*/


    });
    //渲染时间选择框
    var laydate = layui.laydate;
    laydate.render({
        elem: '#btime' //指定元素
        ,type: 'datetime'
    });


    form.on('select(sszy)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/management/detailManagement?specialty="+data.value, function (data) {
            var date=data.data;
            if (date==""){
                Feng.error("此专业暂时没有试题,不能生成试卷")
                window.location.href = Feng.ctxPath + '/paper';
                return false;
            }
        }, function (data) {
            Feng.error(data.responseJSON.message)
        });
        ajax.start();
        formSelects.config('danxts', {
            searchUrl: Feng.ctxPath + "/management/detailManagementQuery?specialty="+data.value+"&questionsType="+1+"&type="+1,
            keyName: 'number',
            keyVal: 'id'
        });
        formSelects.config('danxt1', {
            searchUrl: Feng.ctxPath + "/management/detailManagementQuery?specialty="+data.value+"&questionsType="+1+"&type="+2,
            keyName: 'number',
            keyVal: 'id'
        });
        formSelects.config('danxt2', {
            searchUrl: Feng.ctxPath + "/management/detailManagementQuery?specialty="+data.value+"&questionsType="+1+"&type="+3,
            keyName: 'number',
            keyVal: 'id'
        });
        formSelects.config('duoxt', {
            searchUrl: Feng.ctxPath + "/management/detailManagementQuery?specialty="+data.value+"&questionsType="+2+"&type="+1,
            keyName: 'number',
            keyVal: 'id'
        });
        formSelects.config('duoxt1', {
            searchUrl: Feng.ctxPath + "/management/detailManagementQuery?specialty="+data.value+"&questionsType="+2+"&type="+2,
            keyName: 'number',
            keyVal: 'id'
        });
        formSelects.config('duoxt2', {
            searchUrl: Feng.ctxPath + "/management/detailManagementQuery?specialty="+data.value+"&questionsType="+2+"&type="+3,
            keyName: 'number',
            keyVal: 'id'
        });
        formSelects.config('pdt', {
            searchUrl: Feng.ctxPath + "/management/detailManagementQuery?specialty="+data.value+"&questionsType="+3+"&type="+1,
            keyName: 'number',
            keyVal: 'id'
        });
        formSelects.config('pdt1', {
            searchUrl: Feng.ctxPath + "/management/detailManagementQuery?specialty="+data.value+"&questionsType="+3+"&type="+2,
            keyName: 'number',
            keyVal: 'id'
        });
        formSelects.config('pdt2', {
            searchUrl: Feng.ctxPath + "/management/detailManagementQuery?specialty="+data.value+"&questionsType="+3+"&type="+3,
            keyName: 'number',
            keyVal: 'id'
        });
    });
    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/paper'
    });
    //渲染时间选择框
    var laydate = layui.laydate;
    laydate.render({
        elem: '#etime' //指定元素
        ,type: 'datetime'
    });

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/paper/detail?id=" + $("#id").val());
    var result = ajax.start();

    form.val('paperForm',result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        if(data.field.danxtg!=""){
            var mycars = data.field.danxtg.split(',')
            for (var i = 0; i < mycars.length; i++) {
                danxtg ++
            }
            data.field.danxtgValue=danxtg;
        }else {
            data.field.danxtgValue=danxtg
        }
        if(data.field.danxtz!=""){
            var mycars = data.field.danxtz.split(',')
            for (var i = 0; i < mycars.length; i++) {
                danxtz ++
            }
            data.field.danxtzValue=danxtz;
        }else {
            data.field.danxtzValue=danxtz
        }
        if(data.field.danxtd!=""){
            var mycars = data.field.danxtd.split(',')
            for (var i = 0; i < mycars.length; i++) {
                danxtd ++
            }
            data.field.danxtdValue=danxtd;
        }else {
            data.field.danxtdValue=danxtd
        }
        if(data.field.duoxtg!=""){
            var mycars = data.field.duoxtg.split(',')
            for (var i = 0; i < mycars.length; i++) {
                dduoxtg ++
            }
            data.field.duoxtgValue=dduoxtg;
        }else {
            data.field.duoxtgValue=dduoxtg
        }
        if(data.field.duoxtz!=""){
            var mycars = data.field.duoxtz.split(',')
            for (var i = 0; i < mycars.length; i++) {
                dduoxtz ++
            }
            data.field.duoxtzValue=dduoxtz;
        }else {
            data.field.duoxtzValue=dduoxtz
        }
        if(data.field.duoxtd!=""){
            var mycars = data.field.duoxtd.split(',')
            for (var i = 0; i < mycars.length; i++) {
                dduoxtd ++
            }
            data.field.duoxtdValue=dduoxtd;
        }else {
            data.field.duoxtdValue=dduoxtd
        }


        if(data.field.pdtg!=""){
            var mycars = data.field.pdtg.split(',')
            for (var i = 0; i < mycars.length; i++) {
                pdtg ++
            }
            data.field.pdtgValue=pdtg;
        }else {
            data.field.pdtgValue=pdtg
        }
        if(data.field.pdtz!=""){
            var mycars = data.field.pdtz.split(',')
            for (var i = 0; i < mycars.length; i++) {
                pdtz ++
            }
            data.field.pdtzValue=pdtz;
        }else {
            data.field.pdtzValue=pdtz
        }
        if(data.field.pdtd!=""){
            var mycars = data.field.pdtd.split(',')
            for (var i = 0; i < mycars.length; i++) {
                pdtd ++
            }
            data.field.pdtdValue=pdtd;
        }else {
            data.field.pdtdValue=pdtd
        }
        var ajax = new $ax(Feng.ctxPath + "/paper/editItem", function (data) {
            danxtg=0;
            danxtz=0;
            danxtd=0;
            dduoxtg=0;
            dduoxtz=0;
            dduoxtd=0;
            pdtg=0;
            pdtz=0;
            pdtd=0;
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/paper';
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    var sszy=result.data.sszy;
    formSelects.config('danxts', {
        searchUrl: Feng.ctxPath + "/paper/listdanxtg?specialty="+sszy+"&questionsType="+1+"&type="+1+"&selectValue="+result.data.danxtg,
        keyName: 'number',
        keyVal: 'id',
    });
    formSelects.config('danxt1', {
        searchUrl: Feng.ctxPath + "/paper/listdanxtg?specialty="+sszy+"&questionsType="+1+"&type="+2+"&selectValue="+result.data.danxtz,
        keyName: 'number',
        keyVal: 'id'
    });
    formSelects.config('danxt2', {
        searchUrl: Feng.ctxPath + "/paper/listdanxtg?specialty="+sszy+"&questionsType="+1+"&type="+3+"&selectValue="+result.data.danxtd,
        keyName: 'number',
        keyVal: 'id'
    });
    formSelects.config('duoxt', {
        searchUrl: Feng.ctxPath + "/paper/listdanxtg?specialty="+sszy+"&questionsType="+2+"&type="+1+"&selectValue="+result.data.duoxtg,
        keyName: 'number',
        keyVal: 'id'
    });
    formSelects.config('duoxt1', {
        searchUrl: Feng.ctxPath + "/paper/listdanxtg?specialty="+sszy+"&questionsType="+2+"&type="+2+"&selectValue="+result.data.duoxtz,
        keyName: 'number',
        keyVal: 'id'
    });
    formSelects.config('duoxt2', {
        searchUrl: Feng.ctxPath + "/paper/listdanxtg?specialty="+sszy+"&questionsType="+2+"&type="+3+"&selectValue="+result.data.duoxtd,
        keyName: 'number',
        keyVal: 'id'
    });
    formSelects.config('pdt', {
        searchUrl: Feng.ctxPath + "/paper/listdanxtg?specialty="+sszy+"&questionsType="+3+"&type="+1+"&selectValue="+result.data.pdtg,
        keyName: 'number',
        keyVal: 'id'
    });
    formSelects.config('pdt1', {
        searchUrl: Feng.ctxPath + "/paper/listdanxtg?specialty="+sszy+"&questionsType="+3+"&type="+2+"&selectValue="+result.data.pdtz,
        keyName: 'number',
        keyVal: 'id'
    });
    formSelects.config('pdt2', {
        searchUrl: Feng.ctxPath + "/paper/listdanxtg?specialty="+sszy+"&questionsType="+3+"&type="+3+"&selectValue="+result.data.pdtd,
        keyName: 'number',
        keyVal: 'id'
    });

});