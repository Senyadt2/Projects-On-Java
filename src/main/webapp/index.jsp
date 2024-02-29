<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert into here </title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</head>
<body>
<div class="container">
<div class="row">
<div class="col-md-6 offset-md-3 mt-5">
<div class="card">
<div class="card-header text-center fs-3">
Emp Register
</div>
<% String msg = (String)session.getAttribute("msg");
    if(msg != null){
    %>
    <p class="text-center fs-4"><%= msg %>  </p>
    <%
    }
    session.removeAttribute("msg");
 %>
<div class="card-body">
<form method="POST" action="register">
<div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Name</label>
    <input type="text" class="form-control" name="name">
    <div id="emailHelp" class="form-text"></div>
  </div>
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Email</label>
    <input type="email" class="form-control" name="email" >
    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
  </div>

  <div class="mb-3">
      <label for="exampleInputEmail1" class="form-label">salary</label>
      <input type="text" class="form-control" name="salary" >
    </div>
     <label for="exampleInputEmail1" class="form-label">Department</label>
        <input type="text" class="form-control"  name="department" >
        <div id="emailHelp" class="form-text"></div>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">password</label>
        <input type="password" class="form-control" name="password">
      </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>
</div>
</div>
</div>
</div>
</div>
</body>
</html>