(function (global, $, backstage) {
    var defaults = {
        TEMPLATE: '' + '<div class="autocomplete-menu">' + '   <ul></ul>' + '   <div class="autocomplete-loading">查找中...</div>' + '</div>',
        click: false
    };
    var $document = $(document);

    function Autocomplete(options) {
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        this.$menu = null;
        this.$menuUl = null;
        this.$menuLoading = null;
        this.$input = null;
        this.isOver = false;
        this.isChoice = false;
        this.init();
    }

    Autocomplete.prototype = {
        constructor: Autocomplete, init: function () {
            this.$menu = $(this.settings.TEMPLATE).clone();
            this.$menuUl = this.$menu.find("ul");
            this.$menuLoading = this.$menu.find(".autocomplete-loading");
            this.bindEvent();
        }, bindEvent: function () {
            var self = this;
            $document.on("mouseenter", this.settings.query, {self: self}, this.overHandler);
            $document.on("mouseleave", this.settings.query, {self: self}, this.outHandler);
            this.$menu.on("mouseenter", {self: self}, this.overHandler);
            this.$menu.on("mouseleave", {self: self}, this.outHandler);
            $document.on("focus", this.settings.query, {self: self}, this.clickHandler);
            this.$menu.on("click", "li", {self: self}, this.clickMenuHandler);
            $document.on("blur", this.settings.query, {self: self}, this.blurHandler);
            $document.on("keyup", this.settings.query, {self: self}, this.keyupHandler);
        }, keyupHandler: function (event) {
            var self = event.data.self;
            var $this = $(this);
            self.$input = $this;
            var top = Math.round($this.offset().top);
            var left = Math.round($this.offset().left);
            var width = Math.round($this.outerWidth());
            var height = Math.round($this.outerHeight());
            self.setPosition(top + height, left);
            self.setStyle(width);
            self.showMenu();
            self.settings.fillData(self);
            var code = event.keyCode;
            switch (code) {
                case 37:
                    break;
                case 39:
                    break;
                case 40:
                    self.activeDown();
                    break;
                case 38:
                    self.activeUp();
                    break;
                case 13:
                    self.choiceActive();
                    break;
                default:
                    self.isChoice = false;
                    self.inputHandler();
                    break;
            }
            if ($.trim(self.$input.val()) == "" && !self.settings.click) {
                self.hideMenu();
            }
        }, choiceActive: function () {
            var $li = this.$menu.find("li");
            var $active = $li.filter(".active");
            if ($active.length == 0) {
            } else {
                this.choice($active);
            }
        }, activeDown: function () {
            var $li = this.$menu.find("li");
            var length = $li.length;
            var index = 0;
            if (length == 0) {
                return false;
            }
            var $active = $li.filter(".active");
            if ($active.length == 0) {
                $li.first().addClass("active");
            } else {
                index = $li.index($active);
                $li.eq(index).removeClass("active");
                if (index >= length - 1) {
                    $li.first().addClass("active");
                } else {
                    $li.eq(index).next().addClass("active");
                }
            }
        }, activeUp: function () {
            var $li = this.$menu.find("li");
            var length = $li.length;
            var index = 0;
            if (length == 0) {
                return false;
            }
            var $active = $li.filter(".active");
            if ($active.length == 0) {
                $li.last().addClass("active");
            } else {
                index = $li.index($active);
                $li.eq(index).removeClass("active");
                if (index == 0) {
                    $li.last().addClass("active");
                } else {
                    $li.eq(index).prev().addClass("active");
                }
            }
        }, inputHandler: function () {
            this.settings.fillData(this);
        }, overHandler: function (event) {
            var self = event.data.self;
            self.isOver = true;
        }, outHandler: function (event) {
            var self = event.data.self;
            self.isOver = false;
        }, showMenu: function () {
            $document.find("body").append(this.$menu);
            this.$menu.show()
        }, hideMenu: function () {
            this.$menu.hide();
            if (!this.isChoice) {
                if (this.settings.clearData) {
                    this.$input.val("");
                    this.settings.clearData(this);
                }
            }
        }, clickHandler: function (event) {
            var self = event.data.self;
            if (self.settings.click) {
                var $this = $(this);
                self.$input = $this;
                var top = Math.round($this.offset().top);
                var left = Math.round($this.offset().left);
                var width = Math.round($this.outerWidth());
                var height = Math.round($this.outerHeight());
                self.setPosition(top + height, left);
                self.setStyle(width);
                self.showMenu();
                self.settings.fillData(self);
            }
        }, blurHandler: function (event) {
            var self = event.data.self;
            if (self.isOver) {
            } else {
                self.hideMenu();
            }
        }, choice: function ($ele) {
            if (this.settings.choice) {
                this.settings.choice(this, $ele);
            }
        }, loading: function () {
            this.$menuUl.hide();
            this.$menuLoading.show();
        }, loaded: function () {
            this.$menuLoading.hide();
            this.$menuUl.show();
        }, setPosition: function (top, left) {
            this.$menu.css({"top": top, "left": left});
        }, setStyle: function (width) {
            this.$menu.css({"width": width});
        }, clickMenuHandler: function (event) {
            var self = event.data.self;
            var $this = $(this);
            self.choice($this);
            self.isChoice = true;
            self.hideMenu()
        }
    };
    function AutocompletePublic(options) {
        var autocompleteInstance = new Autocomplete(options);
        this.loading = function () {
            autocompleteInstance.loading();
        };
        this.loaded = function () {
            autocompleteInstance.loaded();
        };
        this.$autocomplete = autocompleteInstance.$autocomplete;
    }

    function autocomplete(options) {
        return new AutocompletePublic(options);
    }

    backstage.autocomplete = autocomplete;
    global.backstage = backstage;
})(this, jQuery, this.backstage || {});