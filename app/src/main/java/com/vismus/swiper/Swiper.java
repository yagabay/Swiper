package com.vismus.swiper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


public class Swiper implements SwiperFragment.Listener{

    FragmentManager _fragmentManager;
    int _frameLayoutId;
    Fragment _destFragment;
    SwiperFragment _swiperFragment;
    Listener _listener;

    Swiper(FragmentManager fragmentManager, int frameLayoutId, Fragment destFragment){
        _fragmentManager = fragmentManager;
        _frameLayoutId = frameLayoutId;
        _destFragment = destFragment;
        Fragment origFragment = _fragmentManager.findFragmentById(frameLayoutId);
        _swiperFragment = new SwiperFragment();
        _fragmentManager
                .beginTransaction()
                .replace(_frameLayoutId, _swiperFragment)
                .commitNow();
        _swiperFragment.setOrigAndDest(origFragment, destFragment);
        _swiperFragment.setListener(this);
    }

    public void setListener(Listener listener){
        _listener = listener;
    }

    public void swipe(){
        _swiperFragment.swipe();
    }

    interface Listener{
        void onSwipeComplete();
    }

    @Override
    public void onSwipeComplete(){
        _fragmentManager
                .beginTransaction()
                .remove(_destFragment)
                .commitNow();
        _fragmentManager
                .beginTransaction()
                .replace(_frameLayoutId, _destFragment)
                .commitNow();
        _listener.onSwipeComplete();
    }

}
