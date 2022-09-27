package com.example.Bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Bookstore.domain.ApplicationUser;
import com.example.Bookstore.domain.UserRepository;

/**
 * This class is used by spring security to authenticate and authorize user
 **/

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	private final UserRepository repository;

		@Autowired
		public UserDetailServiceImpl(UserRepository userRepository) {
			this.repository = userRepository;
		}
		
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			log.info("loadUserByUsername: " + username);
			ApplicationUser curruser = repository.findByUsername(username);
			UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(),
					AuthorityUtils.createAuthorityList(curruser.getRole()));
			return user;
		}
}
