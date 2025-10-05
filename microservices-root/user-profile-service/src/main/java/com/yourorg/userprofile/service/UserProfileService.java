package com.yourorg.userprofile.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
public class UserProfileService {
	private final Map<String, Map<String, String>> profiles = new HashMap<>();
	private static final Logger logger = LoggerFactory.getLogger(UserProfileService.class);

	public boolean profileExists(String userId) {
		logger.debug("in profileExists");
		return profiles.containsKey(userId);
	}

	public String createProfile(Map<String, String> profile) {
		String userId = profile.get("userId");
		logger.debug("in createProfile");
		if (profiles.containsKey(userId)) {
			logger.debug("inside createProfile condition");
			return "Profile already exists";
		}
		profiles.put(userId, profile);
		return "Profile created";
	}

	public Map<String, String> getProfile(String userId) {
		return profiles.get(userId);
	}

	public String updateProfile(String userId, Map<String, String> updatedProfile) {
		if (!profiles.containsKey(userId)) {
			return null;
		}
		profiles.put(userId, updatedProfile);
		return "Profile updated";
	}

	public String deleteProfile(String userId) {
		if (!profiles.containsKey(userId)) {
			return null;
		}
		profiles.remove(userId);
		return "Profile deleted";
	}
}
