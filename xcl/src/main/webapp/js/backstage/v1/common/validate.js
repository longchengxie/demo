/**
 * File:         validate.js
 * Create:       2015-11-26
 * Last Update : 2016-04-21
 * Author:       江圣
 * Description:  表单验证插件
 */

(function(global, $, backstage) {

        //默认值
        var defaults = {
            triggerEvent: "input change keyup",
            //表单触发哪些事件后再次验证
            showError: true,
            //是否显示错误信息
            ERROR: "错误",
            REQUIRED: "该字段不能为空",
            //非空报错
            REGULAR: "格式不正确",
            //正则报错
            MAXLENGTH: "不得超过最大长度",
            //长度报错
            MINLENGTH: "不得低于最小长度",
            //长度报错
            MAX: "不能大于指定值",
            //值报错
            MIN: "不能小于指定值",
            //值报错
            TELEPHONE: "必须填写固定电话",
            //电话报错
            FAX: "必须填写传真",
            //传真报错
            MOBILE: "必须填写手机号",
            //手机报错
            NUMBER: "必须填写数字",
            //数字报错
            ORDINARY: "不得填写特殊符号",
            //特殊符号报错
            DIGITS: "必须填写整数",
            //整数报错
            POSITIVE: "必须填写正数",
            //负数报错
            $ERROR: $('<i class="error"><span class="icon icon-danger"></span><span class="error-text"></span></i>'),
            //报错HTML
            groupQuery: ".form-group",
            //区域查询
            changeCallback: null ,
            //修改值回调
            errorCallback: null //出错回调
        };

        //设置
        var settings = {};

        /**
         * 单个控件
         * @param $ele 元素
         */
        function Item($ele) {
            //控件元素
            this.$ele = $ele;
            this.val = null ;
            this.$group = $ele.parents(settings.groupQuery).eq(0);
            this.showError = false;

            //验证规则
            this.rule = null ;

            //控件是否验证通过
            this.isValidate = false;

            //默认错误提示文本
            this.errorText = settings.ERROR;

            //观察者
            this.observer = null ;

            //初始化
            this.init();
        }

        Item.prototype = {
            constructor: Item,

            //初始化
            init: function() {
                this.rule = this.participle();
                this.$ele.data("validate-item", this);
            },

            //增加观察者
            attach: function(observer) {
                this.observer = observer;
            },

            /**
             * 规则字符串转化为规则对象
             */
            participle: function() {

                //规则对象
                var rule = {};

                //多条规则数组
                var multipleRuleArr;

                //一条规则数组
                var keyValueArr;

                //规则字符串
                var ruleStr = this.$ele.attr("data-validate");

                //去除空格
                ruleStr = ruleStr.replace(/[\s]/g, "");

                ruleStr = ruleStr.substr(1, ruleStr.length - 2);
                multipleRuleArr = ruleStr.split(',');

                //迭代生成规则
                for (var i = 0; i < multipleRuleArr.length; i++) {
                    keyValueArr = multipleRuleArr[i].split(':');

                    switch (keyValueArr[1]) {
                        case "true":
                            keyValueArr[1] = true;
                            break;
                        case "false":
                            keyValueArr[1] = false;
                            break;
                    }
                    keyValueArr[0] = keyValueArr[0].toLowerCase();
                    rule[keyValueArr[0]] = keyValueArr[1];
                }

                return rule;

            },

            /**
             * 获取单选按钮值
             * @returns {string|*} 单选按钮的值
             */
            getRadioVal: function() {
                var name = this.$ele.attr("name");
                var $radioChecked = this.$group.find(':radio:checked');
                return $.trim($radioChecked.val());
            },

            /**
             * 获取复选框值
             * @returns {string|*} 复选框的值
             */
            getCheckboxVal: function() {
                var ret = "";
                var $checkboxChecked = this.$group.find(':checkbox:checked');
                for (var i = 0; i < $checkboxChecked.length; i++) {
                    ret += $.trim($checkboxChecked.val()) + ",";
                }
                return ret;
            },

            //验证但不渲染
            start: function() {

                switch (this.$ele.attr("type")) {
                    //单选按钮
                    case "radio":
                        this.val = this.getRadioVal();
                        break;
                    //复选框
                    case "checkbox":
                        this.val = this.getCheckboxVal();
                        break;
                    default:
                        //输入框 密码框 隐藏域 文本框 等
                        this.val = $.trim(this.$ele.val());
                        break;
                }

                var isValidate = true;

                //禁用 只读 不用验证
                if (this.$ele.is("[disabled]")) {
                    if (this.$ele.is("[data-validate-disabled='true']")) {
                        //Do nothing
                    }
                    else {
                        this.isValidate = isValidate;
                        return false;
                    }
                }
                if (this.$ele.is("[readonly]")) {
                    if (this.$ele.is("[data-validate-readonly='true']")) {
                        //Do nothing
                    }
                    else {
                        this.isValidate = isValidate;
                        return false;
                    }
                }

                //普通验证数组
                var validateMethodArray = {
                    "required": true,
                    "regular": true,
                    "maxlength": true,
                    "minlength": true,
                    "max": true,
                    "min": true,
                    "telephone": true,
                    "mobile": true,
                    "fax": true,
                    "number": true,
                    "ordinary": true,
                    "digits": true,
                    "positive": true
                };

                //进行普通验证
                for (var rule in this.rule) {
                    if (validateMethodArray[rule] && this[rule] && this[rule]() === false) {
                        isValidate = false;
                    }
                }

                //用户扩展验证
                var expands = settings.expands;
                if (expands && expands.length > 0) {

                    for (var expandsIndex = 0; expandsIndex < expands.length; expandsIndex++) {

                        var expand = expands[expandsIndex];

                        var name = expand.name;
                        var method = expand.method;

                        //如果html的data-validate属性中含有添加的扩展规则
                        if (this.rule[name]) {
                            funcRet = method.call(this, this.val, this.$ele);

                            //如果验证失败
                            if (funcRet === false) {
                                this.setError(this.ERROR, this.rule[name]);
                                isValidate = false;
                                break;
                            }
                        }

                    }

                }

                this.isValidate = isValidate;

            },

            //验证并渲染
            test: function() {
                this.start();
                this.render();
            },

            //观察控件 发生改变则立即开始验证
            watch: function() {
                this.bindEvent();
            },

            ignore: function() {
                this.unbindEvent();
            },

            //渲染
            render: function() {

                var $error = this.$group.find("i.error");
                var $newError = null ;

                if (this.showError) {
                    $error.remove();
                    if (this.isValidate == false) {
                        $newError = settings.$ERROR.clone();
                        $newError.find(".error-text").html(this.errorText);
                        this.$group.append($newError);
                        this.$ele.addClass("error");

                        if (settings.errorCallback && $.isFunction(settings.errorCallback)) {
                            settings.errorCallback.apply(this)
                        }

                    } else {
                        this.$ele.removeClass("error");
                    }
                }

            },

            //绑定事件
            bindEvent: function() {
                var self = this;
                this.unbindEvent();
                this.$ele.on(settings.triggerEvent, {
                    self: self
                }, this.changeHandle);
            },

            //解除绑定事件
            unbindEvent: function() {
                this.$ele.off(settings.triggerEvent, this.changeHandle);
            },

            //修改句柄
            changeHandle: function(event) {

                var self = event.data.self;
                if (self.$ele.is(":radio")) {

                    var $checkbox = self.$group.find("[data-validate]:radio");
                    for (var i = 0; i < $checkbox.length; i++) {
                        var that = $checkbox.eq(i).data("validate-item");
                        self.change(that);
                    }

                } else {
                    self.change(self)
                }

            },

            //修改
            change: function(self) {
                self.test();
                self.notify();
                if (settings.changeCallback && $.isFunction(settings.changeCallback)) {
                    settings.changeCallback.apply(this)
                }
            },

            //向观察者（们）发出通知
            notify: function() {
                this.observer.update();
            },

            /**
             * 设置错误文本
             * @param defaultErrorText 默认错误文本
             * @param userErrorText 用户设置错误文本
             */
            setError: function(defaultErrorText, userErrorText) {
                this.errorText = defaultErrorText;
                if (typeof userErrorText == "string") {
                    this.errorText = userErrorText;
                }
            },

            /**
             * 必填验证
             * @returns {boolean} 验证失败返回 false
             */
            required: function() {
                //输入框 密码框 隐藏域 文本框 等
                if (this.val !== "") {
                    return true;
                } else {
                    this.setError(settings.REQUIRED, this.rule.required);
                    return false;
                }
            },

            /**
             * 正则验证
             */
            regular: function() {
                if (this.val == "") {
                    return true;
                }

                var regStr = this.$ele.data("validate-regular");
                var regAttr = this.$ele.data("validate-regular-attributes");

                var reg;
                if (regAttr) {
                    reg = new RegExp(regStr,regAttr);
                } else {
                    reg = new RegExp(regStr);
                }

                if (reg.test(this.val)) {
                    return true;
                } else {
                    this.setError(settings.REGULAR, this.rule.regular);
                    return false;
                }
            },

            //字符数量验证
            maxlength: function() {
                if (this.val == "") {
                    return true;
                }

                var maxlength = parseInt(this.$ele.data("validate-maxlength"));

                if (this.val.length <= maxlength) {
                    return true;
                } else {
                    this.setError(settings.MAXLENGTH, this.rule.maxlength);
                    return false;
                }
            },

            //字符数量验证
            minlength: function() {
                if (this.val == "") {
                    return true;
                }

                var minlength = parseInt(this.$ele.data("validate-minlength"));

                if (this.val.length >= minlength) {
                    return true;
                } else {
                    this.setError(settings.MINLENGTH, this.rule.minlength);
                    return false;
                }
            },

            //值验证
            max: function() {
                if (this.val == "") {
                    return true;
                }

                var val = parseFloat(this.val);
                var max = parseFloat(this.$ele.data("validate-max"));

                if (val <= max) {
                    return true;
                } else {
                    this.setError(settings.MAX, this.rule.max);
                    return false;
                }
            },

            //值验证
            min: function() {
                if (this.val == "") {
                    return true;
                }
                var val = parseFloat(this.val);
                var min = parseFloat(this.$ele.data("validate-min"));

                if (val >= min) {
                    return true;
                } else {
                    this.setError(settings.MIN, this.rule.min);
                    return false;
                }
            },

            /**
             * 内部正则验证扩展
             * @param regular 正则
             * @param errorTitle 错误标题
             * @param name 验证名称
             * @param inverse 反转验证结果
             * @returns {boolean}
             */
            regularTool: function(regular, errorTitle, name, inverse) {
                if (this.val == "") {
                    return true;
                }
                var isValidate = regular.test(this.val);
                inverse ? isValidate = !isValidate : {};
                if (isValidate) {
                    return true;
                } else {
                    this.setError(settings[errorTitle], this.rule[name]);
                    return false;
                }
            },

            //电话
            telephone: function() {
                var regular = /^\d{3,4}-?\d{7,9}$/;
                return this.regularTool(regular, "TELEPHONE", "telephone");
            },

            //手机
            mobile: function() {
                var regular = /^\d{11}$/;
                return this.regularTool(regular, "MOBILE", "mobile");
            },

            //传真
            fax: function() {
                var regular = /^\d{3,4}-?\d{7,9}$/;
                return this.regularTool(regular, "FAX", "fax");
            },

            //数字
            number: function() {
                var regular = /^(-)?[1-9]{0}\d*(\.\d{1,2})?$/;
                return this.regularTool(regular, "NUMBER", "number");
            },

            //特殊字符
            ordinary: function() {
                var regular = /^([\u4e00-\u9fa5]|[a-zA-Z0-9])+$/;
                return this.regularTool(regular, "ORDINARY", "ordinary");
            },

            //整数
            digits: function() {
                var regular = /^(-)?[1-9]\d*$|^0$/;
                return this.regularTool(regular, "DIGITS", "digits");
            },

            //正数
            positive: function() {
                var regular = /^-/;
                return this.regularTool(regular, "POSITIVE", "positive", true);
            }

        };

        /**
         * 验证类
         * @param options
         * @constructor
         */
        function Validate(options) {
            this.settings = $.extend({}, defaults, options);
            this._defaults = defaults;

            settings = this.settings;

            //验证区域
            this.$area = null ;

            //提交按钮
            this.$submit = null ;

            //需要验证的表单列表
            this.validates = [];

            //是否验证成功
            this.isValidate = false;

            this.init();

        }

        Validate.prototype = {

            constructor: Validate,

            //初始化
            init: function() {
                this.$area = this.settings.$area;
                if (this.settings.$submit) {
                    this.$submit = this.settings.$submit;
                }
                this.settings.expands = [];
                var $validates = this.$area.find('[data-validate]');
                var item = null ;
                for (var i = 0; i < $validates.length; i++) {
                    item = new Item($validates.eq(i));
                    if (this.settings.showError) {
                        item.showError = true;
                    }
                    //观察item的变化
                    item.attach(this);
                    this.validates.push(item);
                }
            },

            //观察item的变化 变化时更新自己
            update: function() {
                //子控件变化 父表单重新验证
                this.start();
            },

            //遍历
            iterator: function(func) {
                for (var i = 0; i < this.validates.length; i++) {
                    func(this.validates[i]);
                }
            },

            //开始验证 显示错误
            test: function() {

                var isValidate = true;

                var firstError = true;
                var errorText = "错误";

                this.iterator(function(item) {
                        item.test();

                        if (item.isValidate == false) {
                            isValidate = false;
                            if (firstError) {
                                errorText = item.errorText;
                                firstError = false;
                            }
                        }
                    }
                );

                this.isValidate = isValidate;
                this.errorText = errorText;

                this.disableSubmit();

            },

            //开始验证 但不错误提示
            start: function() {

                var isValidate = true;

                var firstError = true;
                var errorText = settings.ERROR;

                this.iterator(function(item) {
                        item.start();
                        if (item.isValidate == false) {
                            isValidate = false;
                            if (firstError) {
                                errorText = item.errorText;
                                firstError = false;
                            }
                        }
                    }
                );

                this.isValidate = isValidate;
                this.errorText = errorText;

                this.disableSubmit();

            },

            //禁用提交按钮
            disableSubmit: function() {
                if (this.settings.$submit) {
                    if (this.isValidate) {
                        this.$submit.attr("disabled", false);
                    } else {
                        this.$submit.attr("disabled", true);
                    }
                }
            },

            //观察改变
            watch: function() {

                this.iterator(function(item) {
                        item.watch();
                    }
                )

            },

            //忽视
            ignore: function() {
                this.iterator(function(item) {
                        item.ignore();
                    }
                )
            },

            //刷新
            refresh: function() {
                this.ignore();
                this.init();
            },

            //添加方法
            addMethod: function(name, method) {
                this.addExpand(name, method);
            },

            addExpand: function(name, method) {
                var expand = {
                    name: name,
                    method: method
                };
                this.settings.expands.push(expand);
            }

        };

        function ValidatePublic(options) {
            var validateInstance = new Validate(options);

            this.$area = validateInstance.$area;
            this.getIsValidate = function() {
                return validateInstance.isValidate;
            }
            ;
            this.getErrorText = function() {
                return validateInstance.errorText
            }
            ;
            this.test = function() {
                validateInstance.test();
            }
            ;
            this.start = function() {
                validateInstance.start();
            }
            ;
            this.watch = function() {
                validateInstance.watch();
            }
            ;
            this.refresh = function() {
                validateInstance.refresh();
            }
            ;
            this.addMethod = function(name, func) {
                validateInstance.addMethod(name, func);
            }
            ;
            return this;
        }

        //返回弹出框对象，提供接口供后续操作使用
        function validate(options) {
            return new ValidatePublic(options);
        }

        backstage.validate = validate;
        global.backstage = backstage;

    }
)(this, jQuery, this.backstage || {});
