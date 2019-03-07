package txw.com.whatnew.card;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import txw.com.whatnew.R;


/**
 * CardView
 * create by txw on 2018-06-07
 */
public class CardActivity extends AppCompatActivity {


    @BindView(R.id.cv_info)
    CardView mCvInfo;

    @BindView(R.id.sb_corner)
    SeekBar mSbCorner;

    @BindView(R.id.sb_elevation)
    SeekBar mSbElevation;

    @BindView(R.id.sb_content_padding)
    SeekBar mSbContentPadding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * init View
     */
    private void initView() {
        //控制圆角大小
        mSbCorner.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCvInfo.setRadius(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //控制Z轴阴影
        mSbElevation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCvInfo.setCardElevation(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //控制图片间距
        mSbContentPadding.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCvInfo.setContentPadding(progress, progress, progress, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
