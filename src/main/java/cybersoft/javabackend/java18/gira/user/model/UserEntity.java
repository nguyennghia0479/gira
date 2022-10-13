package cybersoft.javabackend.java18.gira.user.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserEntity {
    @UtilityClass
    public static class UserGroupMappedRole {
        public static final String ROLE_MAPPED_USER_GROUP = "roles";
        public static final String JOIN_TABLE = "G_USER_GROUP_ROLE";
        public static final String JOIN_TABLE_ROLE_ID = "G_ROLE_ID";
        public static final String JOIN_TABLE_USER_GROUP_ID = "G_USER_GROUP_ID";
    }

    @UtilityClass
    public class UserGroupMappedUser {
        public static final String USER_MAPPED_USER_GROUP = "users";
        public static final String JOIN_TABLE = "G_USER_GROUP_USER";
        public static final String JOIN_TABLE_USER_ID = "G_USER_ID";
        public static final String JOIN_TABLE_USER_GROUP_ID = "G_USER_GROUP_ID";
    }

    @UtilityClass
    public class User {
        public static final String TABLE_NAME = "G_USER";
        public static final String USERNAME = "G_USERNAME";
        public static final String PASSWORD = "G_PASSWORD";
        public static final String FULL_NAME = "G_FULL_NAME";
        public static final String DISPLAY_NAME = "G_DISPLAY_NAME";
        public static final String AVATAR = "G_AVATAR";
        public static final String EMAIL = "G_EMAIL";
        public static final String STATUS = "G_STATUS";
        public static final String FACEBOOK_URL = "G_LINK_FACEBOOK";
        public static final String MAJORITY = "G_JOB";
        public static final String DEPARTMENT = "G_DEPARTMENT";
        public static final String HOBBIES = "G_HOBBY";
    }
}
