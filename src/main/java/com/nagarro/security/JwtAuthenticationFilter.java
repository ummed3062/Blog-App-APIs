package com.nagarro.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

//		get token

		String requestToken = request.getHeader("Authorization");

//		Bearer 2352534sdggsf
		System.out.println(requestToken);

		String userName = null;

		String token = null;

		if (requestToken != null && requestToken.startsWith("Bearer")) {

			token = requestToken.substring(7);

			try {

				userName = this.jwtTokenHelper.getUsernameFromToken(token);

			} catch (IllegalArgumentException e) {

				System.out.println("Unable to get Jwt token");
			} catch (ExpiredJwtException e) {
				System.out.println("Jwt token has expired!!");
			} catch (MalformedJwtException e) {
				System.out.println("Invalid Jwt");
			}

		} else {
			System.out.println("JWT token does not begin with Bearer");
		}

//		Once we get the token, now check it valid or not

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

			if (this.jwtTokenHelper.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			} else {
				System.out.println("Invalid JWT Token !!");
			}

		} else {
			System.out.println("Username is null or context is not null");
		}

		filterChain.doFilter(request, response);
	}

}
