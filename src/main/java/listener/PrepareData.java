package listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.User;
import model.UserManager;


public class PrepareData implements ServletContextListener {

	public PrepareData() {
	}

	public void contextInitialized(ServletContextEvent sce) {
		UserManager userManager = new UserManager();
		System.out.println("Listener called");
		if (!userManager.hasUser()) {// user table does not have values
			userManager.createUsers(prepareUserData());
		}
	}

	public List<User> prepareUserData() {
		List<User> users = new ArrayList<User>();
		List<String> names = new ArrayList<String>();
		Random randomRoleNumber = new Random();
		Random randomRatings = new Random();

		String[] arrayOfNames = { "Aaron", "Abdel", "Adam", "Aidan", "Akiva"};

		for (int i = 0; i < arrayOfNames.length; i++){
			names.add(arrayOfNames[i]);
		}
		
		for (int i = 0; i < names.size(); i++) {
			User user = new User(i, names.get(i),
					randomRoleNumber.nextInt(3) + 1, randomRatings.nextInt(6));
			users.add(user);
		}
		return users;
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

}
