package com.ricoh.test.service.impl;

import com.ricoh.test.dto.usuario.UsuarioDto;
import com.ricoh.test.dto.usuario.UsuarioMapper;
import com.ricoh.test.dto.usuario.UsuarioRequest;
import com.ricoh.test.model.Usuario;
import com.ricoh.test.model.enums.Status;
import com.ricoh.test.model.security.Privilege;
import com.ricoh.test.model.security.Role;
import com.ricoh.test.reposiroty.UsuarioRepository;
import com.ricoh.test.reposiroty.security.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
@Transactional
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final UsuarioRepository userRepository;

	private final RoleRepository roleRepository;

	private final UsuarioMapper userMapper;

	private final PasswordEncoder bcryptEncoder;

    public UsuarioDto save(UsuarioRequest user) {
		Usuario newUser = userMapper.toModel(user);
        newUser.setFechaRegistro(LocalDateTime.now());
        newUser.setEstatus(Status.CREATED);
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRoles(List.of(roleRepository.findByName("ROLE_USER")));
		return userMapper.toDto(userRepository.save(newUser));
	}

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
            var user = userRepository.findByUsername(username);
            if (user.isEmpty()) {
                throw new UsernameNotFoundException("No user found with username: " + username);
            }

            return new User(user.get().getUsername(), user.get().getPassword(), true, true, true, true, getAuthorities(user.get().getRoles()));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> privileges = new ArrayList<>();
        final List<Privilege> collection = new ArrayList<>();
        for (final Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }

        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

 
}
