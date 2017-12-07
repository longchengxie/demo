(function (global, $, backstage) {
    var defaults = {
        title: "title",
        $content: $("<div>content</div>"),
        width: 400,
        height: 300,
        blurQuery: ".everything",
        className: "",
        callback: function () {
        },
        iframe: false,
        url: "http://www.lvmama.com/",
        close: true,
        closeCallback: function () {
        }
    };
    var $window = $(window);
    var $body = $("body");

    function Shade() {
        this.$shade = null;
        this.init();
    }

    Shade.prototype = {
        constructor: Shade, init: function () {
            var $shade = $('<div class="dialog-shade"></div>');
            $body.append($shade);
            this.$shade = $shade;
            return this;
        }, show: function () {
            this.$shade.show();
        }, hide: function () {
            this.$shade.hide();
        }, destroy: function () {
            this.$shade.remove();
        }
    };
    function Dialog(options) {
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        this.$dialog = null;
        this.$title = null;
        this.$content = null;
        this.$close = null;
        this.$iframe = null;
        this.url = "";
        this.$blur = $(this.settings.blurQuery);
        this.shade = new Shade();
        this.init();
    }

    Dialog.prototype = {
        constructor: Dialog, init: function () {
            this.createElement();
            this.setTitle(this.settings.title);
            this.setContent(this.settings.$content);
            this.setTheme(this.settings.className);
            this.setStyle();
            this.render();
            this.bindEvent();
        }, createElement: function () {
            this.$dialog = $('\
            <div class="dialog">\
                <div class="dialog-title"></div>\
                <div class="dialog-content"></div>\
                <div class="dialog-close">×</div>\
            </div>\
            ');
            this.$title = this.$dialog.find(".dialog-title");
            this.$content = this.$dialog.find(".dialog-content");
            this.$close = this.$dialog.find(".dialog-close");
            if (!this.settings.close) {
                this.$close.remove();
            }
        }, setTheme: function (className) {
            if (className !== "") {
                var classNameArr = className.split(" ");
                for (var i = 0; i < classNameArr.length; i++) {
                    this.$dialog.addClass(classNameArr[i]);
                }
            }
        }, render: function () {
            this.shade.show();
            this.$blur.addClass("dialog-blur");
            $body.append(this.$dialog);
        }, bindEvent: function () {
            var self = this;
            this.$close.on("click", {self: self}, this.closeHandle);
        }, closeHandle: function (event) {
            var self = event.data.self;
            if (self.settings.closeCallback) {
                self.settings.closeCallback();
            }
            self.destroy();
        }, setTitle: function (title) {
            this.$title.html(title);
        }, setContent: function ($content) {
            if (this.settings.iframe) {
                this.setContentIFrame()
            } else {
                this.setContentNormal($content)
            }
        }, setContentNormal: function ($content) {
            this.$content.append($content);
        }, setContentIFrame: function () {
            this.url = this.settings.url;
            this.$iframe = $('<iframe src="about:blank" class="iframe" frameborder="0"></iframe>');
            var contentWidth = this.settings.width;
            var contentHeight = this.settings.height;
            var padding = this.settings.padding;
            if (padding) {
                contentWidth -= padding * 2;
                contentHeight -= padding * 2;
            }
            this.$iframe.css({"width": contentWidth, "height": contentHeight});
            this.$iframe.attr("src", this.url);
            this.$content.append(this.$iframe);
        }, setStyle: function () {
            this.$dialog.css({"width": this.settings.width, "height": this.settings.height + 30});
            var contentWidth = this.settings.width;
            var contentHeight = this.settings.height;
            var padding = this.settings.padding;
            if (padding) {
                contentWidth -= padding * 2;
                contentHeight -= padding * 2;
            }
            this.$content.css({"width": contentWidth, "height": contentHeight, "padding": padding});
            this.centerDisplay();
        }, centerDisplay: function () {
            var left = 0;
            var top = 0;
            var windowWidth = this.getWindowWidth();
            var windowHeight = this.getWindowHeight();
            var width = this.settings.width;
            var height = this.settings.height + 30;
            left = Math.ceil((windowWidth - width) / 2);
            top = Math.ceil((windowHeight - height) / 2 + this.getWindowScrollTop());
            if (top < 10) {
                top = 10
            }
            this.$dialog.css({"left": left, "top": top});
        }, getWindowWidth: function () {
            return $window.width();
        }, getWindowHeight: function () {
            return $window.height();
        }, getWindowScrollTop: function () {
            return $window.scrollTop();
        }, destroy: function () {
            this.shade.destroy();
            this.$blur.removeClass("dialog-blur");
            this.$dialog.remove();
            this.settings.callback();
        }
    };
    function DialogPublic(options) {
        var dialogInstance = new Dialog(options);
        this.destroy = function () {
            dialogInstance.destroy();
        };
        this.$dialog = dialogInstance.$dialog;
        return this;
    }

    function dialog(options) {
        return new DialogPublic(options);
    }

    backstage.dialog = dialog;
    global.backstage = backstage;
})(this, jQuery, this.backstage || {});
(function (global, $, backstage) {
    var defaults = {
        title: "", width: 300, height: 80, content: "提示内容", callback: function () {
        }, close: false
    };

    function DialogLoading(options) {
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        var $content = $('<div class="hint">' +
            this.settings.content + '</div>\
            <div class="btn-group text-center">\
            </div>\
        ');
        var dialog = backstage.dialog({
            width: this.settings.width,
            height: this.settings.height,
            title: this.settings.title,
            $content: $content,
            callback: this.settings.callback,
            close: this.settings.close
        });
        var $btnDetermine = $content.find(".JS_btn_determine");
        $btnDetermine.on("click", function () {
            dialog.destroy();
        });
        return dialog;
    }

    function dialogLoading(options) {
        return new DialogLoading(options)
    }

    backstage.loading = dialogLoading;
    global.backstage = backstage;
})(this, jQuery, this.backstage || {});
(function (global, $, backstage) {
    var defaults = {
        title: "", width: 300, height: 120, content: "提示内容", callback: function () {
        }
    };

    function DialogAlert(options) {
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        var $content = $('<div class="hint">' +
            this.settings.content + '</div>\
            <div class="btn-group text-center">\
            <a class="btn btn-primary JS_btn_determine">关闭</a>\
            </div>\
        ');
        var dialog = backstage.dialog({
            width: this.settings.width,
            height: this.settings.height,
            title: this.settings.title,
            $content: $content,
            callback: this.settings.callback
        });
        var $btnDetermine = $content.find(".JS_btn_determine");
        $btnDetermine.on("click", function () {
            dialog.destroy();
        })
        return dialog;
    }

    function dialogAlert(options) {
        return new DialogAlert(options)
    }

    backstage.alert = dialogAlert;
    global.backstage = backstage;
})(this, jQuery, this.backstage || {});
(function (global, $, backstage) {
    var defaults = {
        width: 300,
        height: 120,
        title: "",
        content: "提示内容",
        determineClass: "btn-primary",
        determineCallback: function () {
        },
        cancelCallback: function () {
        },
        callback: function () {
        }
    };

    function DialogConfirm(options) {
        var that = this;
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        var $content = $('<div class="hint">\
            ' + this.settings.content + '\
            </div>\
            <div class="btn-group text-center">\
            <a class="btn ' + this.settings.determineClass + ' JS_btn_determine">确定</a>\
            <a class="btn JS_btn_cancel" href="javascript:">取消</a>\
            </div>\
        ');
        var dialog = backstage.dialog({
            title: this.settings.title,
            $content: $content,
            width: this.settings.width,
            height: this.settings.height,
            callback: this.settings.callback,
            closeCallback: this.settings.cancelCallback
        });
        var $btnDetermine = $content.find(".JS_btn_determine");
        var $btnCancel = $content.find(".JS_btn_cancel");
        $btnDetermine.on("click", function () {
            that.settings.determineCallback();
            dialog.destroy();
        });
        $btnCancel.on("click", function () {
            that.settings.cancelCallback();
            dialog.destroy();
        });
        return dialog;
    }

    function dialogConfirm(options) {
        return new DialogConfirm(options)
    }

    backstage.confirm = dialogConfirm;
    global.backstage = backstage;
})(this, jQuery, this.backstage || {});