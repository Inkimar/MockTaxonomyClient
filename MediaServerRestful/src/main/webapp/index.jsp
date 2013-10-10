<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Upload Media</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">
    </head>
    <body>
        <h1>File Upload </h1>
        <form action="resources/file/upload" method="post" enctype="multipart/form-data">
            <p>
                Select a file : <input type="file" name="file" /> <br/>
                information : <input type="text" name="information" value="abc" />
            </p>
            <input type="submit" value="Upload It" />
        </form>
    </body>
</html>
