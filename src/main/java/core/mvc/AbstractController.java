package core.mvc;

import next.View.JsonView;
import next.View.JspView;

public abstract class AbstractController implements Controller{
	protected ModelAndView jspView(String forwardUrl){
		return new ModelAndView(new JspView(forwardUrl));
	}
	
	protected ModelAndView jsonView(){
		return new ModelAndView(new JsonView());
	}
}
