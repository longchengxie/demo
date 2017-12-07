(function (global, $, backstage) {
    var defaults = {
        content: "<span>错误提示</span>",
        width: 150,
        height: 30,
        className: "",
        selfDestructionTime: 1000,
        destroyCallback: function () {
        }
    };
    var $window = $(window);
    var $body = $("body");

    function FloatAlert(options) {
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        this.$floatAlert = null;
        this.init();
    }

    FloatAlert.prototype = {
        constructor: FloatAlert, init: function () {
            this.createElement();
            this.setContent(this.settings.content);
            this.setTheme(this.settings.className);
            this.setStyle();
            this.render();
            this.selfDestruction();
        }, createElement: function () {
            this.$floatAlert = $('\
            <div class="float-alert">\
            </div>\
            ');
        }, setTheme: function (className) {
            if (className !== "") {
                var classNameArr = className.split(" ");
                for (var i = 0; i < classNameArr.length; i++) {
                    this.$floatAlert.addClass(classNameArr[i]);
                }
            }
        }, render: function () {
            $body.append(this.$floatAlert);
        }, setContent: function (content) {
            this.$floatAlert.append($('<span>' + content + '</span>'));
        }, setStyle: function () {
            this.$floatAlert.css({"width": this.settings.width, "height": this.settings.height});
            this.centerDisplay();
        }, centerDisplay: function () {
            var left = 0;
            var top = 0;
            var windowWidth = this.getWindowWidth();
            var windowHeight = this.getWindowHeight();
            var width = this.settings.width;
            var height = this.settings.height;
            left = Math.ceil((windowWidth - width) / 2);
            top = Math.ceil((windowHeight - height) / 2);
            if (top < 10) {
                top = 10
            }
            this.$floatAlert.css({"left": left, "top": top});
        }, getWindowWidth: function () {
            return $window.width();
        }, getWindowHeight: function () {
            return $window.height();
        }, destroy: function () {
            var self = this;
            this.$floatAlert.fadeOut(200, function () {
                self.$floatAlert.remove();
            });
            this.settings.destroyCallback();
        }, selfDestruction: function () {
            var self = this;
            setTimeout(function () {
                self.destroy();
            }, this.settings.selfDestructionTime);
        }
    };
    function FloatAlertPublic(options) {
        var floatAlertInstance = new FloatAlert(options);
        this.destroy = function () {
            floatAlertInstance.destroy();
        };
        this.$floatAlert = floatAlertInstance.$floatAlert;
        return this;
    }

    function floatAlert(options) {
        return new FloatAlertPublic(options);
    }

    backstage.floatAlert = floatAlert;
    global.backstage = backstage;
})(this, jQuery, this.backstage || {});