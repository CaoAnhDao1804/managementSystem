
<%@page import="java.util.ArrayList"%>
<%@page import ="model.bean.Classes"%>;
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/inc/dashboard.jsp" %>  
<div class="content-wrapper py-3">
  <div class="container-fluid">
    <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i>
          Lịch học của bạn
        </div>
        <script type="text/javascript">
            $(document).ready(function(){
                $(document).on('change', '.checkall, .checkitem', function(){
                    var $_this = $(this);
                    document.getElementById("deleteall").style.display = "block";
                    if($_this.hasClass('checkall')){
                        $('.checkitem').prop('checked', $_this.prop('checked'));
                    }else{
                        var $_checkedall = true;
                        $('.checkitem').each(function(){
                            if(!$(this).is(':checked')){
                                $_checkedall = false;
                            }
                            $('.checkall').prop('checked', $_checkedall);
                        });
                    }
                    var $_uncheckedall = true;
                    $('.checkitem').each(function(){
                        if($(this).is(':checked')){
                            $_uncheckedall = false;
                        }
                        if($_uncheckedall){
                            document.getElementById("deleteall").style.display = "none";
                        }else{
                            document.getElementById("deleteall").style.display = "block";
                        }
                    });
                });
            });
        </script>
        <div class="card-body">
          <div class="table-responsive">
            <form action=""  method="post">
                <input style="display: none; margin-left: 10px; margin-bottom: 10px; color: red" id="deleteall" type="submit" value="Delete">
                <table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">
                  <thead>
                    <tr>
                       <th>No.</th>
                      <th width="20%">Class  </th>
                      <th width="20%">Room </th>
                      <th width="20%">Trainer </th>
                      <th>Time </th>
                      <th width="20%">Day</th>
                      <th width="20%">Count Lession </th>
                      <th width="20%">Room ID</th>
                      <th>Action</th>
                      
                    </tr>
                  </thead>
                  <tbody>
                  <%
                
                  	ArrayList<Classes> listClass = (ArrayList<Classes>) request.getAttribute("listClass");
                  	int i=0;
                  	for (Classes classes : listClass){
                  		i+=1;
                  	System.out.print(classes.getClassId());
                  %>
                  
                  <tr>
                  <td><%= i %></td>
                  <td><%= classes.getClassId() %></td>
            
                  <td><%= classes.getRoomId()%></td>
                  <td><%= classes.getTrainerId() %></td>
                  <td><%= classes.getTimeOfDate() %></td>
                  <td><%= classes.getDateOfWeek() %></td>
                  <td><%= classes.getCountLession() %></td>
                  <td><%= classes.getRoomId() %></td>
                  <td> <a href="/managementSystem/trainee/list?class_id=<%= classes.getClassId() %>"> See list trainee of class </a>
                  </tr>
                  <%
                  	}
                  		
                  
                  %>
                  </tbody>
                  
                </table>
            </form>
          </div>
        </div>
        <div class="card-footer small text-muted">
          Updated yesterday at 11:59 PM
        </div>
      </div>
    </div>
  </div>
<%@include file="/templates/inc/footer.jsp" %> 
