<%@page import="dao.impl.AccountDAOImpl"%>
<%@page import="Entity.Account"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Chat Employee</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/inbox.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style type="text/css">
            #innerdiv {
                width: auto;
                word-break: break-all;
            }
        </style>
    </head>
    <body onload="sendMessage()">

        <div class="messaging">
            <div class="inbox_msg">
                <div class="inbox_people">
                    <div class="headind_srch">
                        <div class="recent_heading">
                            <h4>Recent</h4>
                        </div>
                        <div class="srch_bar">
                            <div class="stylish-input-group">
                                <form class="sidebar-search-form" action="" method="POST">
                                    <input oninput="searchByName(this,${sessionScope.Account.accountid})" name="searchKey" type="text" placeholder="Search here..." value="">
                                </form>
                            </div>
                        </div>
                    </div>
                    <!--List Of User-->
                    <div class="inbox_chat scroll" id="cha">
                        <%
                            List<Account> listAccByEm = (List<Account>) request.getAttribute("listAccByEm");
                            List<String> list = (List<String>) request.getAttribute("listMess");
                            Account acc = (Account) session.getAttribute("Account");
                            Integer CustomerID = (Integer) request.getAttribute("CustomerID");
                            AccountDAOImpl daoAccountDao = new AccountDAOImpl();
                            Account accCus = daoAccountDao.getAccountByIDA(CustomerID);
                        %>
                        <%if (listAccByEm.size() != 0) {%>
                        <%for (Account e : listAccByEm) {%>
                        <div class="chat_list ">
                            <div class="chat_people">
                                <div class="chat_img"> <img src="<%=e.getImageURL()%>" alt="img"> </div>
                                <div class="chat_ib">
                                    <h5> <a href="chat?do=chatEm&CustomerID=<%=e.getAccountid()%>"> <%=e.getDisplayname()%> </a>  <span class="chat_date">Dec 25</span></h5>

                                    <p><%=list.get(list.size() - 1).substring(Integer.toString(e.getAccountid()).length(), list.get(list.size() - 1).length() - Integer.toString(CustomerID).length())%>.</p>

                                </div>
                            </div>
                        </div>
                        <%}%>

                        <%}%>
                    </div>


                </div>
                <div class="mesgs">
                    <div class="msg_history" id="croll">

                        <%if (list.size() != 0) {%>
                        <% for (String elem : list)

                                if (elem.startsWith(String.valueOf(acc.getAccountid()))) {%>
                        <div id="innerdiv" class="outgoing_msg">
                            <div style="width:50%; ; word-break:break-all;  " class="sent_msg">
                                <p><%= elem.substring(Integer.toString(acc.getAccountid()).length(), elem.length() - Integer.toString(CustomerID).length())%></p>
                                <span class="time_date"> 11:01 AM    |    June 9</span> </div>
                        </div>

                        <%} else {%> 

                        <div class="incoming_msg">
                            <div class="incoming_msg_img"> <img src="<%=accCus.getImageURL()%>" alt="img"> </div>
                            <div id="innerdiv" class="received_msg">
                                <div class="received_withd_msg">
                                    <p><%=elem.substring(Integer.toString(CustomerID).length(),  elem.length() - Integer.toString(acc.getAccountid()).length())%></p>
                                    <span class="time_date"> 11:01 AM    |    June 9</span></div>
                            </div>
                        </div>

                        <%}%>
                        <%}%>

                    </div>
                    <div class="type_msg">
                        <div class="input_msg_write">
                            <input value="<%=acc.getAccountid()%>" id="textMessage"  type="text" class="write_msg" placeholder="Type a message" />
                            <button onclick="sendMessage()" class="msg_send_btn" type="button"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--script chat-->
        <script type="text/javascript">
            var websocket = new WebSocket("ws://localhost:8080/home/chatRoomServer");
            websocket.onopen = function (message) {
                processOpen(message);
            };
            websocket.onmessage = function (message) {
                processMessage(message);
            };
            websocket.onclose = function (message) {
                processClose(message);
            };
            websocket.onerror = function (message) {
                processError(message);
            };

            function processOpen(message) {

//               $('#cha').append('<div class="chat_list active_chat"><div class="chat_people"><div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div><div class="chat_ib"> <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5><p>'+message.data+'.</p></div></div></div>');

            }
            function processMessage(message) {
                console.log(message);
                //var mess = message.data.replace("${sessionScope.Account.accountid}: ", "");


                if (message.data.startsWith("${sessionScope.Account.accountid}") && message.data.endsWith("${CustomerID}")) {
                    var mess = message.data.substring(<%=Integer.toString(acc.getAccountid()).length()%>, message.data.length - <%=Integer.toString(CustomerID).length()%>);
                    let length = mess.length;
                     console.log(mess);
                     console.log(length);
                    if (length == 1) {
                        $('#croll').append('<div class=""><div style="width:100%;height:5px; word-break:break-all;text-align: right" class=""> <p>' + '____________Today____________' + '</p> </div></div>');
                    } else {
                        $('#croll').append('<div class="outgoing_msg"><div style="width:50%; word-break:break-all;" class="sent_msg"> <p>' + mess + '</p> <span class="time_date"> 11:01 AM    |    June 9</span> </div></div>');
                    }
                }
                    
                if (message.data.startsWith("${CustomerID}") && message.data.endsWith("${sessionScope.Account.accountid}")) {
                    //var mess = message.data.replace("${CustomerID}", "");
                    var mess = message.data.substr(1, message.data.length - 2);
                    //if (message.data.startsWith("${CustomerID}") && ${sessionScope.Account.accountid})
                        $('#croll').append('<div class="incoming_msg"> <div class="incoming_msg_img"> <img src="<%=accCus.getImageURL()%>" alt="sunil"> </div><div style="width:50%; word-break:break-all;" class="received_msg"><div class="received_withd_msg"><p>' + mess + '</p><span class="time_date"> 11:01 AM    |    June 9</span></div></div></div>');
                }
                if (message.data.startsWith("<div") && ${sessionScope.Account.accountid}) {
                    const myArray = message.data.split("~");
                    //$('#cha').append('<div class="chat_list active_chat"><div class="chat_people"><div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div><div class="chat_ib"> <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5><p>'+message.data+'.</p></div></div></div>');
                    document.getElementById("cha").insertAdjacentHTML('afterbegin', myArray[0]);
                }
            }
            function processClose(message) {
                textAreaMessage.value += "Server Disconnect... \n";
            }
            function processError(message) {
                textAreaMessage.value += "Error... " + message + " \n";
            }


            function sendMessage() {
                if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                    var mess = "${sessionScope.Account.accountid}" + "~" + textMessage.value + "~" +${CustomerID};
                    if (!isNaN(textMessage.value)) {
                        websocket.send(textMessage.value);
                    } else {
                        websocket.send(mess);
                    }
                }
                textMessage.value = "";
            }

        </script>     
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            function searchByName(param, param2) {
                var keySearch = param.value.trim();
                var CustomerID = param2;
                var x = location.origin;
                var s = "<%= getServletContext().getContextPath()%>"
                console.log(x);
                console.log(s);
                $.ajax({

//                    url:" http://localhost:8080/home/chat?do=chatEm&CustomerID=8",
                    url: x + s + "/searchUserAjax",
                    type: "get", //send it through get method
                    data: {
                        keySearch: keySearch,
                        CustomerID: CustomerID
                    },
                    success: function (data) {
                        var row = document.getElementById("cha");
                        row.innerHTML = data;

                    },
                    error: function (xhr) {
                        //Do Something to handle error
                    }
                });
            }
            ;
        </script>
    </body>
</html>


