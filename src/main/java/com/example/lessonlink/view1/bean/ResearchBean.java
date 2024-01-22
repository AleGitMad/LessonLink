package com.example.lessonlink.view1.bean;

public class ResearchBean {

        private String subject;
        private String where;
        private boolean isOnline;

        public ResearchBean(String subject, String where) {
            this.subject = subject;
            this.where = where;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String brand) {
            this.subject = brand;
        }

        public String getWhere() {
            return where;
        }

        public void setWhere(String startingMileage) {
            this.where = startingMileage;
        }
        public boolean getIsOnline() {
            return isOnline;
        }
        public void setIsOnline() {
            this.isOnline = true;
        }

        public boolean validate() {
            return (subject != null) && where != null;
        }

}
