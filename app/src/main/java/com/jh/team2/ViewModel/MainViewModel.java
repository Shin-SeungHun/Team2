package com.jh.team2.ViewModel;



import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import com.jh.team2.Model.API;
import com.jh.team2.R;
import com.jh.team2.Team2B_FragmentMain;
import com.jh.team2.Team2C_FragmentChat;
import com.jh.team2.Team2D_Fragment_pop;
import com.jh.team2.Team2E_Fragment_top;

public class MainViewModel extends ViewModel {
    private FragmentManager fragmentManager;
    private Team2B_FragmentMain fragmentMain;
    private Team2C_FragmentChat fragmentChat;
    private Team2D_Fragment_pop fragmentPop;
    private Team2E_Fragment_top fragmentTop;
    private FragmentTransaction transaction;

    public void init(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        fragmentMain = new Team2B_FragmentMain();
        fragmentPop = new Team2D_Fragment_pop();
        fragmentTop = new Team2E_Fragment_top();
        fragmentChat = new Team2C_FragmentChat();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();

    }


    public void onClick(int viewId) {
        transaction = fragmentManager.beginTransaction();

        switch (viewId) {
            case R.id.btn_main:
                transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();
                break;
            case R.id.btn_chat:
                transaction.replace(R.id.frameLayout, fragmentChat).commitAllowingStateLoss();
                break;
            case R.id.btn_pop:
                API.isPop = true;
                transaction.replace(R.id.frameLayout, fragmentPop).commitAllowingStateLoss();
                break;
            case R.id.btn_top:
                API.isPop = false;
                transaction.replace(R.id.frameLayout, fragmentTop).commitAllowingStateLoss();
                break;
        }

    }

}
