/**
 * 添加或者修改页面
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
        $('#sszy').val("1");
        form.render('select');
        var url= "/dict/listDictsByName?dictTypeName="+'试卷状态';
        func.initDictSelect(url,"type","code","name");
        var ajax = new $ax(Feng.ctxPath + "/management/detailManagement?specialty=1", function (data) {
            var date=data.data;
            if (date==""){
                Feng.error("此专业暂时没有试题,不能生成试卷")
                window.location.href = Feng.ctxPath + '/paper';
                return false;
            }

            danxtg=date.danxtg;
            danxtz=date.danxtz;
            danxtd=date.danxtd;
            dduoxtg=date.dduoxtg;
            dduoxtz=date.dduoxtz;
            dduoxtd=date.dduoxtd;
            pdtg=date.pdtg;
            pdtz=date.pdtz;
            pdtd=date.pdtd;
        }, function (data) {
            Feng.error(data.responseJSON.message)
        });
        ajax.start();
    });
    //渲染时间选择框
    var laydate = layui.laydate;
    laydate.render({
        elem: '#btime' //指定元素
        ,type: 'datetime'
    });
    //渲染时间选择框
    var laydate = layui.laydate;
    laydate.render({
        elem: '#etime' //指定元素
        ,type: 'datetime'
    });

    form.on("select(sszy)", function (data) {
debugger
        var ajax = new $ax(Feng.ctxPath + "/management/detailManagement?specialty="+data.value, function (data) {
            var date=data.data;
            if (date==""){
                Feng.error("此专业暂时没有试题,不能生成试卷")
                window.location.href = Feng.ctxPath + '/paper';
                return false;
            }

            danxtg=date.danxtg;
            danxtz=date.danxtz;
            danxtd=date.danxtd;
            dduoxtg=date.dduoxtg;
            dduoxtz=date.dduoxtz;
            dduoxtd=date.dduoxtd;
            pdtg=date.pdtg;
            pdtz=date.pdtz;
            pdtd=date.pdtd;
        }, function (data) {
            Feng.error(data.responseJSON.message)
        });
        ajax.start();

    });
 // 校验
     $('#danxt').change(function () {

         var danxtV=$("#danxt").val();
         if (danxtV>danxtg){
             Feng.error("单选难度高已超出设置的试题"+danxtg)
         }
     });
     // 校验
     $('#danxt1').change(function () {
         var danxtV=$("#danxt1").val();
         if (danxtV>danxtz){
             Feng.error("单选难度中已超出设置的试题"+danxtz)
         }
     });
     // 校验
     $('#danxt2').change(function () {
         var danxtV=$("#danxt2").val();
         if (danxtV>danxtd){
             Feng.error("单选难度低已超出设置的试题"+danxtd)
         }
     });
     // 校验
     $('#duoxt').change(function () {
         var duoxtV=$("#duoxt").val();
         if (duoxtV>dduoxtg){
             Feng.error("多选难度高已超出设置的试题"+dduoxtg)
         }
     });
     // 校验
     $('#duoxt1').change(function () {
         var danxtV=$("#duoxt1").val();
         if (danxtV>dduoxtz){
             Feng.error("多选难度中已超出设置的试题"+dduoxtz)
         }
     });
     // 校验
     $('#duoxt2').change(function () {
         var danxtV=$("#duoxt2").val();
         if (danxtV>dduoxtd){
             Feng.error("多选难度低已超出设置的试题"+dduoxtd)
         }
     });
     // 校验
     $('#pdtd').change(function () {
         var danxtV=$("#pdtd").val();
         if (danxtV>pdtg){
             Feng.error("判断难度高已超出设置的试题"+pdtg)
         }
     });
     // 校验
     $('#pdtd1').change(function () {
         var danxtV=$("#pdtd1").val();
         if (danxtV>pdtz){
             Feng.error("判断难度中已超出设置的试题"+pdtz)
         }
     });

     // 校验
     $('#pdtd2').change(function () {
         var danxtV=$("#pdtd2").val();
         if (danxtV>pdtd){
             Feng.error("判断难度低已超出设置的试题"+pdtd)
         }
     });



    form.verify({
        num: [ /^[0-9]\d*$/, '只能是整数哦'],
    });


    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        debugger
        var danxtgValue=data.field.danxtg
        if (danxtgValue>danxtg){
            Feng.error("单选难度高已超出设置的试题"+danxtg)
            return false;
        }
        var danxtzValue=data.field.danxtz
        if (danxtzValue>danxtz){
            Feng.error("单选难度中已超出设置的试题"+danxtz)
            return false;
        }
        var danxtdValue=data.field.danxtd
        if (danxtdValue>danxtd){
            Feng.error("单选难度低已超出设置的试题"+danxtd)
            return false;
        }
        var duoxtgValue=data.field.duoxtg
        if (duoxtgValue>dduoxtg){
            Feng.error("多选难度高已超出设置的试题"+dduoxtg)
            return false;
        }
        var duoxtzValue=data.field.duoxtz
        if (duoxtzValue>dduoxtz){
            Feng.error("多选难度中已超出设置的试题"+dduoxtz)
            return false;
        }
        var duoxtdValue=data.field.duoxtd
        if (duoxtdValue>dduoxtd){
            Feng.error("多选难度低已超出设置的试题"+dduoxtd)
            return false;
        }
        var pdtgValue=data.field.pdtg
        if (pdtgValue>pdtg){
            Feng.error("判断难度高已超出设置的试题"+pdtg)
            return false;
        }
        var pdtzValue=data.field.pdtz
        if (pdtzValue>pdtz){
            Feng.error("判断难度中已超出设置的试题"+pdtz)
            return false;
        }
        var pdtdValue=data.field.pdtd
        if (pdtdValue>pdtd){
            Feng.error("判断难度低已超出设置的试题"+pdtd)
            return false;
        }
      /*  if ((parseInt(danxtgValue)+parseInt(danxtzValue)+parseInt(danxtdValue)+parseInt(duoxtgValue)+parseInt(duoxtzValue)+parseInt(duoxtdValue)+parseInt(pdtgValue)+parseInt(pdtzValue)+parseInt(pdtdValue))<100){
            Feng.error("请输入大于100的试卷总数")
            return false;
        }*/
        var ajax = new $ax(Feng.ctxPath + "/paper/addItem", function (data) {
            danxtg=0;
            danxtz=0;
            danxtd=0;
            dduoxtg=0;
            dduoxtz=0;
            dduoxtd=0;
            pdtg=0;
            pdtz=0;
            pdtd=0;
            Feng.success("添加成功！");
            window.location.href = Feng.ctxPath + '/paper';
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });


});