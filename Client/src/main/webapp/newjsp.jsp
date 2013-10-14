<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Upload Media from Client</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">
        <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    </head>
    <body>
        <script>
            function prependToForm(form) {
                var owner = $("#owner").val();
                var access = $("#access").val();
                
                var data = {
                    "owner": owner,
                    "access":access
                };

                var js = $(form).find("#json");

                //var jsonObj = "{a:b, c:d, foo:bar}"

                // $(js).val("{a:b, c:d, foo:bar}"+owner);
                 $(js).val("{owner:" + owner + 
                         ",access:" + access + 
                         "}");
               // $(js).val($data); 


                return true;
            }
        </script>
        <h1>File Upload</h1>
        <form action="http://localhost:8080/MediaServerRestful/resources/file/upload" 
              method="post" enctype="multipart/form-data" onsubmit="prependToForm($(this))">
            <p>
                Select a file : <input id="file" type="file" name="file" /> <br/>

                Owner : <input id="owner" type="text" name="owner" value="ingimar" /><br/>
                Public/Private : <input id="access" type="text" name="access" value="private" />

                <input type="hidden" id="json" name="json" value="" />  

                <input type="submit" value="Upload It" />
        </form>
    </body>
</html>