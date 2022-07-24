package Controller;

import Entity.Account;
import dao.MessDAO;
import dao.impl.AccountDAOImpl;
import dao.impl.MessDAOImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chatRoomServer")
public class ChatRoomServerEndpoint {

    static Set<Session> users = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void handleOpen(Session session) {

        users.add(session);
    }
    static List<Integer> listBefor = new ArrayList<>();

    @OnMessage
    public void handleMessage(String message, Session userSession) throws IOException {
        try {
            //lan dau chua co
            String username = (String) userSession.getUserProperties().get("username");
            AccountDAOImpl daoAcc = new AccountDAOImpl();
            MessDAOImpl daoMess = new MessDAOImpl();

            List<Integer> listAcc = daoAcc.ListAllAccountEmpID();
            List<Integer> listAccCus = daoMess.getIdCus();

            if (username == null) {
                List<Integer> listAccCus1 = daoMess.getIdCus();

                listBefor = listAccCus1;
                System.out.println(listBefor);
                //char[] charID = message.toCharArray();
                int idCustomer = Integer.parseInt(message);
                //check inbox new

                // cung laemployee
                boolean checkEmployee = false;
                boolean checkCus = false;
                for (Integer integer : listAcc) {
                    if (integer == idCustomer) {
                        checkEmployee = true;
                    }
                }
                //kiểm tra xem có chat chưa?
                for (Integer listcus : listAccCus) {
                    if (listcus == idCustomer) {
                        checkCus = true;
                    }
                }
                String temp = message;
                if (checkEmployee == false && checkCus == false) {
                    int idEmployee = daoAcc.getRandomElemAccountEmpID(listAcc);
                    daoMess.insertMess(idCustomer, idEmployee, "");
                    message = "Hi" + message;
                }

                userSession.getUserProperties().put("username", temp);

                System.out.println("temp:" + temp);
                System.out.println(message);
                userSession.getBasicRemote().sendText(message);
            } else {
                //co roi
                String[] arrOfStr = message.split("~");
//            char[] charID = message.toCharArray();
                int idCustomer = Integer.parseInt(String.valueOf(arrOfStr[0]));// and employee
                int idEmployee = Integer.parseInt( arrOfStr[arrOfStr.length-1]); //daoMess.getIdEmByIdCus(idCustomer); // customer
                boolean checkEmployee = false;
                for (Integer integer : listAcc) {
                    if (integer == idCustomer) {
                        checkEmployee = true;
                    }
                }
                int tempbox = idCustomer;
                String tempStringbox = message;
                if (checkEmployee == true) {
                    idEmployee = Integer.parseInt(String.valueOf(arrOfStr[arrOfStr.length - 1]));
                    tempbox = idEmployee;
                    message = arrOfStr[1];
                    tempStringbox = arrOfStr[1];
                    String temp = daoMess.getMessByID(idEmployee, idCustomer);
                    temp += (username + message + arrOfStr[arrOfStr.length - 1] + "~");
                    daoMess.updateMess(idEmployee, idCustomer, temp);
                } else {
                    message = arrOfStr[1];
                    tempStringbox = arrOfStr[1];
                    String temp = daoMess.getMessByID(idCustomer, idEmployee);
                    temp += (username + message + idEmployee + "~");
                    daoMess.updateMess(idCustomer, idEmployee, temp);
                }
                boolean checkBefor = false;
                for (Integer in : listBefor) {
                    if (idEmployee == in) {
                        checkBefor = true;
                    }
                }
                System.out.println(checkBefor);

                if (!checkBefor) {
                    try {
                        String temp = "*" + tempStringbox;
                        Account acc = daoAcc.getAccountByIDA((tempbox));
                        message = "<div class=\"chat_list active_chat\">\n"
                                + "                            <div class=\"chat_people\">\n"
                                + "                                <div class=\"chat_img\"> <img src=\"" + acc.getImageURL() + "\" alt=\"img\"> </div>\n"
                                + "                                <div class=\"chat_ib\">\n"
                                + "                                    <h5> <a href=\"chat?do=chatEm&CustomerID=" + acc.getAccountid() + "\"> " + acc.getDisplayname() + " </a>  <span class=\"chat_date\">Dec 25</span></h5>\n"
                                + "                                    <p> " + temp + ".</p>\n"
                                + "                                </div>\n"
                                + "                            </div>\n"
                                + "                        </div>" + "~"
                                + "<div id=\"innerdiv\" class=\"chat-bubble me\">"+tempStringbox+"</div>"
                                ;

                       
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    listBefor.add(idEmployee);
                    listBefor.add(idCustomer);
                    for (Session session : users) {
                        session.getBasicRemote().sendText(message);
                    }
                } else {
                    listBefor.add(idEmployee);
                    listBefor.add(idCustomer);
                    for (Session session : users) {
                        session.getBasicRemote().sendText(username + message+arrOfStr[2]);
                    }
                }

                System.out.println(listBefor);
            }
        } catch (Exception ex) {
            Logger.getLogger(ChatRoomServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnClose
    public void handleClose(Session session) {
        users.remove(session);
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }

}
