<%@ page import="com.turbulence6th.asciiart.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><%= Constants.TITLE %></title>
  </head>

  <body>
    <form action="${pageContext.request.contextPath}/image" method="post" enctype="multipart/form-data">
        <table>
            <thead>
                <tr style="font-weight: bold">
                    <td>Setting</td>
                    <td>Default</td>
                    <td>Input</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Scale</td>
                    <td><%= Constants.DEFAULT_SCALE %></td>
                    <td><input type="number" name="<%= Constants.SCALE %>" step="1"></td>
                </tr>
                <tr>
                    <td>Font Size</td>
                    <td><%= Constants.DEFAULT_FONT_SIZE %></td>
                    <td><input type="number" name="<%= Constants.FONT_SIZE %>" step="1"></td>
                </tr>
                <tr>
                    <td>Brightness</td>
                    <td><%= Constants.DEFAULT_BRIGHTNESS %></td>
                    <td><input type="number" step="0.01" name="<%= Constants.BRIGHTNESS %>"></td>
                </tr>
                <tr>
                    <td>Black</td>
                    <td><%= Constants.BLACK_DEFAULT %></td>
                    <td><input type="text" name="<%= Constants.BLACK %>" maxlength="1"></td>
                </tr>
                <tr>
                    <td>Blue</td>
                    <td><%= Constants.BLUE_DEFAULT %></td>
                    <td><input type="text" name="<%= Constants.BLUE %>" maxlength="1"></td>
                </tr>
                <tr>
                    <td>Green</td>
                    <td><%= Constants.GREEN_DEFAULT %></td>
                    <td><input type="text" name="<%= Constants.GREEN %>" maxlength="1"></td>
                </tr>
                <tr>
                    <td>Cyan</td>
                    <td><%= Constants.CYAN_DEFAULT %></td>
                    <td><input type="text" name="<%= Constants.CYAN %>" maxlength="1"></td>
                </tr>
                <tr>
                    <td>Red</td>
                    <td><%= Constants.RED_DEFAULT %></td>
                    <td><input type="text" name="<%= Constants.RED %>" maxlength="1"></td>
                </tr>
                <tr>
                    <td>Magenta</td>
                    <td><%= Constants.MAGENTA_DEFAULT %></td>
                    <td><input type="text" name="<%= Constants.MAGENTA %>" maxlength="1"></td>
                </tr>
                <tr>
                    <td>Yellow</td>
                    <td><%= Constants.YELLOW_DEFAULT %></td>
                    <td><input type="text" name="<%= Constants.YELLOW %>" maxlength="1"></td>
                </tr>
                <tr>
                    <td>White</td>
                    <td><%= Constants.WHITE_DEFAULT %></td>
                    <td><input type="text" name="<%= Constants.WHITE %>" maxlength="1"></td>
                </tr>
            </tbody>
        </table> <br>

        Image: <input type="file" name="<%= Constants.FILE %>" accept="image/*" required> <br> <br>

        <input type="submit">
    </form>
  </body>

</html>