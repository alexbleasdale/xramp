xquery version "1.0";

declare namespace exist = "http://exist.sourceforge.net/NS/exist"; 
declare default element namespace "http://www.w3.org/1999/xhtml";

declare option exist:serialize "method=xhtml media-type=text/html omit-xml-declaration=no indent=yes 
        doctype-public=-//W3C//DTD&#160;XHTML&#160;1.0&#160;Transitional//EN
        doctype-system=http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd";

let $title := 'Loops, Timeouts and XHR | Example Test Runner'

return
<html lang="en">
    <head>
        <title>{$title}</title>
        <script type="text/javascript" src="/exist/rest/db/cust/exist-admin/resources/js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="http://localhost:8080/exist/rest/db/testharness/jquery.ajaxmanager.js"></script>
        <script type="text/javascript" src="http://localhost:8080/exist/rest/db/testharness/jquery.ba-dotimeout.min.js"></script>
        <script type="text/javascript" src="http://localhost:8080/exist/rest/db/testharness/controller.js"></script>
        <link rel="stylesheet" href="screen.css" type="text/css" media="screen, projection"/>
        <link rel="stylesheet" href="testrunner.css" type="text/css" media="screen, projection"/>
    </head>
    <body>
        <div class="container">
            <div id="test_runner">
                <h3>eXist Test Runner</h3>
                <table id="results">
                    <thead>
                        <tr>
                            <th>Timestamp</th>
                            <th>eXist SystemTime</th>
                            <th>XQuery Completion Time (ms)</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <td colspan="2"></td>
                            <td>X</td>
                        </tr>
                    </tfoot>
                    <tbody>
                        <tr class="options">
                            <td colspan="3">
                                <a href="#test" class="start">Start Tests</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div id="resultset"></div>
            </div>
        </div>
    </body>
</html>
