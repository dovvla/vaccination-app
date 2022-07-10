package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.models.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private KorisnikService korisnikService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik korisnik;
        korisnik = korisnikService.getXmlAsObject(username);

        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = korisnik::getRola;
        authorities.add(authority);

        return new User(username, korisnik.getSifra(), authorities);
    }
}
