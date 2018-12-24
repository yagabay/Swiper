package com.vismus.swiper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SwiperFragment extends Fragment{

    SwiperViewPager _viewPager;
    SwiperViewPagerAdapter _viewPagerAdapter;
    Listener _listener;

    Fragment _origFragment;
    Fragment _destFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_swiper, container, false);
        _viewPager = rootView.findViewById(R.id.view_pager);
        _viewPager.addOnPageChangeListener(new OnPageChangeListener());
        _viewPagerAdapter = new SwiperViewPagerAdapter(getFragmentManager());
        _viewPager.setAdapter(_viewPagerAdapter);
        setOrigAndDest(_origFragment, _destFragment);
        return rootView;
    }

    public void setOrigAndDest(Fragment origFragment, Fragment destFragment){
        if(_viewPagerAdapter == null) {
            _origFragment = origFragment;
            _destFragment = destFragment;
        }
        else{
            List<Fragment> items = new ArrayList<>();
            items.add(_origFragment);
            items.add(_destFragment);
            _viewPagerAdapter.setItems(items);
            _viewPagerAdapter.notifyDataSetChanged();
        }
    }

    public void setListener(Listener listener){
        _listener = listener;
    }

    public void swipe(){
        _viewPager.setCurrentItem(1);
    }

    interface Listener{
        void onSwipeComplete();
    }

    class OnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position){}

        @Override
        public void onPageScrollStateChanged(int state) {
            if(state == ViewPager.SCROLL_STATE_IDLE){
                _listener.onSwipeComplete();
            }
        }
    }

}
