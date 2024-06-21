package com.multicampus.ganwonActivity.filter;

import com.multicampus.ganwonActivity.entity.UserEntity;
import com.multicampus.ganwonActivity.provider.JwtProvider;
import com.multicampus.ganwonActivity.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            String token = parseBearerToken(request);

            if(token == null){
                filterChain.doFilter(request,response);
                return;

            }
            //userNo으로 할지 user Id로 할지
            //ROLE_USER, ROLE_ADMIN
            String userEmail = jwtProvider.validate(token);
            if(userEmail == null){
                filterChain.doFilter(request, response);
                return;
            }

//            AbstractAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(userEmail, null, AuthorityUtils.NO_AUTHORITIES);
//            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//            securityContext.setAuthentication(authenticationToken);
//
//            SecurityContextHolder.setContext(securityContext);

            UserEntity userEntity = userRepository.findUserByUserEmail(userEmail);
            String role = userEntity.getUserRole(); //role : ROLE_USER, ROLE_ADMIN


            //ROLE_USER, ROLE_ADMIN형태로
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role)); //반드시 ROLE_형태


            //빈 securityContext 형성
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            // 비어있는 securityContext에 authenticationToken값을 넣어주는 과정
            AbstractAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userEmail, null, authorities);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            securityContext.setAuthentication(authenticationToken);
            //등록 -> 이제 얘가 controller에 접근할수 있게
            SecurityContextHolder.setContext(securityContext);

        }catch(Exception e){
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);

    }

    private String parseBearerToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        boolean hasAuthorization = StringUtils.hasText(authorization);

        if(!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer) return null;

        String token = authorization.substring(7); //"Bearer "이후 글자부터
        return token;

    }


}
