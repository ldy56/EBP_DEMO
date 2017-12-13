var tbs=document.getElementById("tbss");
var oli=tbs.getElementsByTagName("li");
for(var i=0;i<oli.length;i++){
    oli[i].index=i;

    oli[i].onclick=function () {
        for(var i=0;i<oli.length;i++){
            oli[i].className="";
        }
        this.className="clk"
    };
}
