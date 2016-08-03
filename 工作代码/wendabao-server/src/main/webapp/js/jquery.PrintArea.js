(function ($) {
    var printAreaCount = 0;
    $.fn.printArea = function (style,htmlStr,iframeCss) {
        var ele = $(this);
        var eleHtml = $(ele).html();
        if(undefined != htmlStr && ""!=htmlStr){
            eleHtml = htmlStr;
        }
        var iframeCssStr = "padding-left:20px;";
        if(undefined != iframeCss && ""!=iframeCss){
            iframeCssStr = iframeCss;
        }
        var idPrefix = "printArea_";
        removePrintArea(idPrefix + printAreaCount);
        printAreaCount++;
        var iframeId = idPrefix + printAreaCount;
        var iframeStyle = 'position:absolute;width:0px;height:0px;left:-500px;top:-500px;';
        iframe = document.createElement('IFRAME');
        $(iframe).attr({
            style: iframeStyle,
            id: iframeId
        });
        document.body.appendChild(iframe);
        var doc = iframe.contentWindow.document;
        doc.write('<style type=\"text/css\">'+style+'</style>');
        $(document).find("link").filter(function () {
            return $(this).attr("rel").toLowerCase() == "stylesheet";
        }).each(
            function () {
                doc.write('<link type="text/css" rel="stylesheet" href="'
                    + $(this).attr("href") + '" >');
            });
        doc.write('<div style="'+iframeCssStr+'" class="' + $(ele).attr("class") + '">' + eleHtml
            + '</div>');

        doc.close();
        var frameWindow = iframe.contentWindow;
        frameWindow.close();
        frameWindow.focus();
        frameWindow.print();
    }
    var removePrintArea = function (id) {
        $("iframe#" + id).remove();
    };
})(jQuery);