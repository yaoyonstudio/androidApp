package com.example.administrator.bottomnavigation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/3/10.
 */

public class FragmentHome extends Fragment {
    private SliderLayout mDemoSlider;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDemoSlider = (SliderLayout) getActivity().findViewById(R.id.slider);

        HashMap<String,String> urlMaps = new HashMap<>();
        urlMaps.put("Slide 1", "https://tj2.tigonetwork.com/static/img/slide1.jpg");
        urlMaps.put("Slide 2", "https://tj2.tigonetwork.com/static/img/slide2.jpg");
        urlMaps.put("Slide 3", "https://tj2.tigonetwork.com/static/img/slide3.jpg");

        for(String name : urlMaps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .description(name)                                      //描述
                    .image(urlMaps.get(name))                               //image方法可以传入图片url、资源id、File
                    .setScaleType(BaseSliderView.ScaleType.Fit)             //图片缩放类型
                    .setOnSliderClickListener(onSliderClickListener);           //图片点击
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);             //传入参数
            mDemoSlider.addSlider(textSliderView);                          //添加一个滑动页面
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);       //滑动动画
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);          //默认指示器样式
        mDemoSlider.setCustomIndicator((PagerIndicator) getActivity().findViewById(R.id.custom_indicator2));   //自定义指示器
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());                             //设置图片描述显示动画
        mDemoSlider.setDuration(4000);                                                          //设置滚动时间，也是计时器时间
        mDemoSlider.addOnPageChangeListener(onPageChangeListener);
    }

    private BaseSliderView.OnSliderClickListener onSliderClickListener=new BaseSliderView.OnSliderClickListener() {
        @Override
        public void onSliderClick(BaseSliderView slider) {
            Toast.makeText(getActivity(),slider.getBundle().get("extra") + "",
                    Toast.LENGTH_SHORT).show();
        }
    };

    //页面改变监听
    private ViewPagerEx.OnPageChangeListener onPageChangeListener=new ViewPagerEx.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            Log.d("ansen", "Page Changed: " + position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {}
    };
}
