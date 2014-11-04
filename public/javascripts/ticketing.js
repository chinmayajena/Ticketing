$(document).ready(function() {
	
	
	$(".tickstatus").change(function (){
		
		if($(".tickstatus").val()!="default"){
			var reqData = {status: $(".tickstatus").val() }
			 $.ajax({
		         async: false,
		         type :  "GET",
		         timeout:300000,
		         dataType: 'json',
		         contentType : 'application/json; charset=utf-8',
		         data:reqData,
		         url  :"/loadtickets",
		        
		         success: function(data){
		        	 $.each(data, function(index, row) {
		        		 	
			            	  $(".tickid").append("<option value="+row+">"+row+"</option>");
			              });
			             },
		         error: function(jqXHR, textStatus, errorThrown){
		                 alert("Error");
		         		}
			 	});
		}
		
	});
	
	$("#btnSearch").click(function(){
		if($(".tickid").val()!="default"){
			var reqData = {id:$(".tickid option:selected").text()};
				
			 $.ajax({
		         async: false,
		         type :  "GET",
		         timeout:300000,
		         dataType: 'json',
		         contentType : 'application/json; charset=utf-8',
		         data:reqData,
		         url  :"/showticket",
		        
		         success: function(data){
		        	 
		        	 if(data.status=="closed"){
		        		 $("#context").find('option[value="'+data.area+'"]').attr("selected",true);
		        		 $("#context").attr("disabled","disabled");
			        	 $("#ticketId").val(data.id);
			        	 $("#InputName").val(data.custname);
			        	 $("#InputAssigned").val(data.assign);
			        	 $("#InputMessage").val(data.comment);
			        	 $("#status").find('option[value="'+data.status+'"]').attr("selected",true);
 
		        		 
		        	 }else{
		        	 $("#context").find('option[value="'+data.area+'"]').attr("selected",true);
		        	 $("#ticketId").val(data.id);
		        	 $("#InputName").val(data.custname);
		        	 $("#InputAssigned").val(data.assign);
		        	 $("#InputMessage").val(data.comment);
		        	 $("#status").find('option[value="'+data.status+'"]').attr("selected",true);

		        	 }
		        	 
		        	 
		        	 
		        	 //$('select[name="options"]').find('option[value="'+data.context+'"]').attr("selected",true);
		        	 /*$.each(data, function(index, row) {
		        		 	alert(row);
			            	  
			              });*/
			             },
		         error: function(jqXHR, textStatus, errorThrown){
		                 alert("Error");
		         		}
			 	});
			
			}
		});


});

