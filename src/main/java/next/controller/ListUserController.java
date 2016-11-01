package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import core.mvc.Controller;
import next.dao.UserDao;

public class ListUserController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(ListUserController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            return "redirect:/users/loginForm";
        }
        
        try {
			UserDao userDao = new UserDao();
			req.setAttribute("users", userDao.findAll());
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
		}
        
        return "/user/list.jsp";
    }
}
