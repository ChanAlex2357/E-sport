function activeCurrentPage( idLink ){
    var current_page = document.getElementById(idLink);
        current_page.classList.add("border-bottom");
        current_page.classList.add("active");
}
function collapseAction(){
    var collapser = document.getElementsByClassName("collapser");
    for (let index = 0; index < collapser.length; index++) {
        const element = collapser[index];
        element.addEventListener("click" , function() {
            var data = document.getElementById(element.dataset.target.replace("#" , ""));
    
            if( element.ariaExpanded === "false" ){
                element.ariaExpanded = "true";
                element.classList.remove("collapsed");
                data.classList.add("collapsing");
                data.classList.remove("collapsing");
                data.classList.add("show");
            }
            else{
                element.ariaExpanded = "false";
                element.classList.add("collapsed");
                data.classList.add("collapsing");
                data.classList.remove("collapsing");
                data.classList.remove("show");
            }
        });
    };
}
window.addEventListener("load" , function(){ collapseAction() ;});