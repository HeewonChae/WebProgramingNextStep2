package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;
import next.dao.UserDao;
import next.model.User;

public class ProfileController implements Controller {

	private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String userId = req.getParameter("userId");
		// User user = DataBase.findUserById(userId);

		try {
			UserDao userDao = new UserDao();
			User user = userDao.findByUserId(userId);
			
			if (user == null) {
				throw new NullPointerException("사용자를 찾을 수 없습니다.");
			}
			
			req.setAttribute("user", user);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return "/user/profile.jsp";
	}
}
