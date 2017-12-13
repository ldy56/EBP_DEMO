/**
 * Created by Administrator on 2017/3/11.
 */
var t=document.getElementById("t");
function tianjia(m,j,n) {
	
    if(m.innerHTML=="-") {
        var deltrow=document.getElementById("td"+j);
        deltrow.innerHTML="";
        document.getElementById("jian"+j).innerHTML="+";
    }else{
        var deltrow=document.getElementById("td"+j);
        
        $.ajax({
        	url:"queryOrderListByOid",
        	data:"oid="+n,
        	type:"post",
        	datatype:"html",
        	success:function(data){
        		deltrow.innerHTML=data;
        	}
        });
        
        document.getElementById("jian"+j).innerHTML="-";
    }
}
