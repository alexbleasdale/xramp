xquery version "1.0";

(: This returns true if there are not files or subcollections in this collection :)
declare function local:is-empty-collection($collection as xs:string) as xs:boolean {
  if (
       empty(xmldb:get-child-resources($collection)) and 
       empty(xmldb:get-child-collections($collection))
     )
     then true ()
     else false()
};

(: This xquery returns a list of all the files and sub-collections in jsTree format of a given collection.
   It takes a single parameter "collection" and if none is supplied it will defualt to "/db"
   See the http://www.jsTree.com library "flat XML" standard.  Note that flat is used
   since we only are fetching a single level at a time.  :)

let $title := "List Collections"

let $collection := request:get-parameter('id', '/db')
let $dbroot := '/db'

(: note that the IDs we pass to the http://www.jsTree.com library can not start with a '/' :)
let $new-collection := substring-after($collection, 'xml_')


(: Todo use subsequence($in, 1, 100) to limit the number of values in a subcollection :)

let $sub-collections := 
   for $child in xmldb:get-child-collections($new-collection)
   return $child

let $resources :=
   for $child in xmldb:get-child-resources($new-collection)
   return $child
   
let $all :=
   for $child in ($sub-collections, $resources)
   order by $child
   return $child

return
<root>
   {
   for $child at $count in $all
      let $full-collection-path := concat($new-collection, '/', $child)
      (: fixme - to a test to see if we have subcollections :)
      let $is-a-file :=
         if (contains($child, '.'))
            then true()
            else false()
   return
     <item parent_id="0" id="xml_{$full-collection-path}">
     {if ($is-a-file)
               then attribute {'rel'} {'file'} 
               else () 
               }
      {if ( not($is-a-file) and not(local:is-empty-collection($full-collection-path)))
               then attribute {'state'} {'closed'} 
               else () 
               }
        <content>
           <name rel="{concat($dbroot, $full-collection-path)}">
            {$child}
           </name>
        </content>
     </item>
    }
</root>