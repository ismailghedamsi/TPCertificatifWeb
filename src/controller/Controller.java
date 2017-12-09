package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.LogManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.core.Logger;

import TPCertificatifApp.bean.*;
import TPCertificatifApp.service.*;
import TPCertificatifApp.util.IPersisanceService;


/**
 * Servlet implementation class Controller
 */
@WebServlet("/ControllerTpCertificatif")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AbstractUser createUserFromForm(HttpServletRequest request, RegistredUsers registredUser) throws Exception {
		
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		System.out.println(registredUser.getListMails().contains(email));
		if(registredUser.getListMails().contains(email)) {
				throw new Exception("The email is already used");
		}else {
			registredUser.getListMails().add(email);
		}
		String password = request.getParameter("password");
		//Set credentials
		AbstractUser newUser = new NormalUser(firstName,lastName,age);
		newUser.getCredentials().setLogin(email);
		newUser.getCredentials().setPassword(password);
		//Create a new User
		return newUser;
	}
	
	public void sendMail() {
		MailService mailService = new MailService();
		try {
			mailService.sendMail("ismailghedamsi@gmail.com", "Confirmation mail", "Welcome omar your code is "+UUID.randomUUID(),"/home/small44/user1mail.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String ACTION_CREATE_ACCOUNT = "createAccount";
		final String ACTION_LOGIN = "loginIn";
		final String ACTION_CREATE_DECK = "createDeck";
		final String NORMAL_USER_PAGE = "NormalUserSession.jsp";
		final String ACCOUNT_CREATION_SUCCESS = "NewAccount.jsp";
		final String DECK_CREATION_PAGE = "createDeck.jsp";
		AbstractUser newUser=null;
		List<AbstractUser> currentUsers;
		String action = request.getParameter("action");
		//Call  DAO,MAIL et persistance services
		UserPersistanceService userPersistanceService = new UserPersistanceService();
		UserDao userDao = new UserDao();
	
		//load user file if users exist
		if(new File(IPersisanceService.USER_SAVE_LOCATION+"users"+".xml").exists()) {
			 currentUsers = userPersistanceService.loadElement(IPersisanceService.USER_SAVE_LOCATION+"users"+".xml");
		}else {
			currentUsers = new ArrayList<>();
		}
		if(action.equals(ACTION_CREATE_ACCOUNT)) {
		
			//Instaciate the list of registred  users
			RegistredUsers ru = new RegistredUsers();

			 try {
				newUser = createUserFromForm(request, ru);
				//Add user to liste of users
				currentUsers.add(newUser);
				//Update the list of user with the new user
				ru.setListUsers(currentUsers);
				if(!new File(System.getProperty("user.home")+File.separator+"users"+File.separator).exists()) {
					new File(System.getProperty("user.home")+File.separator+"users"+File.separator).mkdirs();
				}
				//Save users to xml
				userPersistanceService.saveElementToXML(System.getProperty("user.home")+File.separator+"users"+File.separator+"users"+".xml", ru.getListUsers());
				
				// Send a confimation email to the user with confirmation key
				sendMail();
				request.setAttribute("newUser", newUser);
				RequestDispatcher dispatcher = request.getRequestDispatcher(ACCOUNT_CREATION_SUCCESS);
				dispatcher.forward(request, response);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 }else if(action.equals(ACTION_LOGIN)) {
				String mail = request.getParameter("userMail");
				String password = request.getParameter("userPassword");
				if(mail.equals("ismailghedamsi@gmail.com")) {
					RequestDispatcher dispatcher = request.getRequestDispatcher(NORMAL_USER_PAGE);
					dispatcher.forward(request, response);
				}
			}else if(action.equals(ACTION_CREATE_DECK)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(DECK_CREATION_PAGE);
				dispatcher.forward(request, response);
			}
			
		
			
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
