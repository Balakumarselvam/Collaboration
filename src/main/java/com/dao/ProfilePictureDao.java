package com.dao;

import com.model.ProfilePicture;

public interface ProfilePictureDao {
	void saveProfilePicture(ProfilePicture profilePicture);
	ProfilePicture getProfilePic(String username);
}
