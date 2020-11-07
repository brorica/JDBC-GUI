<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ page import = "java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script>
// 이 코드 안 본 눈 삽니다
	function check(box){
		var names = [];
		const name = String(box.value);
		// 0 ~ 7 까지는 검색 항목 checkBox여서 패쓰
		for(var i = 8; i < document.getElementsByTagName('input').length; i++){
			if( document.getElementsByTagName('input')[i].checked == true ) {
				var checkedName = document.getElementsByTagName('input')[i].value;
				
				for(var j = 0 ; j < document.getElementsByTagName('td').length; j++) {
					if(document.getElementsByTagName('td')[j].attributes.length > 2) {
						if(checkedName === document.getElementsByTagName('td')[j].attributes[1].value) {
							names.push(document.getElementsByTagName('td')[j].attributes[2].value)
						}
					}
		       	}
			}
		}
	    const who = document.getElementById('who');
		who.innerHTML = names;
	}
</script>
</head>
<body>

<form action="index.jsp">
<h5> 검색 범위 </h5>
<select name="department">
    <option value="All">전체</option>
    <option value="Research">Research</option>
    <option value="Administration">Administration</option>
    <option value="Headquarters">Headquarters</option>
</select>
<p>
<h5> 검색 항목 </h5>
<input type="checkbox" checked="checked" name="Name"> Name
<input type="checkbox" checked="checked" name="ssn"> ssn
<input type="checkbox" checked="checked" name="Bdate"> Bdate
<input type="checkbox" checked="checked" name="Address"> Address
<input type="checkbox" checked="checked" name="sex"> sex
<input type="checkbox" checked="checked" name="salary"> salary	
<input type="checkbox" checked="checked" name="supervisor"> supervisor
<input type="checkbox" checked="checked" name="Dname"> department
<button type="submit" name="method" value="search"> 검색 </button>
<table width="1000" border=1>
<%
	Statement state;
	PreparedStatement preState;
	ResultSet result;
	Connection connect = null;                                        // null로 초기화 한다.
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch(ClassNotFoundException jdbcDriverError) {
		System.out.println("jdbc Driver Load error : " + jdbcDriverError);
		jdbcDriverError.printStackTrace();
	}
	// connect
	try {
		String user = "root";
		String password = "123456";
		String database = "mydb";
		String url = "jdbc:mysql://localhost:3306/" + database + "?serverTimezone=UTC";
		String sql = "";
		String selects[];
		connect = DriverManager.getConnection(url,user,password);
		state = connect.createStatement();
		
		if(request.getParameter("department") != null)
		{
			String range = request.getParameter("department");
			String method = request.getParameter("method");
			// salary 업데이트
			if(method.equals("update"))
			{
				selects = request.getParameterValues("select");
				String newSalary = request.getParameter("newSalary");
				for(String val : selects)
				{
					String update = "UPDATE employee SET Salary=? where ssn=?";
					preState = connect.prepareStatement(update);
					preState.setString(1, newSalary);
					preState.setString(2, val);
					preState.executeUpdate();
				}
			}
			// employee 삭제
			else if(method.equals("delete"))
			{
				selects = request.getParameterValues("select");
				String test0 = "SET foreign_key_checks = 0";
				String test1 = "SET foreign_key_checks = 1";
				state.executeQuery(test0);
				for(String val : selects)
				{
					String delete = "DELETE FROM employee where ssn=?";
					preState = connect.prepareStatement(delete);
					preState.setString(1, val);
					preState.executeUpdate();
					
				}
				state.executeQuery(test1);
			}
			// 검색
			else if(method.equals("search"))
			{
				String column = "";
				// name 설정 때문에 이름은 무조건 받게 함
				sql = "Select concat(e1.Fname, e1.Lname) as Name,";
			    if(request.getParameter("Name") != null)
			    {
			    	column += "Name ";
			    }
			    if(request.getParameter("ssn") != null)
			    {
			    	column += "ssn ";
			    	sql += " e1.ssn,";
			    }
			    if(request.getParameter("Bdate") != null)
			    {
			    	column += "Bdate ";
			    	sql += " e1.Bdate,";
			    }
			    if(request.getParameter("Address") != null)
			    {
			    	column += "Address ";
			    	sql += " e1.Address,";
			    }
			    if(request.getParameter("sex") != null)
			    {
			    	column += "sex ";
			    	sql += " e1.sex,";
			    }
			    if(request.getParameter("salary") != null)
			    {
			    	column += "salary ";
			    	sql += " e1.salary,";
			    }
			    if(request.getParameter("supervisor") != null)
			    {
			    	column += "supervisor ";
			    	sql += " concat(e2.Fname, e2.Lname) as supervisor,";
			    }
			    if(request.getParameter("Dname") != null)
			    {
		    		column += "Dname ";
		    		sql += " Dname,";
			    }
			    sql = sql.substring(0, sql.length() - 1);
			    sql += "  from department, employee as e1 left join employee as e2 on e1.Super_ssn = e2.Ssn where Dnumber=e1.dno";
			    
			    if(!(range.equals("All"))){
					sql += " AND DNAME LIKE \"" + range + "\"";
				}
			    
			    result = state.executeQuery(sql);
%>
		   		<th> 선택 </th>
<%
				// 여기는 칼럼명 적는 곳
   		    	if(column.indexOf("Name") != -1)
		    	{
		    		%>	<th> Name </th> <%
		    	}
		    	if(column.indexOf("ssn") != -1)
		    	{
		    		%>	<th> ssn </th>	<%
		    	}
		    	if(column.indexOf("Bdate") != -1)
		    	{
		    		%>
		    		<th> BDATE </th>
		    		<%
		    	}
		    	if(column.indexOf("Address") != -1)
		    	{
		    		%>
		    		<th> Address </th>
		    		<%
		    	}
		    	if(column.indexOf("sex") != -1)
		    	{
		    		%>
		    		<th> sex </th>
		    		<%
		    	}
		    	if(column.indexOf("salary") != -1)
		    	{
		    		%>
		    		<th> salary </th>
		    		<%
		    	}
		    	if(column.indexOf("supervisor") != -1)
		    	{
		    		%>
		    		<th> supervisor </th>
		    		<%
		    	}
		    	if(column.indexOf("Dname") != -1)
		    	{
		    		%>	<th> Department </th> 	<%
		    	}
%>
<%			
				int num=0;
				//여기는 데이터 나오는 곳
			    while(result.next()){
			    	num++;
			    	String Name = "";
			    	String ssn = result.getString("ssn");;
			    	String Bdate = "";
			    	String Address = "";
			    	String sex = "";
			    	String salary = "";
			    	String supervisor = " ";
			    	String Dname = "";
%>
	    		<tr>
    				<td> <input type="checkbox" name="select" onClick="check(this)" value="<%=ssn%>"> </td>
<%
		    	if(column.indexOf("Name") != -1)
		    	{
		    		Name = result.getString("Name");
		    		%>
		    		<td width="100" name="<%=ssn%>" value="<%=Name%>"> <%=Name%> </td>
		    		<%
		    	}
		    	if(column.indexOf("ssn") != -1)
		    	{
		    		ssn = result.getString("ssn");
		    		%>
		    		<td width="100"><%=ssn%></td>
		    		<%
		    	}
		    	if(column.indexOf("Bdate") != -1)
		    	{
		    		Bdate = result.getString("Bdate");
		    		%>
		    		<td width="200"><%=Bdate%></td>
		    		<%
		    	}
		    	if(column.indexOf("Address") != -1)
		    	{
		    		Address = result.getString("Address");
		    		%>
		    		<td width="300"><%=Address%></td>
		    		<%
		    	}
		    	if(column.indexOf("sex") != -1)
		    	{
		    		sex = result.getString("sex");
		    		%>
		    		<td width="100"><%=sex%></td>
		    		<%
		    	}
		    	if(column.indexOf("salary") != -1)
		    	{
		    		salary = result.getString("salary");
		    		%>
		    		<td width="100"><%=salary%></td>
		    		<%
		    	}
		    	if(column.indexOf("supervisor") != -1)
		    	{
		    		supervisor = result.getString("supervisor");
		    		if(supervisor == null){
		    		%>
		    			<td width="150"> </td>
		    		<%
		    		}else{
		    		%> 
		    			<td width="100"><%=supervisor%></td>
		    		<%
		    		}
		    	}
		    	if(column.indexOf("Dname") != -1)
		    	{
		    		Dname = result.getString("Dname");
		    		%>
		    		<td width="100"><%=Dname%></td>
		    		<%
		    	}
		    	
%>
				</tr>
<%
		   }
			%>
			<p>
				인원 수 : <%=num %>
			<p>
				선택한 직원 : <div id=who> </div>
<%
	    }
	}
	}catch(SQLException connectnectError) {
		System.out.println("connectnect error : " + connectnectError);
		connectnectError.printStackTrace();
	}
	

%>
<p>
새로운 Salary : <input type="text" name="newSalary">
<button type="submit" name="method" value="update"> update </button>
선택한 데이터 삭제
<button type="submit" name="method" value="delete"> delete </button>
</table>
</form>

</body>
</html>