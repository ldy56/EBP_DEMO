/**
 * Created by Administrator on 2017/3/14.
 */
var t=document.getElementById("t");
var i=3;
function tianjia() {
    window.location="adminaddTicket.jsp";

}

//点击修改，在页面中进行修改票据
function xiugai(m,id) {
    var upertr=document.getElementById("tr"+m);
    var tds=upertr.cells;
    var tdval=tds[1].innerHTML;
    var tdval1=tds[2].innerHTML;
    var tdval2=tds[3].innerHTML;
    var tdval3=tds[4].innerHTML;
    var tdval4=tds[5].innerHTML;
//    var tdval5=tds[6].innerHTML.trim();

    tds[1].innerHTML="<input id='input1"+m+"' type='text' required value='"+tdval+"'>";
    tds[2].innerHTML="<input id='input2"+m+"' type='date' required value='"+tdval1+"'>";
    tds[3].innerHTML="<input id='input3"+m+"' type='number' required min='0' value='"+tdval2+"'>";
    tds[4].innerHTML="<input id='input4"+m+"' type='number' required min='0' value='"+tdval3+"'>";
    tds[5].innerHTML="<input id='input5"+m+"' type='text' required value='"+tdval4+"'>";
//    tds[6].innerHTML="<input id='input"+m+"' type='text' value='"+tdval5+"'>";
   
    tds[8].innerHTML="<input class='btnc' type='button' value='确认' onclick='return queren("+m+","+id+")'>";
}

//点击确认按钮，修改联赛信息！
function queren(m,id) {
    var upertr=document.getElementById("tr"+m);
    var tds=upertr.cells;
    var name=document.getElementById("input1"+m).value;
    if(name==""){
    	alert("请输入电影名称！");
    	return false;
    }
    
    var time=document.getElementById("input2"+m).value;
    if(time==""){
    	alert("请输入放映时间！");
    	return false;
    }
    
    var amount=document.getElementById("input3"+m).value;
    
    if(amount=="" || parseInt(amount)<0){
    	alert("请正确输入总数！");
    	return false;
    }
    
    var balance=document.getElementById("input4"+m).value;
    if(balance=="" || parseInt(balance)<0){
    	alert("请正确输入余票！");
    	return false;
    }
    
    if(parseInt(balance)>parseInt(amount)){
    	alert("余票数不能大于总数！");
    	return false;
    }
    
    var price=document.getElementById("input5"+m).value;

    if(price!=parseFloat(price) || parseInt(price)<0){
    	alert("请正确输入单价！");
    	return false;
    }
    
    var data="tid="+id+"&descs="+name+"&startTime="+time+"&" +
    		"amount="+amount+"&balance="+balance+"&price="+price;
//    alert(data);
    $.ajax({
    	url:"updateTicketAjax",
    	type:"post",
    	dataType:"text",
    	data:data,
    	success:function(data){
//    		alert(data);
    		if(data=="ok"){
    			alert("修改成功!");
    			 	tds[1].innerHTML=name;
    			    tds[2].innerHTML=time;
    			    tds[3].innerHTML=amount;
    			    tds[4].innerHTML=balance;
    			    tds[5].innerHTML=price;
    			    tds[8].innerHTML="<input type='button' class='btnc' value='删除' onclick='return shanchu("+id+")'><input class='btnc' type='button' value='修改' onclick='xiugai("+m+","+id+")'>";
    			    return true;
    		}else{
    			return false;
    		}
    	}
    });
    
   
}

function queryByDate(){ //根据时间查询票据
	var begin=document.getElementById("begin").value;
    var end=document.getElementById("end").value;
    window.location="queryTickets?begin="+begin+"&end="+end;
}

//根据ID修改票据状态信息
function upTicketStatus(id){
	var stat=0;
	if($("#stat_tick"+id).text().trim()=="已停售"){
		stat=1;
	}
//	alert($("#stat_tick"+id).text().trim());
//	alert(id+"=="+stat);
	if(confirm("确认修改状态？")){
		$.ajax({
			url:"updateStatusById",
			type:"post",
			data:"id="+id+"&status="+stat,
			dataType:"text",
			success:function(data){
				if(data=="ok"){
					if(stat==0){
						$("#stat_tick"+id).html("<font color='red'>已停售</font>");
					}else{
						$("#stat_tick"+id).text("售票中");
					}
				}
			}
		});
	}
	
	return false;
}

function shanchu(id) {	//删除票据
    if(confirm("确认删除？")){
    	alert("删除成功！");
    }
    return false;
}

//选择图片以ajax方式上传文件
function fileSelected(id){
	var formData = new FormData($("#form_face"+id)[0]);
	$.ajax({
		url:"saveTicketPhoto?tid="+id,
		type:"post",
		processData: false,
		contentType: false,
		data:formData,
		dataType:"text",
		success:function(data){
			if(data=="ok"){
				$("#photo"+id).attr("src","../getPhotoByTid?tid="+id);
			}
		}
		
	});
};
