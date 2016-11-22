package me.kareluo.popupmenuview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Arrays;

import me.kareluo.ui.OptionMenu;
import me.kareluo.ui.OptionMenuView;
import me.kareluo.ui.PopLayout;
import me.kareluo.ui.PopupMenuView;
import me.kareluo.ui.PopupView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener, OptionMenuView.OnOptionMenuClickListener {

    private PopLayout mPopLayout;

    private PopLayout mImgPopLayout;

    private OptionMenuView mMenuView;

    private PopupMenuView mPopupMenuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg_group);
        radioGroup.setOnCheckedChangeListener(this);

        mPopLayout = (PopLayout) findViewById(R.id.pl_pop);
        mImgPopLayout = (PopLayout) findViewById(R.id.pl_img);

        SeekBar sbRadius = (SeekBar) findViewById(R.id.sb_radius);
        sbRadius.setProgress(mImgPopLayout.getRadius());
        sbRadius.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        mImgPopLayout.setRadius(progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

        SeekBar sbBulge = (SeekBar) findViewById(R.id.sb_bulge);
        sbBulge.setProgress(mImgPopLayout.getBugleSize());
        sbBulge.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        mImgPopLayout.setBulgeSize(progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

        mMenuView = (OptionMenuView) findViewById(R.id.omv_menu);
        mMenuView.setOnOptionMenuClickListener(this);

        SeekBar seekBar = (SeekBar) findViewById(R.id.sb_offset);
        seekBar.setProgress(mPopLayout.getOffset());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPopLayout.setOffset(progress);
                mImgPopLayout.setOffset(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mPopupMenuView = new PopupMenuView(this, R.menu.menu_pop);
        mPopupMenuView.setOnMenuClickListener(this);

        mMenuView.updateAll(Arrays.asList(
                new OptionMenu("复制"), new OptionMenu("转发到朋友圈"),
                new OptionMenu("收藏"), new OptionMenu("翻译"),
                new OptionMenu("删除")));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_orientation:
                if (mMenuView.getOrientation() == LinearLayout.HORIZONTAL) {
                    mMenuView.setOrientation(LinearLayout.VERTICAL);
                    mPopupMenuView.setOrientation(LinearLayout.VERTICAL);
                } else {
                    mMenuView.setOrientation(LinearLayout.HORIZONTAL);
                    mPopupMenuView.setOrientation(LinearLayout.HORIZONTAL);
                }
                break;
            case R.id.btn_show:
            case R.id.btn_show1:
            case R.id.btn_show2:
            case R.id.btn_show3:
                mPopupMenuView.show(v);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio1:
                mPopLayout.setSiteMode(PopLayout.SITE_LEFT);
                mImgPopLayout.setSiteMode(PopLayout.SITE_LEFT);
                mPopupMenuView.setSites(PopupView.SITE_LEFT, PopupView.SITE_TOP, PopupView.SITE_RIGHT, PopupView.SITE_BOTTOM);
                break;
            case R.id.radio2:
                mPopLayout.setSiteMode(PopLayout.SITE_TOP);
                mImgPopLayout.setSiteMode(PopLayout.SITE_TOP);
                mPopupMenuView.setSites(PopupView.SITE_TOP, PopupView.SITE_RIGHT, PopupView.SITE_BOTTOM, PopupView.SITE_LEFT);
                break;
            case R.id.radio3:
                mPopLayout.setSiteMode(PopLayout.SITE_RIGHT);
                mImgPopLayout.setSiteMode(PopLayout.SITE_RIGHT);
                mPopupMenuView.setSites(PopupView.SITE_RIGHT, PopupView.SITE_BOTTOM, PopupView.SITE_LEFT, PopupView.SITE_TOP);
                break;
            case R.id.radio4:
                mPopLayout.setSiteMode(PopLayout.SITE_BOTTOM);
                mImgPopLayout.setSiteMode(PopLayout.SITE_BOTTOM);
                mPopupMenuView.setSites(PopupView.SITE_BOTTOM, PopupView.SITE_LEFT, PopupView.SITE_TOP, PopupView.SITE_RIGHT);
                break;
        }
    }

    @Override
    public boolean onOptionMenuClick(int position, OptionMenu menu) {
        if (menu.getId() == R.id.menu_collect) {
            menu.toggle();
        }
        Toast.makeText(this, menu.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }
}
