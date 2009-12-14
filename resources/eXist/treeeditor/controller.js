/**
*  Tree Editor v0.1
*  @author Syntactica
*
*/

// global variables
var SOURCE_INSTANCE_URI = "/exist/rest/db/treeeditor/flatxml1.xml";
var DEST_INSTANCE_URI = "/exist/rest/db/treeeditor/flatxml2.xml";


/**
* jQuery TreeEditor Controller v0.1
*/
$(function () {
    //console.log("DOM ready");
    
    // Manage Source Tree
    $("#source-tree").tree({
    
        plugins: {
            contextmenu: {
                items: {
                    create: false,
                    rename: false
                }
            }
        },
     
        data: {
            type: "xml_flat",
            opts: {
                url: SOURCE_INSTANCE_URI
            }
        },
        
        rules: {
            draggable: "all",
            dragrules: "all",
            drag_copy: "on",
        }
    });
    
    
    // Manage Target Tree
    $("#target-tree").tree({
        
        plugins: {
            contextmenu: {
                items: {
                    create: false,
                    rename: false
                }
            }
        },
       
        data: {
            type: "xml_flat",
            opts: {
                url: DEST_INSTANCE_URI
            }
        },
        
        rules: {
            dragrules: "all",
            multitree: "all"
        }
    });
});