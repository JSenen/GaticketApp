package com.juansenen.gaticketapp.domain;

public class User {

        private long userId;
        private String userMail;
        private String userPassword;
        private String userTip;
        private String userRol;
        private Department userDepartment;

        public User(){}

        public User(long userId, String userMail, String userPassword, String userTip, String userRol) {
                this.userId = userId;
                this.userMail = userMail;
                this.userPassword = userPassword;
                this.userTip = userTip;
                this.userRol = userRol;
        }

        public User(long userId, String userMail, String userPassword, String userTip, String userRol, Department userDepartment) {
                this.userId = userId;
                this.userMail = userMail;
                this.userPassword = userPassword;
                this.userTip = userTip;
                this.userRol = userRol;
                this.userDepartment = userDepartment;
        }

        public long getUserId() {
                return userId;
        }

        public void setUserId(long userId) {
                this.userId = userId;
        }

        public String getUserMail() {
                return userMail;
        }

        public void setUserMail(String userMail) {
                this.userMail = userMail;
        }

        public String getUserPassword() {
                return userPassword;
        }

        public void setUserPassword(String userPassword) {
                this.userPassword = userPassword;
        }

        public String getUserTip() {
                return userTip;
        }

        public void setUserTip(String userTip) {
                this.userTip = userTip;
        }

        public String getUserRol() {
                return userRol;
        }

        public void setUserRol(String userRol) {
                this.userRol = userRol;
        }

        public Department getUserDepartment() {
                return userDepartment;
        }

        public void setUserDepartment(Department userDepartment) {
                this.userDepartment = userDepartment;
        }
}


