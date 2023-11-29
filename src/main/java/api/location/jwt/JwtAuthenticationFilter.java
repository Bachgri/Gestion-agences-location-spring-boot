package api.location.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.auth.MalformedChallengeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import api.location.exception.JwtTokenMalformedException;
import api.location.exception.JwtTokenMissingException;
import api.location.service.UtilisateurService;
import api.location.serviceImpl.AuthService; 


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	JwtUtil jwtUtil;
	AuthService authService;
	

	public JwtAuthenticationFilter(JwtUtil jwtUtil, AuthService authService) {
		super();
		this.jwtUtil = jwtUtil;
		this.authService = authService;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		System.err.println("ok ");
		if(header ==null || !header.startsWith("HTTP_TOKEN")) {
			throw new JwtTokenMissingException("No token found in the request header");
		}
		String token = header.replace("HTTP_TOKEN ", "");
		try {
			jwtUtil.validateToken(token);
		}catch(Exception e) {
			throw new JwtTokenMalformedException("");
		}
		String username = jwtUtil.getUserName(token);
		
		UserDetails userDetails = authService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken usToken = new UsernamePasswordAuthenticationToken(username, null,userDetails.getAuthorities());
		
		usToken.setDetails(new WebAuthenticationDetails(request));
		if(SecurityContextHolder.getContext().getAuthentication() == null) {
			SecurityContextHolder.getContext().setAuthentication(usToken);
		}
		filterChain.doFilter(request, response);
		
	}

}
