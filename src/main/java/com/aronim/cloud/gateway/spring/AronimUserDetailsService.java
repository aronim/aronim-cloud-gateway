package com.aronim.cloud.gateway.spring;

import com.aronim.cloud.common.security.AronimUserDetails;
import com.aronim.cloud.gateway.service.Role;
import com.aronim.cloud.gateway.service.User;
import com.aronim.cloud.gateway.service.UserIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User: Kevin W. Sewell
 * Date: 2015-05-30
 * Time: 19h26
 */
@Component("userDetailsService")
public class AronimUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserIntegrationService userIntegrationService;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException
    {
        User user = userIntegrationService.findByEmailAddress(emailAddress);

        if (user == null)
        {
            return null;
        }

        String userId = user.getId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String encryptedPassword = user.getEncryptedPassword();
        Collection<? extends GrantedAuthority> authorities = getAuthorities(user);

        return new AronimUserDetails(userId, firstName, lastName, emailAddress, encryptedPassword, authorities);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user)
    {
        Set<Role> roles = user.getRoles();

        if (roles != null)
        {
            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());
        }

        return new HashSet<>();
    }
}
