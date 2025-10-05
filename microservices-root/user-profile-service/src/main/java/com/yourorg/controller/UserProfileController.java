package com.yourorg.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class UserProfileController {
	// Dummy in-memory storage

	private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);
	private static Map<String, Map<String, String>> profiles = new HashMap<>();

	static {
		Map<String, String> m1 = new HashMap<>();
		m1.put("userId", "1");
		// Create Profile
		m1.put("userId", "2");

		m1.put("userId", "3");
		m1.put("userId", "4");
		profiles.put("userId", m1);
		logger.debug("profiles");
	}

	@PostMapping
	public ResponseEntity<?> createProfile(@RequestBody Map<String, String> profile) {
		String userId = profile.get("userId");
		logger.debug("the token is" + userId);

		// System.out.println(profiles.toString());

		for (Map.Entry<String, Map<String, String>> m11 : profiles.entrySet()) {
			System.out.println("profiles entry objects" + m11.getKey());
			System.out.println("profiles entry objects" + m11.getValue());

		}
		if (profiles.containsKey(userId)) {
			logger.debug("userId exists");
			return ResponseEntity.badRequest().body("Profile already exists");
		}
		profiles.put(userId, profile);
		return ResponseEntity.ok("Profile created");
	}
	@GetMapping("/profile")
	public ResponseEntity<String> profile(Principal principal) {
	    return ResponseEntity.ok("Authenticated as: " + principal.getName());
	}

	// Get Profile
	@GetMapping("/{userId}")
	public ResponseEntity<?> getProfile(@PathVariable String userId) {
		Map<String, String> profile = profiles.get(userId);
		if (profile == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(profile);
	}

	// Update Profile
	@PutMapping("/{userId}")
	public ResponseEntity<?> updateProfile(@PathVariable String userId,
			@RequestBody Map<String, String> updatedProfile) {
		if (!profiles.containsKey(userId)) {
			return ResponseEntity.notFound().build();
		}
		profiles.put(userId, updatedProfile);
		return ResponseEntity.ok("Profile updated");
	}

	// Delete Profile
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteProfile(@PathVariable String userId) {
		if (!profiles.containsKey(userId)) {
			return ResponseEntity.notFound().build();
		}
		profiles.remove(userId);
		return ResponseEntity.ok("Profile deleted");
	}
}
