/**
 * File:         float-alert.js
 * Create:       2015-11-24
 * Last Update : 2015-11-24
 * Author:       江圣
 * Description:  浮动警告框插件
 */

(function(global, $, backstage) {

        var defaults = {
            content: "<span>错误提示</span>",
            width: 150,
            height: 30,
            className: "",
            //销毁等待时间
            selfDestructionTime: 1000,
            //销毁时调用
            destroyCallback: function() {

            }
        };

        var $window = $(window);
        var $body = $("body");

        //弹出框
        function FloatAlert(options) {
            this.settings = $.extend({}, defaults, options);
            this._defaults = defaults;
            this.$floatAlert = null ;
            this.init();
        }

        FloatAlert.prototype = {

            constructor: FloatAlert,

            //初始化
            init: function() {
                this.createElement();
                this.setContent(this.settings.content);
                this.setTheme(this.settings.className);
                this.setStyle();
                this.render();
                //自我销毁
                this.selfDestruction();
            },

            //创建元素
            createElement: function() {
                this.$floatAlert = $('\
            <div class="float-alert">\
            </div>\
            ');
            },

            //设置主题
            setTheme: function(className) {
                if (className !== "") {
                    var classNameArr = className.split(" ");
                    for (var i = 0; i < classNameArr.length; i++) {
                        this.$floatAlert.addClass(classNameArr[i]);
                    }
                }
            },

            //渲染
            render: function() {
                $body.append(this.$floatAlert);
            },

            //设置内容
            setContent: function(content) {

                this.$floatAlert.append($('<span>' + content + '</span>'));

            },

            //设置样式
            setStyle: function() {
                this.$floatAlert.css({
                    "width": this.settings.width,
                    "height": this.settings.height
                });
                this.centerDisplay();
            },

            //居中显示
            centerDisplay: function() {

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

                this.$floatAlert.css({
                    "left": left,
                    "top": top
                });

            },

            //获取屏幕尺寸
            getWindowWidth: function() {
                return $window.width();
            },
            getWindowHeight: function() {
                return $window.height();
            },

            //销毁
            destroy: function() {
                var self = this;
                this.$floatAlert.fadeOut(200, function() {
                        self.$floatAlert.remove();
                    }
                );
                //销毁时执行回调函数
                this.settings.destroyCallback();
            },

            //自我销毁
            selfDestruction: function() {
                var self = this;
                setTimeout(function() {
                        self.destroy();
                    }
                    , this.settings.selfDestructionTime);
            }

        };

        function FloatAlertPublic(options) {
            var floatAlertInstance = new FloatAlert(options);
            this.destroy = function() {
                floatAlertInstance.destroy();
            }
            ;
            this.$floatAlert = floatAlertInstance.$floatAlert;
            return this;
        }

        //返回弹出框对象，提供接口供后续操作使用
        function floatAlert(options) {
            return new FloatAlertPublic(options);
        }

        backstage.floatAlert = floatAlert;
        global.backstage = backstage;

    }
)(this, jQuery, this.backstage || {});
