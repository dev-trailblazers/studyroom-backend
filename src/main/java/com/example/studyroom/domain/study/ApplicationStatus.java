package com.example.studyroom.domain.study;

public enum ApplicationStatus {
    대기, 승인, 반려;

    public static ApplicationStatus getStatus(boolean status){
        return status ? 승인 : 반려;
    }
}