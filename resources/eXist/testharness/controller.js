/**
*  Test Runner v0.1
*  @author alexbleasdale
*
*/

// global variables
var TIMEOUT_INTERVAL = 500;
var BASE_URI = "/exist/rest/db/testharness/timestamp.xq?id=";
var QUERY_ITEMS =[ "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"];
var RESULTS_DIV = "#results";

/**
* jQuery TestRunner Controller v0.1
*/
$(function () {
    var gChartUri = "http://chart.apis.google.com/chart?cht=ls&chxt=y&chs=600x400&chd=t:";
    var queryTimes = [];
    var total = 0;
    /**
     * Generates a chart based on the time (in Miliseconds) each query took.
     *
     * Example: http://chart.apis.google.com/chart?cht=ls&chd=t:27,25,60,31,25,39,25,31,26,28,80,28&chs=250x100
     *
     */
    function buildChart() {
         for (var i=0; i<queryTimes.length; i++) {
            // create total value for all items
            total = total + queryTimes[i];
            // append the chart URI
            gChartUri += queryTimes[i];
            if (i != (queryTimes.length - 1)){
                // console.log(queryTimes.length - 1, " | ", i);
                gChartUri += ",";
            }
         }
        $("#resultset").append('<h3>Results:</h3><img src="'+gChartUri+'" />');
    }
    
    function generateTotal(){
        $(RESULTS_DIV + " tfoot td:last").html(total);
        $(RESULTS_DIV + " tfoot tr").fadeIn("slow");
    }
    
    // set up a function to make an xhr request with a GET param
    function timestampReqItem(id, startTime) {
        ajaxManager1.add({
            success: function (html) {
                // add query time to array.
                queryTimes.push(new Date().getTime() - startTime);
                html = $(html + " td:last").append('<td>'+queryTimes[queryTimes.length - 1]+'</td>');
                
                $(RESULTS_DIV).append(html);
                $(RESULTS_DIV + " tr:last").addClass("hide");
                $(RESULTS_DIV + " tr:even").addClass("alt");
                $(RESULTS_DIV + " tr:last").fadeIn("slow").removeClass("hide");
                
                // when we're at the end we can build the chart 
                if (QUERY_ITEMS.length === queryTimes.length){
                    buildChart();
                    generateTotal();
                }
            },
            url: BASE_URI + id
        });
    }
    
    // set up XHR manager and queue
    var ajaxManager1 = $.manageAjax({
        manageType: 'queue', maxReq: 0
    });
    
    // create a counter
    var counter = 0;
    
    $('a.start').click(function () {
        // hide the testrunner trigger
        $(RESULTS_DIV + " tr:last").fadeOut("fast");
        
        // Start a polling loop with a counter.
        $.doTimeout(TIMEOUT_INTERVAL, function () {
            timestampReqItem(QUERY_ITEMS[counter], new Date().getTime());
            counter++;
            if (counter < QUERY_ITEMS.length) {
                return true;
            } 
        });
    });
});