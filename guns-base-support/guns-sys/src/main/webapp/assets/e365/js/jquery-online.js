(function ($) {
    $.fn.onLine = function (options) {
        var $this = $(this);
        var defaults = { // 定义默认变量
            isTime: true, //是否需要定时，默认为true
            isHideRes: false, // 是否需要解析模块
            box: '#online_wrapper', //试题容器
            answerBox: '.answer-list', //答题卡容器
            prevBtn: '#prev_btn', //上一题按钮
            nextBtn: '#next_btn', //下一题按钮
            current: '#current_num', //当前题序号
            cardBox: '.answer-list-card', //答题卡列表
            cardPopup: '.popup-card-container', //答题卡弹窗
            popupOverlay: '.popup-overlay', //答题卡弹窗背景
            CutTime: '.cut-time', //倒计时
            submitBtn: '#submit_answer', //提交按钮
            time: '#online_wrappers', //倒计时：分钟
            scoreDan: '#online_Danscore', //单选题
            scoreDuo: '#online_Duoscore', //多选题
            scorePan: '#online_Panscore', //判选题
            slideW: Math.floor($('body').width()), //容器宽度
            sureFn: function () { }, //提交答案回调函数
            examEnd: function () { } //考试结束回调函数
        }
        var options = $.extend(defaults, options); //合并参数
        var methods = {
            opts: {
                time: parseInt($(options.time).val()),
                timer: null,
                num: $(options.box).children().length,
                slideDis: 0, //左右滑动的基础像素
                startx: 0, //X轴滑动开始像素
            },
            init: function () { //初始化
                this.touchMove();
                this.prev();
                this.next();
                this.checkAnswer();
                this.chooseCardNum();
                if (options.isTime) {
                    this.timer();
                }
            },
            touchs: function (event) {
                var that = methods;
                if (event.type == "touchstart") {
                    var touch = event.originalEvent.targetTouches[0];
                    that.opts.startx = Math.floor(touch.pageX);
                    that.opts.starty = Math.floor(touch.pageY);
                    event.stopPropagation();
                } else if (event.type == "touchmove") {
                    var touch = event.originalEvent.targetTouches[0];
                    var movex = Math.floor(touch.pageX);
                    var movey = Math.floor(touch.pageY);
                    var slidex = movex - that.opts.startx;
                    var slidey = movey - that.opts.starty;

                    if (Math.abs(slidex) > Math.abs(slidey)) {
                        var moveDis = that.opts.slideDis + slidex;
                        that.trans(moveDis);
                        return false;
                    }
                } else if (event.type == "touchend" || event.type == "touchcancel") {

                    var endx = Math.floor(event.originalEvent.changedTouches[0].pageX);
                    var endy = Math.floor(event.originalEvent.changedTouches[0].pageY);

                    var nx = endx - that.opts.startx;
                    var ny = endy - that.opts.starty;

                    var angle = Math.atan2(ny, nx) * 180 / Math.PI; //计算角度

                    if (Math.abs(nx) <= 1 || Math.abs(ny) <= 1) { //当只触摸 不滑动时 页面不动
                        that.trans(that.opts.slideDis);
                        return;
                    }

                    if (angle < 45 && angle >= -45) { //角度在此区间容器右滑
                        that.right();
                        that.trans(that.opts.slideDis);
                        that.currentNum(that.opts.slideDis);
                        that.hideResolution();
                        return false;
                    } else if ((angle <= 180 && angle >= 135) || (angle >= -180 && angle < -135)) { //角度在此区间容器右滑
                        that.left();
                        that.trans(that.opts.slideDis);
                        that.currentNum(that.opts.slideDis);
                        that.hideResolution();
                        return false;
                    } else {
                        that.trans(that.opts.slideDis)
                    }
                }
                return;
            },
            forbid: function (e) {
                e.preventDefault && e.preventDefault();
                e.returnValue = false;
                e.stopPropagation && e.stopPropagation();
                return false;
            },
            touchMove: function () {
                $this.off("touchstart").on('touchstart', this.touchs);
                $this.off("touchmove").on('touchmove', this.touchs);
                $this.off("touchend").on('touchend', this.touchs);


                //禁止头部和底部滑动事件
                $(".test-bar").off("touchmove").on('touchmove', this.forbid);
                $(".header-nav").off("touchmove").on('touchmove', this.forbid);
                $(".test-head-tips").off("touchmove").on('touchmove', this.forbid);

            },

            hideResolution: function () { //答案查看解析的时候
                if (options.isHideRes) {
                    $this.find('.resolution-content').hide();
                }
            },
            prev: function () { //上一题
                var that = this;
                $(document).on('click', options.prevBtn, function () {
                    that.right();
                    that.trans(that.opts.slideDis);
                    that.currentNum(that.opts.slideDis);
                    that.hideResolution();
                })
            },

            next: function () { //下一题
                var that = this;
                $(document).on('click', options.nextBtn, function () {
                    that.left();
                    that.trans(that.opts.slideDis);
                    that.currentNum(that.opts.slideDis);
                    that.hideResolution();
                })
            },

            trans: function (s) { //容器滑动
                $(options.box).css({
                    'transform': 'translate3d(' + s + 'px, 0px, 0px)',
                });
            },

            currentNum: function () { // 当前题目
                var that = this;
                var cur = that.opts.slideDis / options.slideW;
                $(options.current).text(Math.abs(cur) + 1);
            },

            left: function () { //容器左滑动
                var that = this;
                if (that.opts.slideDis == (-(that.opts.num - 1) * options.slideW)) {
                    that.opts.slideDis = -(that.opts.num - 1) * options.slideW;
                } else {
                    that.opts.slideDis -= options.slideW;
                }

            },

            right: function () { //容器右滑动
                var that = this;

                if (that.opts.slideDis > -1 && that.opts.slideDis <= 0) {
                    that.opts.slideDis = 0;
                } else {
                    that.opts.slideDis += options.slideW;
                }
            },

            format: function (t) { //时间格式化
                var h, m, s;
                h = Math.floor(t / 3600);
                m = Math.floor(t / 60 % 60);
                s = Math.floor(t % 60);
                h < 10 ? h = '0' + h : h = h;
                m < 10 ? m = '0' + m : m = m;
                s < 10 ? s = '0' + s : s = s;
                return h + ':' + m + ':' + s;
            },

            timer: function () { //定时器
                var that = this;
                that.opts.timer = setInterval(function () {
                    that.opts.time -= 1;
                    if (that.opts.time == -1) {
                        clearInterval(that.opts.timer);
                        that.submitAnswer1();
                        return false;
                    } else {
                        var t = that.format(that.opts.time);
                        $(options.CutTime).text(t);
                    }
                }, 1000);

                that.submitAnswer();
            },
            submitAnswer1: function () { //提交答案
                $.alert('考试时间已结束，请提交试卷', function () {
                    var danV=$(options.scoreDan).val();
                    var duoV=$(options.scoreDuo).val();
                    var panV=$(options.scorePan).val();
                    var subjectNum = $(options.cardBox).children();
                    var subjectNum1 = $(options.box).children();
                    var total=0;
                    var notDoneNum="";
                    for (let i = 0; i <subjectNum1.length ; i++) {
                        var arrays= $(subjectNum[i]).html();//正确的答案
                        var flag=  $(subjectNum1[i])[0].innerText;//判断单选还是多选
                        if(flag.indexOf('单选') > -1 || flag.indexOf('判断') > -1){//单选
                            var array=  $(subjectNum1[i]).find('.active').find('span').html();//选的答案
                            if(array!=undefined){
                                if (array==arrays){
                                    if (array=="1" || array=="2"){ //判断题
                                        total=total+parseInt(panV);
                                    }else {
                                        total=total+parseInt(danV);
                                    }
                                }else {
                                    var fieldFlag=document.getElementById(i+1)
                                    fieldFlag.innerHTML='<i style="width: 12px;height: 15px;display: inline-block"></i>正确的是:（    &nbsp;  &nbsp; '+arrays+' &nbsp; &nbsp; ）'

                                }
                            }else {
                                notDoneNum+=(i+1)+",";
                            }
                        }else {
                            var array=  $(subjectNum1[i]).find('.active').find('span').html();//选的答案
                            if(array!=undefined){
                                arrays = arrays.replace(/,/g, "");//取消字符串中出现的所有逗号
                                var duo="";
                                for (let j = 0; j <$(subjectNum1[i]).find('.active').length ; j++) {
                                    var search=$(subjectNum1[i]).find('.active')[j].innerText.split('.')[0];
                                    duo+=search;
                                }
                                if(Array.from(new Set(duo)).toString().replace(/,/g, "")==arrays){
                                    total=total+parseInt(duoV);
                                }else {
                                    var fieldFlag=document.getElementById(i+1)
                                    fieldFlag.innerHTML='<i style="width: 12px;height: 15px;display: inline-block"></i>正确的是:（    &nbsp;  &nbsp; '+arrays+' &nbsp; &nbsp; ）'

                                }
                            }else {
                                notDoneNum+=(i+1)+",";
                            }
                        }
                    }
                    if (total<0){
                        total=0;
                    }
                    options.sureFn(total);
                });
            },
            checkAnswer: function () { //选择答案，当type为2（多选）时，需要手动切换下一题
                var that = this;
                $(options.answerBox).on('click', 'span', function () {
                    var self = $(this);
                    var li = self.parents('li');
                    var type = li.data('type');
                    var subjectNum = li.data('id') * 1;
                    $(options.cardBox).children().eq(subjectNum).addClass('active');
                    if (type != 2) {
                        self.addClass('active').siblings().removeClass('active');
                        self.siblings().children("i").removeClass("fa-dot-circle-o").addClass("fa-circle-o");
                        self.children("i").removeClass("fa-circle-o").addClass("fa-dot-circle-o");
                        that.left();
                        that.trans(that.opts.slideDis)
                        that.currentNum(that.opts.slideDis);
                    }
                    else {
                        if (self.children("i").hasClass("fa-square-o")) {
                            self.children("i").removeClass("fa-square-o").addClass("fa-check-square-o");
                            self.children("span").addClass("active");
                        }
                        else {
                            self.children("i").removeClass("fa-check-square-o").addClass("fa-square-o");
                            self.children("span").removeClass("active");
                        }

                        self.toggleClass('active');
                    }
                })
            },
            chooseCardNum: function () { //选中答题卡序号跳到当前题目
                var that = this;
                $(options.cardBox).on('click', 'span', function () {
                    var self = $(this);
                    var subjectNum = self.data('id');
                    var curSubjectPos = -((subjectNum * 1) * options.slideW);
                    var cur = curSubjectPos / options.slideW;
                    $(options.current).text(Math.abs(cur) + 1);
                    that.trans(curSubjectPos);
                    that.opts.slideDis = curSubjectPos;
                    $(options.cardPopup).removeClass('modal-in');
                    $(options.popupOverlay).removeClass('modal-overlay-visible');
                    that.hideResolution();
                })
            },
            submitAnswer: function () { //提交答案
                var that = this;
                $(document).on('click', options.submitBtn, function () {
                    var danV=$(options.scoreDan).val();
                    var duoV=$(options.scoreDuo).val();
                    var panV=$(options.scorePan).val();
                    var subjectNum = $(options.cardBox).children();
                    var subjectNum1 = $(options.box).children();
                    var total=0;
                    var notDoneNum="";
                    for (let i = 0; i <subjectNum1.length ; i++) {
                        var arrays= $(subjectNum[i]).html();//正确的答案
                        var flag=  $(subjectNum1[i])[0].innerText;//判断单选还是多选
                        if(flag.indexOf('单选') > -1 || flag.indexOf('判断') > -1){//单选
                            var array=  $(subjectNum1[i]).find('.active').find('span').html();//选的答案
                            if(array!=undefined){
                                if (array==arrays){
                                    if (array=="1" || array=="2"){ //判断题
                                        total=total+parseInt(panV);
                                    }else {
                                        total=total+parseInt(danV);
                                    }
                                }else {
                                    var fieldFlag=document.getElementById(i+1)
                                    fieldFlag.innerHTML='<i style="width: 12px;height: 15px;display: inline-block"></i>正确的是:（    &nbsp;  &nbsp; '+arrays+' &nbsp; &nbsp; ）'

                                }
                            }else {
                                notDoneNum+=(i+1)+",";
                            }
                        }else {
                            var array=  $(subjectNum1[i]).find('.active').find('span').html();//选的答案
                            if(array!=undefined){
                                arrays = arrays.replace(/,/g, "");//取消字符串中出现的所有逗号
                                var duo="";
                                for (let j = 0; j <$(subjectNum1[i]).find('.active').length ; j++) {
                                    var search=$(subjectNum1[i]).find('.active')[j].innerText.split('.')[0];
                                    duo+=search;
                                }
                                if(Array.from(new Set(duo)).toString().replace(/,/g, "")==arrays){
                                    total=total+parseInt(duoV);
                                }else {
                                    var fieldFlag=document.getElementById(i+1)
                                    fieldFlag.innerHTML='<i style="width: 12px;height: 15px;display: inline-block"></i>正确的是:（    &nbsp;  &nbsp; '+arrays+' &nbsp; &nbsp; ）'

                                }
                            }else {
                                notDoneNum+=(i+1)+",";
                            }
                        }
                    }
                   /* var notDoneNum = 0; //未做的题数
                    subjectNum.each(function () {
                        if (!$(this).hasClass('active')) {
                            notDoneNum += 1;
                        }
                    })*/
                    if (total<0){
                        total=0;
                    }
                    document.body.style.overflow = 'hidden'
                    if (notDoneNum!='') {
                        $(".online-wrapper").css("overflow", "hidden");
                        $.confirm('您还有' + notDoneNum + '道题未做，确定要提交试卷吗？',
                            function () {
                                clearInterval(that.opts.timer);
                                options.sureFn(total);

                                $(".online-wrapper").css("overflow", "visible");
                            }, function () {
                                $(".online-wrapper").css("overflow", "visible");
                            }
                        );
                    } else {
                        $.confirm('您确定要提交试卷吗?',
                            function () {
                                clearInterval(that.opts.timer);
                                options.sureFn(total);
                            },
                        );
                    }
                    document.body.style.overflow = 'scroll'
                })
            },

        };
        methods.init();
    }
})(jQuery)