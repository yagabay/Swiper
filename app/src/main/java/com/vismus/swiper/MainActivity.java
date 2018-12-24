package com.vismus.swiper;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements Swiper.Listener{

    Swiper _swiper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSwipe = findViewById(R.id.btn_swipe);
        btnSwipe.setOnClickListener(new OnSwipeButtonClickListener());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.lay_main, new OrigFragment())
                .commitNow();
        _swiper = new Swiper(getSupportFragmentManager(), R.id.lay_main, new DestFragment());
        _swiper.setListener(this);
    }

    class OnSwipeButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view){
            _swiper.swipe();
        }

    }

    @Override
    public void onSwipeComplete(){
        Toast.makeText(this, "MainActivity::onSwipeComplete", Toast.LENGTH_SHORT).show();
    }

}
