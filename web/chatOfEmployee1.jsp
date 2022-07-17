<%@page import="dao.impl.AccountDAOImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </head>
    <body>

        <div class="messaging">
            <div class="inbox_msg">
                <div class="inbox_people">
                    <div class="headind_srch">
                        <div class="recent_heading">
                            <h4>Recent</h4>
                        </div>
                        <div class="srch_bar">
                            <div class="stylish-input-group">
                                <input type="text" class="search-bar"  placeholder="Search" >
                            </div>
                        </div>
                    </div>
                    <div class="inbox_chat scroll">

                        <%  List<Account> list = (List<Account>) request.getAttribute("listAccByEm");
                            Account acc = (Account) session.getAttribute("Account");
                        %> 

                        <% for (Account elem : list) {%>
                        <div class="chat_list active_chat">
                            <div class="chat_people">
                                <div class="chat_img"> <img src="<%=elem.getImageURL()%>" alt="sunil"> </div>
                                <div class="chat_ib">
                                    <h5> <a href="chat?do=chatEm&CustomerID=<%=elem.getAccountid()%>"> <%=elem.getDisplayname()%> </a>  <span class="chat_date">Dec 25</span></h5>
                                    
                                    <p><%=elem.getPhone()%></p>
                                </div>
                            </div>
                        </div>

                        <% }%>


                    </div>
                </div>
                <div class="mesgs">
                    <div class="msg_history">


                    </div>
                    <div class="type_msg">
                         <div class="input_msg_write">
                            <input value="${sessionScope.Account.accountid}" id="textMessage"  type="text" class="write_msg" placeholder="Type a message" />
                            <button onclick="sendMessage()" class="msg_send_btn" type="button"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--script chat-->
        

    </body>
</html>


