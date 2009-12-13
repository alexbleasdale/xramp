xquery version "1.0";

declare namespace exist = "http://exist.sourceforge.net/NS/exist"; 

import module namespace util = "http://exist-db.org/xquery/util";



declare option exist:serialize "method=xhtml media-type=text/html omit-xml-declaration=yes indent=yes";

let $resp :=
<tr><td>{current-dateTime()}</td><td>{util:system-time()}</td></tr>
return $resp