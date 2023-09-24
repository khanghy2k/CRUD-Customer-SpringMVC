<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="frm" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Thêm mới khách hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        /* Thêm style border và border radius cho form */
        form {
            border: 1px solid #ccc; /* Độ rộng và màu sắc của border */
            border-radius: 5px; /* Độ cong của border */
            padding: 20px; /* Khoảng cách bên trong form */
            max-width: 400px; /* Độ rộng tối đa của form */
            margin: 0 auto; /* Căn giữa form trên trang */
        }

        /* Thêm style cho các input fields */
        input[type="text"],
        input[type="number"],
        input[type="date"],
        input[type="file"],
        button[type="submit"] {
            width: 100%; /* Độ rộng của input và nút */
            padding: 10px; /* Khoảng cách bên trong input và nút */
            margin-bottom: 10px; /* Khoảng cách giữa các input fields và nút */
            border: 1px solid #ccc; /* Độ rộng và màu sắc của border cho input */
            border-radius: 3px; /* Độ cong của border cho input */
        }

        /* Thêm style cho nút "Thêm" */
        button[type="submit"] {
            background-color: black; /* Màu nền của nút */
            color: white; /* Màu chữ của nút */
            font-weight: bold; /* Độ đậm của font chữ */
            border: none; /* Loại bỏ border của nút */
            border-radius: 5px;
            cursor: pointer; /* Biến con trỏ thành mũi tên khi di chuyển qua nút */
        }

        /* Thêm style khi di chuột qua nút "Thêm" */
        button[type="submit"]:hover {
            background-color: #0056b3; /* Màu nền thay thế khi di chuột qua nút */
        }
        h1 {
            text-align: center;
        }
    </style>
</head>
<body>
<h1>Thêm mới khách hàng</h1>
<frm:form action="/customer/create" method="post" enctype="multipart/form-data" modelAttribute="customer">
    <frm:input path="name" id="txtName" /><br>
    <frm:input path="age" id="txtAge" /><br>
    <frm:input path="birthday" id="txtBirthday" type="date" /><br>
    <frm:input path="image" id="txtImage" type="file" /> <br>
    <button type="submit">Thêm</button>
</frm:form>
</body>
</html>
