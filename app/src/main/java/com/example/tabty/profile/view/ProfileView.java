package com.example.tabty.profile.view;

public interface ProfileView {
    void onSyncDataSuccess(String success);
    void onSyncDataFailure(String errorMessage);
    void onBackUpSuccess(String success);
    void onBackUpFailure(String errorMessage);
}
