<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <body>
        <h1>JAX-RS File Upload Example</h1>
        <form action="media/upload-file" method="post" enctype="multipart/form-data">
            <p>
                Owner : <input type="text" name="owner" /></br>
            </p>
             <p>
                private/public : <input type="text" name="access" /></br>
            </p>
            <p>
                Choose the file : <input type="file" name="selectedFile" />
            </p>
            <input type="submit" value="Upload" />
        </form>

    </body>
</html>