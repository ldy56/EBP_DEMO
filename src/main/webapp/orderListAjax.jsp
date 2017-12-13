<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                   <tr>
                       <th width="15%" height="40">序&nbsp;&nbsp;&nbsp;号</th>
                       <th width="30%">描述</th>
                       <th width="15%">单价</th>
                       <th width="20%">购票张数</th>
                       <th width="20%">金额（元）</th>

                   </tr>
	                <c:forEach items="${orderList }" var="order" varStatus="st">
	                    <tr  align="center" height="15px">
	                        <td>${order.lid }</td>
	                        <td>${order.descs }</td>
	                        <td>${order.price }</td>
	                        <td>${order.quantity }</td>
	                        <td>${order.amount }</td>
	                    </tr>
	                    
	                </c:forEach>
                </table>

           