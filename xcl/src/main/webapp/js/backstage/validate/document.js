"use strict";

$(function () {

    $('pre code').each(function(i, block) {
        hljs.highlightBlock(block);
    });

});