package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		System.out.println("[preHandler]"+id);
		
		if(id == null){
			String urlPrior = request.getRequestURI().toString()+"?"+request.getQueryString();
			request.getSession().setAttribute("url_prior_login", urlPrior);
			
			response.sendRedirect(request.getContextPath()+"/member/login");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		System.out.println("[postHandler]");
		
	
		postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("[afterCompletion]");
		afterCompletion(request, response, handler, ex);
		
	}


}
