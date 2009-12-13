(:
    INCOMPLETE - example for generating a treeTable using jQuery and XQuery
:)

xquery version "1.0";

declare namespace xacml = "urn:oasis:names:tc:xacml:2.0:policy:schema:os";
declare default element namespace "urn:oasis:names:tc:xacml:2.0:policy:schema:os";

declare option exist:serialize "method=xhtml media-type=text/html indent=yes";

let $title := 'Policy Set Tree Table'

let $id := request:get-parameter('id', '')

(: check for required parameters :)
return

if (not($id))
    then (
    <error>
        <message>Parameter "id" is missing.  This argument is required for this web service.</message>
    </error>)
    else
      let $collection := '/db/treetable/data'
      let $policy-set := collection($collection)/xacml:PolicySet[@PolicySetId = $id]

return
<html>
   <head>
      <title>{$title}</title>
      {style:import-css()}
      <link rel="stylesheet" type="text/css" href="/exist/rest/db/cust/exist-admin/resources/css/master.css" />
      <link rel="stylesheet" type="text/css" href="/exist/rest/db/cust/exist-admin/resources/css/jquery.treeTable.css" />
      <script type="text/javascript" src="/exist/rest/db/cust/exist-admin/resources/js/jquery-1.3.2.min.js"></script>
      <script type="text/javascript" src="/exist/rest/db/cust/exist-admin/resources/js/jquery.ui.min.js"></script>
      <script type="text/javascript" src="/exist/rest/db/cust/exist-admin/resources/js/jquery.treeTable.min.js"></script>	
      <script type="text/javascript" src="/exist/rest/db/cust/exist-admin/resources/js/jquery.qtip-1.0.0-rc3.min.js"></script>	
      <!-- TODO - Move this -->
      <style type="text/css">
        /* <![CDATA[ */
        .tree-table th {background:#ccc; padding:5px; font-weight:bold;}
        .alt {background:#f0f0f6;}
        /* ]]> */
      </style>
      <script type="text/javascript">  
        /* <![CDATA[ */
        
     	$(document).ready(function() {
      		$(".tree-table").treeTable();
      		 $(".tree-table tr:even").addClass("alt");
      		$('[title]').qtip({ 
        position: {
        corner: { target: 'topLeft', tooltip: 'bottomLeft' } }, adjust: { screen: true },
       
   /*    style: { 
      width: 200,
      padding: 5,
      background: '#A2D959',
      color: 'black',
      textAlign: 'center',
      border: {
         width: 7,
         radius: 5,
         color: '#A2D959'
      }*/

        style: { background: '#0990ee', color:'white', border: { width:7, radius:5, color: '#0072bf' }, padding:5, name: 'blue', tip: true } } 
        );
        
     	});
     	/* ]]> */
      </script>
   </head>
   <body>
      <div id="main">
          <div id="content">
          
         <h1>{$title}</h1>
               <table>
                  <tbody>
                   <tr><th align="right">ID:</th><td>{string(string-join($policy-set/@PolicySetId, ', '))}</td></tr>

                   <tr><th align="right">Description:</th><td>{$policy-set/Description/text()}</td></tr>

                   </tbody>
                </table>
                
              <h2>Policies</h2>
              
              {
              for $policy in $policy-set/Policy
              return
              <div class="policy">
                  <h3>Policy</h3>
                  <div>PolicyId = {string(string-join($policy/@PolicyId, ', '))}</div>
                  <span>Description = {$policy/Description/text()}</span>
                  <h4>Rule</h4>
                     <div>RuleID={string(string-join($policy/Rule/@RuleId, ', '))}</div>
                     <h5>Resource</h5>
                     <div class="Resource">{$policy/Rule/Target/Resources/Resource/ResourceMatch/AttributeValue/text()}</div>
                     <h5>Action</h5>
                     <div class="Action">{$policy/Rule/Target/Actions/Action/ActionMatch/AttributeValue/text()}</div>
               </div>
               }
               
               <h2>Tree Table</h2>
            
              <table class="tree-table">
               <caption>Role-Based Access Control</caption>
                  <thead>
                    <tr>
                      <th colspan="6">Role/Member</th>
                    </tr>
                  </thead>
              <tbody>
              { for $policy at $pos in $policy-set/Policy
              return
              <span class="policy-item">
                <tr id="ex0-node-{$pos}">
                    <td title="{$policy/Description/text()}"><a href="http://example.com">{string(string-join($policy/@PolicyId, ', '))}</a></td>
                    <td><a href="http://example.com">Add Member</a></td>
                    <td><a href="http://example.com">Add Role</a></td>
                    <td><a href="http://example.com">Remove Role</a></td>
                    <td></td>
                    <td></td>
                </tr>
                {
                for $rule at $innerpos in $policy/Rule
                return 
                <tr id="ex0-node-{$pos}-{$innerpos}" class="child-of-ex0-node-{$pos}">
                    <td>{string($rule/@RuleId)}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><a href="http://example.com">Remove Member</a></td>
                    <td><a href="http://example.com">Replace Member</a></td>
                </tr>
                }
              </span>
              }
              </tbody>
              </table>
              
               <a href="../edit/edit.xq?id={$id}">Edit Policy Set</a><br/>
               <a href="../edit/delete-confirm.xq?id={$id}">Delete Policy Set</a>
               </div>
      </div>
   </body>
</html>