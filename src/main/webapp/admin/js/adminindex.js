/**
 * Created by Administrator on 2017/3/9.
 */
var div1=document.getElementById("l_bottom");
//    console.log(div1)
var aLi=div1.getElementsByTagName("li");
for(var i=0;i<aLi.length;i++){
    aLi[i].index=i;
    aLi[i].onclick=function () {
        for(var i=0;i<=aLi.length;i++) {
//                alert(this.index)
            if (this.index == 0) {
                self.location="adminindex.html"
            }else if(this.index==1){
                self.location="adminTicket.html"
            }else if(this.index==2){
                self.location="adminUser.html"
            }else if(this.index==3){
                self.location="adminOder.html"
            }else if(this.index==4){
                self.location="adminindexlogin.html"
            }
        }
    }
}
var timer=document.getElementById("timer");
console.log(timer)
var now= new Date();
var month=(now.getMonth()+1);
if(month<=10){
    month='0'+month;
}
var minutes=now.getMinutes();
if(minutes<=10){
    minutes='0'+minutes;
}
var Hours=now.getHours();
if(Hours<=10){
    Hours='0'+Hours;
}
var data=now.getDate();
if(data<=10){
    data='0'+data;
}
var time=1900+now.getYear()+"-"+month+"-"+data+"&nbsp;&nbsp; "+Hours+"&nbsp;:&nbsp;"+minutes;
document.getElementById("timer").innerHTML=time
