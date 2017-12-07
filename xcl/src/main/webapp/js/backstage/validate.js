(function (global, $, backstage) {
    var defaults = {
        showError: true,
        REQUIRED: "该字段不能为空",
        REGULAR: "格式不正确",
        $ERROR: $('<i class="error"><span class="icon icon-danger"></span><span class="error-text"></span></i>')
    };
    var settings = {};

    function Item($ele) {
        this.$ele = $ele;
        this.val = null;
        this.$group = $ele.parents(".form-group").eq(0);
        this.showError = false;
        this.rule = null;
        this.isValidate = false;
        this.errorText = "错误";
        this.observer = null;
        this.init();
    }

    Item.prototype = {
        constructor: Item, init: function () {
            this.rule = this.participle();
            this.$ele.data("validate-item", this);
        }, attach: function (observer) {
            this.observer = observer;
        }, participle: function () {
            var rule = {};
            var multipleRuleArr;
            var keyValueArr;
            var ruleStr = this.$ele.attr("data-validate");
            ruleStr = ruleStr.replace(/[\s]/g, "");
            ruleStr = ruleStr.substr(1, ruleStr.length - 2);
            multipleRuleArr = ruleStr.split(',');
            for (var i = 0; i < multipleRuleArr.length; i++) {
                keyValueArr = multipleRuleArr[i].split(':');
                switch (keyValueArr[1]) {
                    case"true":
                        keyValueArr[1] = true;
                        break;
                    case"false":
                        keyValueArr[1] = false;
                        break;
                }
                rule[keyValueArr[0]] = keyValueArr[1];
            }
            return rule;
        }, getRadioVal: function () {
            var name = this.$ele.attr("name");
            var $radioChecked = this.$group.find(':radio:checked');
            return $.trim($radioChecked.val());
        }, getCheckboxVal: function () {
            var ret = "";
            var $checkboxChecked = this.$group.find(':checkbox:checked');
            for (var i = 0; i < $checkboxChecked.length; i++) {
                ret += $.trim($checkboxChecked.val()) + ",";
            }
            return ret;
        }, start: function () {
            switch (this.$ele.attr("type")) {
                case"radio":
                    this.val = this.getRadioVal();
                    break;
                case"checkbox":
                    this.val = this.getCheckboxVal();
                    break;
                default:
                    this.val = $.trim(this.$ele.val());
                    break;
            }
            var isValidate = true;
            if (this.$ele.is("[disabled]")) {
                this.isValidate = isValidate;
                return false;
            }
            if (this.$ele.is("[readonly]")) {
                if (this.$ele.is("[data-validate-readonly='true']")) {
                }
                else {
                    this.isValidate = isValidate;
                    return false;
                }
            }
            if (this.rule.required) {
                if (this.required() == false) {
                    isValidate = false;
                }
            }
            if (this.rule.regular) {
                if (this.regular() == false) {
                    isValidate = false;
                }
            }
            this.isValidate = isValidate;
        }, test: function () {
            this.start();
            this.render();
        }, watch: function () {
            this.bindEvent();
        }, ignore: function () {
            this.unbindEvent();
        }, render: function () {
            var $error = this.$group.find("i.error");
            var $newError = null;
            if (this.showError) {
                $error.remove();
                if (this.isValidate == false) {
                    $newError = settings.$ERROR.clone();
                    $newError.find(".error-text").html(this.errorText);
                    this.$group.append($newError);
                    this.$ele.addClass("error");
                } else {
                    this.$ele.removeClass("error");
                }
            }
        }, bindEvent: function () {
            var self = this;
            this.unbindEvent();
            this.$ele.off("input change keyup", this.changeHandle);
            this.$ele.on("input change keyup", {self: self}, this.changeHandle);
        }, unbindEvent: function () {
            this.$ele.off("input change", this.changeHandle);
        }, changeHandle: function (event) {
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
        }, change: function (self) {
            self.test();
            self.notify();
        }, notify: function () {
            this.observer.update();
        }, required: function () {
            if (this.val == "") {
                this.errorText = settings.REQUIRED;
                var required = this.rule.required;
                if (typeof required == "string") {
                    this.errorText = required;
                }
                return false;
            } else {
                return true;
            }
        }, regular: function () {
            if (this.val == "") {
                return true;
            }
            var regStr = this.$ele.data("validate-regular");
            var reg = new RegExp(regStr);
            if (reg.test(this.val)) {
                return true;
            } else {
                this.errorText = settings.REGULAR;
                var regular = this.rule.regular;
                if (typeof regular == "string") {
                    this.errorText = regular;
                }
                return false;
            }
        }
    };
    function Validate(options) {
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        settings = this.settings;
        this.$area = null;
        this.$submit = null;
        this.validates = [];
        this.isValidate = false;
        this.init();
    }

    Validate.prototype = {
        constructor: Validate, init: function () {
            this.$area = this.settings.$area;
            if (this.settings.$submit) {
                this.$submit = this.settings.$submit;
            }
            var $validates = this.$area.find('[data-validate]');
            var item = null;
            for (var i = 0; i < $validates.length; i++) {
                item = new Item($validates.eq(i));
                if (this.settings.showError) {
                    item.showError = true;
                }
                item.attach(this);
                this.validates.push(item);
            }
        }, update: function () {
            this.start();
        }, iterator: function (func) {
            for (var i = 0; i < this.validates.length; i++) {
                func(this.validates[i]);
            }
        }, test: function () {
            var isValidate = true;
            var firstError = true;
            var errorText = "错误";
            this.iterator(function (item) {
                item.test();
                if (item.isValidate == false) {
                    isValidate = false;
                    if (firstError) {
                        errorText = item.errorText;
                        firstError = false;
                    }
                }
            });
            this.isValidate = isValidate;
            this.errorText = errorText;
            this.disableSubmit();
        }, start: function () {
            var isValidate = true;
            var firstError = true;
            var errorText = "错误";
            this.iterator(function (item) {
                item.start();
                if (item.isValidate == false) {
                    isValidate = false;
                    if (firstError) {
                        errorText = item.errorText;
                        firstError = false;
                    }
                }
            });
            this.isValidate = isValidate;
            this.errorText = errorText;
            this.disableSubmit();
        }, disableSubmit: function () {
            if (this.settings.$submit) {
                if (this.isValidate) {
                    this.$submit.attr("disabled", false);
                } else {
                    this.$submit.attr("disabled", true);
                }
            }
        }, watch: function () {
            this.iterator(function (item) {
                item.watch();
            })
        }, ignore: function () {
            this.iterator(function (item) {
                item.ignore();
            })
        }, refresh: function () {
            this.ignore();
            this.init();
        }
    };
    function ValidatePublic(options) {
        var validateInstance = new Validate(options);
        this.$area = validateInstance.$area;
        this.getIsValidate = function () {
            return validateInstance.isValidate;
        };
        this.getErrorText = function () {
            return validateInstance.errorText
        };
        this.test = function () {
            validateInstance.test();
        };
        this.start = function () {
            validateInstance.start();
        };
        this.watch = function () {
            validateInstance.watch();
        };
        this.refresh = function () {
            validateInstance.refresh();
        };
        return this;
    }

    function validate(options) {
        return new ValidatePublic(options);
    }

    backstage.validate = validate;
    global.backstage = backstage;
})(this, jQuery, this.backstage || {});