package com.example.studyroom.domain.study;

public enum ParticipantRole {
    ANY("일반 참가자"), MANAGER("매니저"), LEADER("방장");

    private final String name;

    ParticipantRole(String name) {
        this.name = name;
    }
}
