package br.com.magal.aula01.services;

import br.com.magal.aula01.data.vo.versions.security.AccountCredentialsVO;
import br.com.magal.aula01.data.vo.versions.security.TokenVO;
import br.com.magal.aula01.repositories.UserRepository;
import br.com.magal.aula01.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity<?> signIn(AccountCredentialsVO data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            var user = userRepository.findByUsername(username);

            var tokenReponse = new TokenVO();
            if (user != null){
                tokenReponse = jwtTokenProvider.createAccessToken(username, user.getRoles());

            }else{
                throw new UsernameNotFoundException(username);
            }
            return ResponseEntity.ok(tokenReponse);

        }catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password.");
        }
    }

}
