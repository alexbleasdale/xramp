xquery version "1.0";

declare namespace exist = "http://exist.sourceforge.net/NS/exist"; 
declare default element namespace "http://www.w3.org/1999/xhtml";

declare option exist:serialize "method=xhtml media-type=text/html omit-xml-declaration=no indent=yes 
        doctype-public=-//W3C//DTD&#160;XHTML&#160;1.0&#160;Transitional//EN
        doctype-system=http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd";

let $title := 'Tree Metadata Builder | Syntactica'

return
<html lang="en">
    <head>
        <title>{$title}</title>
        <script type="text/javascript" src="jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="jquery.tree.min.js"></script>
        
        <script type="text/javascript" src="lib/sarissa.js"></script>
        
        <script type="text/javascript" src="plugins/jquery.tree.contextmenu.js"></script>
        <script type="text/javascript" src="plugins/jquery.tree.xml_flat.js"></script>
        <script type="text/javascript" src="controller.js"></script>
        
        <link rel="stylesheet" href="screen.css" type="text/css" media="screen, projection" />
        <link rel="stylesheet" href="editor.css" type="text/css" media="screen, projection" />
    </head>
    <body>
        <div class="container">
           <h2>Tree Editor Example</h2>
           
           <div id="left">
           <!-- TARGET -->
             <h3>Source Tree</h3>
             <div id="source-tree">
             
             </div>
           </div>
          
           <div id="right">
           <!-- TARGET -->
                <h3>Target Tree</h3>
                <div id="target-tree">
          
                </div>
           </div>
           
           <br class="clearboth" />
           
        </div>
    </body>
</html>
