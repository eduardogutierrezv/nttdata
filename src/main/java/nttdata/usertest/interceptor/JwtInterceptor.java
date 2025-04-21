package nttdata.usertest.interceptor;

import nttdata.usertest.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (JwtUtils.validateToken(token)) {
                return true; // Token válido, permitir el acceso
            }
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.sendError(401, "Error token invalido");
        return false;
    }

}
