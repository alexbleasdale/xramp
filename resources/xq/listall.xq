xquery version "1.0";

declare namespace oco-p="http://www.oco.com/schemas";
declare namespace xmldb="http://exist-db.org/xquery/xmldb";

(: this just throws back a list of all XML docs currently in the collection - very simple :)

let $collection := collection('/db/perfreport', "admin", "password")

return
<Items>
    {$collection//guid}
</Items>
