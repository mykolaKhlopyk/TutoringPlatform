package com.mkh.tutoringplatform.web.utils;

import com.mkh.tutoringplatform.domain.exception.AccessDeniedException;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class ControllerUtils {

    public static User getAuthenticatedUser(UserDetails userDetails) {
        return ((UserDetailsImpl) userDetails).getUser();
    }

    public static void isAvailableResourceForUser(long expectedId, long actualId) {
        if(expectedId != actualId) {
            throw new AccessDeniedException();
        }
    }

    public static void isAvailableResourceForUser(List<Long> expectedIds, long actualId) {
        if(!expectedIds.contains(actualId)) {
            throw new AccessDeniedException();
        }
    }
}
