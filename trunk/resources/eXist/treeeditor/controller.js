/**
*  Tree Editor v0.1
*  @author Syntactica
*
*/

// global variables


/**
* jQuery TreeEditor Controller v0.1
*/
$(function () {
    console.log("DOM ready");
    
    // Manage Source Tree
    
    $("#source-tree").tree({
      
      /*
        plugins: {
            contextmenu: {
                items: {
                    // get rid of the remove item
                    create: false,
                    rename: false,
                    remove: false,
                    // add an item of our own
                    my_act: {
                        label: "Copy to Target",
                        icon: "", // you can set this to a classname or a path to an icon like ./myimage.gif
                        visible: function (NODE, TREE_OBJ) {
                            // this action will be disabled if more than one node is selected
                            if (NODE.length != 1) return 0;
                            // this action will not be in the list if condition is met
                            if (TREE_OBJ.get_text(NODE) == "Child node 1") return - 1;
                            // otherwise - OK
                            return 1;
                        },
                        action: function (NODE, TREE_OBJ) {
                            alert(TREE_OBJ.get(NODE, "xml_nested", false));
                        },
                        separator_before: true
                    }
                }
            }
        },
        
        */
        data: {
            type: "xml_flat",
            opts: {
                // TODO - refactor out
                url: "/exist/rest/db/treeeditor/flatxml1.xml"
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
                    // get rid of the remove item
                    create: false,
                    rename: false
                }
            }
        },
        
        
        data: {
            type: "xml_flat",
            opts: {
                // TODO - refactor out
                url: "/exist/rest/db/treeeditor/flatxml2.xml"
            }
        },
        
        
        rules: {
            clickable: "all",
            multiple: "on",
            droppable: "all",
            deletable: "all",
            
            
            
            draggable: "all",
            creatable: "all",
            dragrules: "all",
            multitree: "all"
        }
    });
});