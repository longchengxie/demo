"use strict";

$(function() {

        var $document = $(document);

        var $headers = $("#main>:header,#main>.section>:header");
        var $navigation = $('<div class="navigation">');

        $("body").append($navigation);
        var size = $headers.length;
        var $tempA;
        for (var i = 0; i < size; i++) {
            var $header = $headers.eq(i);
            $header.data("header-index", i);
            var tagName = $header.get(0).tagName.toLocaleLowerCase();
            $tempA = $('<a class="' + tagName + '">' + $header.html() + '</a>');
            $navigation.append($tempA)
        }

        $document.on("click", ".navigation>a", function() {
                var $this = $(this);
                var index = $this.index();
                var $header = $headers.eq(index);
                var top = $header.offset().top;
                $("html,body").finish().animate({
                    "scrollTop": top
                }, 200);
            }
        );

        $document.on("mouseenter", ".navigation>a", function() {
                var $this = $(this);
                var index = $this.index();
                $headers.removeClass("hover");
                var $header = $headers.eq(index);
                $header.addClass("hover");
            }
        );

        $document.on("mouseleave", ".navigation>a", function() {
                $headers.removeClass("hover");
            }
        );

        $('pre code').each(function(i, block) {
                hljs.highlightBlock(block);
            }
        );

    }
);
