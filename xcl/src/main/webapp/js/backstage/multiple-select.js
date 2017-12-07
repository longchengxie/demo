(function (global, $, backstage) {
    var defaults = {
        $multipleSelect: $(".multiple-select"),
        $TEMPLATE: $('' + '<div class="multiple-select-container">' + '   <ul class="multiple-select-choices">' + '       <li class="search-field"></li>' + '   </ul>' + '   <div class="multiple-select-drop">' + '       <ul class="multiple-select-results">' + '       </ul>' + '   </div>' + '   <div class="search-triangle"></div>' + '</div>'),
        $RESULT: $('' + '<li>' + '   <label>' + '       <input type="checkbox"/>' + '       <span class="text"></span>' + '   </label>' + '</li>'),
        $CHOICE: $('' + '<li class="search-choice">' + '   <span class="text"></span>' + '   <a class="multiple-select-choice-close"></a>' + '</li>')
    };
    var $document = $(document);
    var $window = $(window);
    var $body = $("body");

    function MultipleSelect(options) {
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        this.init();
    }

    function Option() {
        this.index = null;
        this.text = "";
        this.val = "";
        this.selected = null;
        this.unique = null;
        this.$result = null;
        this.$choice = null;
    }

    MultipleSelect.prototype = {
        constructor: MultipleSelect, init: function () {
            this.bindEvent();
            var $select = this.settings.$multipleSelect;
            for (var i = 0; i < $select.length; i++) {
                this.createElement($select.eq(i));
            }
        }, bindEvent: function () {
            this.unbindEvent();
            $document.on("change", ".multiple-select-results :checkbox", {self: this}, this.pickOptionHandler);
            $document.on("click", ".multiple-select-container", {self: this}, this.showDropDownHandler);
            $document.on("click", {self: this}, this.hideDropDownOrCloseChoiceHandler);
        }, unbindEvent: function () {
            $document.off("change", ".multiple-select-results :checkbox", this.pickOptionHandler);
            $document.off("click", ".multiple-select-container", this.showDropDownHandler);
            $document.off("click", this.hideDropDownOrCloseChoiceHandler);
        }, showDropDownHandler: function () {
            var $this = $(this);
            var $dropDown = $this.find(".multiple-select-drop");
            $dropDown.show();
        }, hideDropDownOrCloseChoiceHandler: function (event) {
            var self = event.data.self;
            var $target = $(event.target);
            var $dropDown = $(".multiple-select-drop");
            var $container = $target.parents(".multiple-select-container");
            if ($target.is(".multiple-select-choice-close")) {
                var $choice = $target.parents(".search-choice");
                self.delChoice($choice);
            } else {
                if ($container.length > 0) {
                    $dropDown.not($container.find(".multiple-select-drop")).hide();
                } else {
                    $dropDown.hide();
                }
            }
        }, delChoice: function ($choice) {
            var option = $choice.data("multiple-select-option");
            var $checkbox = option.$result.find(":checkbox");
            var isChecked = $checkbox.prop("checked");
            $checkbox.prop("checked", !isChecked).change();
        }, pickOptionHandler: function (event) {
            var self = event.data.self;
            var $this = $(this);
            var $thatResult = $this.parents("li");
            var option = $thatResult.data("multiple-select-option");
            var $container = $this.parents(".multiple-select-container");
            var $select = $container.prev();
            var $choices = $container.find(".multiple-select-choices");
            var $results = $container.find(".multiple-select-results");
            var $result = $results.children();
            var index = $result.index($thatResult);
            var isChecked = $this.prop("checked");
            $select.find("option").eq(index).prop("selected", isChecked).change();
            if (isChecked) {
                var $choice = self.createChoice(option);
                option.$choice = $choice;
                $choices.append($choice);
            } else {
                option.$choice.remove();
            }
            var $searchField = $container.find(".search-field");
            if ($select.find("option").is(":selected")) {
                $searchField.hide();
            } else {
                $searchField.show();
            }
            if (option.unique) {
                self.refresh($result);
            }
        }, refresh: function ($result) {
            $result.find(":checkbox").prop("disabled", false);
            var unique = {};
            for (var i = 0; i < $result.length; i++) {
                var $resultI = $result.eq(i);
                var optionI = $resultI.data("multiple-select-option");
                var isCheckedI = $resultI.find(":checkbox").is(":checked");
                if (optionI.unique && isCheckedI) {
                    unique[optionI.unique] = true;
                }
            }
            for (var j = 0; j < $result.length; j++) {
                var $resultJ = $result.eq(j);
                var optionJ = $resultJ.data("multiple-select-option");
                var isCheckedJ = $resultJ.find(":checkbox").is(":checked");
                if (unique[optionJ.unique] && !isCheckedJ) {
                    $resultJ.find(":checkbox").prop("disabled", true);
                }
            }
        }, createElement: function ($thatSelect) {
            $thatSelect.hide();
            var $container = this.settings.$TEMPLATE.clone();
            var $searchField = $container.find(".search-field");
            var placeholder = $thatSelect.attr("data-placeholder");
            $searchField.html(placeholder);
            var $results = $container.find(".multiple-select-results");
            var $choices = $container.find(".multiple-select-choices");
            var $maybeOldContainer = $thatSelect.next();
            if ($maybeOldContainer.is(".multiple-select-container")) {
                $maybeOldContainer.remove();
            }
            $thatSelect.after($container);
            var $option = $thatSelect.find("option");
            if ($option.is(":selected")) {
                $searchField.hide();
            }
            for (var i = 0; i < $option.length; i++) {
                (function (self) {
                    var $choice = null;
                    var $result = null;
                    var $thatOption = $option.eq(i);
                    var option = new Option();
                    option.val = $thatOption.val();
                    option.text = $thatOption.html();
                    option.index = i;
                    if ($thatOption.attr("data-unique")) {
                        option.unique = $thatOption.attr("data-unique");
                    }
                    if ($thatOption.prop("selected")) {
                        option.selected = true;
                    }
                    $result = self.createResult(option);
                    option.$result = $result;
                    $results.append($result);
                    if (option.selected) {
                        $choice = self.createChoice(option);
                        option.$choice = $choice;
                        $choices.append($choice);
                        $choices.data("multiple-select-option", option);
                    }
                    $result.data("multiple-select-option", option);
                })(this);
            }
            this.refresh($results.children());
        }, createChoice: function (option) {
            var $choice = this.settings.$CHOICE.clone();
            $choice.data("multiple-select-option", option);
            $choice.find(".text").html(option.text);
            return $choice;
        }, createResult: function (option) {
            var $result = this.settings.$RESULT.clone();
            $result.data("multiple-select-option", option);
            $result.find(".text").html(option.text);
            var $checkbox = $result.find(":checkbox");
            if (option.selected) {
                $checkbox.prop("checked", true);
            }
            return $result;
        }
    };
    function multipleSelect(options) {
        return new MultipleSelect(options);
    }

    backstage.multipleSelect = multipleSelect;
    global.backstage = backstage;
})(this, jQuery, this.backstage || {});