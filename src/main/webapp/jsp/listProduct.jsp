<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" rel="stylesheet"/>
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <title>BLOW JOB</title>
    </head>

    <body>
        <nav class="light-teal-team2 lighten-1" role="navigation">
            <div class="nav-wrapper container">
                <a id="logo-container" href="#" class="brand-logo">BLOW JOB</a>
            </div>
        </nav>

        <!-- IMPORT FILE -->
        <div class="section no-pad-bot" id="index-banner">
            <div class="container">
                    <form:form method="GET"  action="process" id="form">
                        <div class="center">
                            <button class="btn waves-effect waves-light" type="submit" name="action" id ="submitBtn">
                                Process <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>

        <!-- PRELOADER -->
        <div class="container" id="progress" style="visibility: hidden">
            <div class="progress">
                <div class="indeterminate"></div>
            </div>
        </div>

        <!-- MESSAGE -->
        <div class="container">
            <div class="section">
                <div class="row">
                    <c:forEach var="error" items="${errorExceptionList}">
                        <h5 class="left red-text">${error.errMsg}</h5>
                    </c:forEach>
                </div>
            </div>
        </div>

        <br><br>
        <footer class="page-footer">
            <div class="footer-copyright">
                <div class="container">
                    Create by Team DreamVL
                </div>
            </div>
        </footer>

        <!--  Scripts-->
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
        <script src="js/materialize.js"></script>
        <script src="js/init.js"></script>
        <script type="text/javascript">
            function myFunction() {
                document.getElementById("submitBtn").disabled = true;
                document.getElementById("progress").style.visibility="visible";
                document.getElementById("message").style.visibility="visible";

                $.ajax({
                    type: "POST",
                    dataType: formdata,
                    url: "${home}/upload",
                    data: $("#form").serialize(), //serializes the form's elements.
                    success: function(data)
                    {
                        alert('A success');// show response from the php script.
                    }
                  });
              }

            $(function () {
                $('.open-popup-link').click(function () {
                    dialog.dialog("open");
                });
                var dialog = $("#popup").dialog({
                    autoOpen: false,
                    height: $(window).height() - 20,
                    width: 1024,
                    modal: true,
                    open: function (event, ui) {
                        // Will fire when this popup is opened
                        // jQuery UI Dialog widget
                        $('#table0').tablesorter({
                            theme: 'blue',
                            headerTemplate: '{content} {icon}', // Add icon for various themes
                            widgets: ['zebra', 'filter', 'stickyHeaders'],
                            widgetOptions: {
                                // jQuery selector or object to attach sticky header to
                                stickyHeaders_attachTo: '#popup',
                                stickyHeaders_offset: 0,
                                stickyHeaders_addCaption: true
                            }
                        });
                    }
                });
            });
        </script>
    </body>
</html>
