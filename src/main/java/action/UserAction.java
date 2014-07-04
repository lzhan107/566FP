package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserManager;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;


public class UserAction extends MappingDispatchAction {
	private UserManager userManager = new UserManager();

	public ActionForward showUsers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//userManager = new UserManager();
		List<User> users = new ArrayList<User>();
		users = userManager.getUsers();
		request.setAttribute("users", users);
		return mapping.findForward("success");
	}

	public ActionForward createUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("success");
	}

	public ActionForward deleteUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		userManager.deleteUser(Integer.parseInt(id));
		showUsers(mapping, form, request, response);
		return mapping.findForward("success");
	}

	public void FindEditingUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String editUserId = request.getParameter("id");
		User user = userManager.getUserById(Integer.parseInt(editUserId));
		try {
			response.getWriter().write(
					user.getId() + "," + user.getName() + "," + user.getRoleId()
							+ "," + user.getRatings());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ActionForward editUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String editUserId = request.getParameter("editUserId");
		String editUserName = request.getParameter("editUserName");
		String editUserRole = request.getParameter("editUserRole");
		String editUserRating = request.getParameter("editUserRating");
		User user = new User();
		user.setId(Integer.parseInt(editUserId));
		user.setName(editUserName);
		user.setRoleId(Integer.parseInt(editUserRole));
		user.setRatings(Integer.parseInt(editUserRating));
		
		userManager.editUser(user);
		showUsers(mapping, form, request, response);
		return mapping.findForward("success");
	}

	public ActionForward copyUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		User user = userManager.getUserById(Integer.parseInt(id));
		userManager.copyUser(user);
		showUsers(mapping, form, request, response);
		return mapping.findForward("success");
	}
}
