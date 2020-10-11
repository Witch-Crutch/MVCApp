<#ftl encoding="UTF-8"/>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Услуги</title>
</head>
<body>
<table border="1">
    <tr>
        <td>name</td>
        <td>description</td>
        <td>price</td>
        <td>category</td>
        <td>image</td>
    </tr>
    <#list products as product>
        <tr>
            <td>${product.getName()}</td>
            <td>${product.getDescription()}</td>
            <td>${product.getPrice()}</td>
            <td>${product.getCategory().getName()}</td>
            <td>${product.getImage()}</td>
        </tr>
    </#list>
</table>
</body>
</html>