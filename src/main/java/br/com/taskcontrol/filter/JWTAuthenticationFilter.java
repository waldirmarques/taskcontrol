package br.com.taskcontrol.filter;

import br.com.taskcontrol.user.User;
import br.com.taskcontrol.user.dto.UserSuccessfulLoginDTO;
import br.com.taskcontrol.util.CredentialsDTO;
import br.com.taskcontrol.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, ObjectMapper objectMapper) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws AuthenticationException {

        try {
            var credentials = objectMapper.readValue(
                    httpServletRequest.getInputStream(),
                    CredentialsDTO.class
            );

            var authToken = new UsernamePasswordAuthenticationToken(
                    credentials.getEmail(),
                    credentials.getPassword(),
                    List.of()
            );
            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new AuthenticationCredentialsNotFoundException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void successfulAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                         FilterChain chain, Authentication auth) throws IOException {
        var loggedUser = (User) auth.getPrincipal();
        var token = jwtUtil.generateToken(loggedUser.getEmail(), loggedUser.getId().toString());

        httpServletResponse.addHeader("Authorization", "Bearer " + token);
        httpServletResponse.setCharacterEncoding("UTF8");

        var responseUser = UserSuccessfulLoginDTO.from(loggedUser);
        responseUser.setToken(token);

        var jsonString = objectMapper.writeValueAsString(responseUser);
        httpServletResponse.getWriter().write(jsonString);

        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
        httpServletResponse.addHeader("access-control-expose-headers", "Authorization");
    }

    private static class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().append(json());
        }

        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", " +
                    "\"status\": 401, " +
                    "\"error\": \"Access denied\", " +
                    "\"message\": \"Invalid Email or password\", " +
                    "\"path\": \"/login\"}";
        }
    }
}
