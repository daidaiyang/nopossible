package com.nopossible.activities.main.first;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.sunflower.FlowerCollector;
import com.nopossible.R;
import com.nopossible.activities.onekeysaveorder.OnekeysaveorderActivity;
import com.nopossible.activities.search.SearchActivity;
import com.nopossible.activities.search.SearchResultBus;
import com.nopossible.customview.CircleImageView;
import com.nopossible.customview.WaveView;
import com.nopossible.dialog.RecognitionDialog;
import com.nopossible.mvp.MVPBaseFragment;
import com.nopossible.utils.IntentUtil;
import com.nopossible.utils.JsonParser;
import com.nopossible.utils.LogUtil;
import com.nopossible.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FirstFragment extends MVPBaseFragment<FirstContract.View, FirstPresenter> implements FirstContract.View {


    Unbinder unbinder;
    @BindView(R.id.first_location_icon)
    ImageView firstLocationIcon;
    @BindView(R.id.first_location_txt)
    TextView firstLocationTxt;
    @BindView(R.id.first_record_txt1)
    TextView firstRecordTxt1;
    @BindView(R.id.first_record_txt2)
    TextView firstRecordTxt2;
    @BindView(R.id.first_record_low_icon)
    ImageView firstRecordLowIcon;
    @BindView(R.id.first_record_icon)
    WaveView firstRecordIcon;
    @BindView(R.id.first_record_circle)
    CircleImageView firstRecordCircle;
    @BindView(R.id.first_record_icon_img)
    ImageView firstRecordIconImg;
    @BindView(R.id.first_function_scan_icon)
    ImageView firstFunctionScanIcon;
    @BindView(R.id.first_function_scan_txt)
    TextView firstFunctionScanTxt;
    @BindView(R.id.first_function_scan)
    LinearLayout firstFunctionScan;
    @BindView(R.id.first_function_order_icon)
    ImageView firstFunctionOrderIcon;
    @BindView(R.id.first_function_order_txt)
    TextView firstFunctionOrderTxt;
    @BindView(R.id.first_function_order)
    LinearLayout firstFunctionOrder;
    @BindView(R.id.first_search_view)
    LinearLayout firstSearchView;

    private Animation animation = null;
    private Animator animationJump = null;
    private long downTime;
    public static final int REQUEST_CODE_SCAN = 0x001;
    // 语音听写对象
    private SpeechRecognizer mIat;
    private static String TAG = FirstFragment.class.getSimpleName();
    private StringBuffer buffer = new StringBuffer();
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
    private String resultType = "json";
    int ret = 0; // 函数调用返回值
    public RecognitionDialog dialog = null;
    private String mResult = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        // 初始化识别无UI识别对象
        // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
        mIat = SpeechRecognizer.createRecognizer(getActivity(), mInitListener);
        return view;
    }

    /**
     * 初始化layout
     */
    private void initView() {
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.image_scale);
        animationJump = AnimatorInflater.loadAnimator(getContext(), R.animator.heart_jump);
        animationJump.setTarget(firstRecordCircle);
        firstRecordIcon.setDuration(2000);
        firstRecordIcon.setInitialRadius(getContext().getResources().getDimension(R.dimen.x280) / 2.0f);
        firstRecordIcon.setMaxRadius(getContext().getResources().getDimension(R.dimen.x360) / 2.0f);
        firstRecordIcon.setStyle(Paint.Style.FILL);
        firstRecordIcon.setColor(Color.parseColor("#FEEE94"));
        firstRecordIcon.setInterpolator(new LinearOutSlowInInterpolator());
        firstRecordIcon.start();
        firstRecordIconImg.setOnTouchListener(touchLis);
    }


    private void toSearch(String result) {
        Bundle bundle = new Bundle();
        bundle.putString("key",result);
        IntentUtil.startActivity(getContext(), SearchActivity.class,bundle);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.first_function_scan, R.id.first_function_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.first_function_scan:
                mPresenter.startScan();
                break;
            case R.id.first_function_order:
                Intent intent = new Intent(getContext(), OnekeysaveorderActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    public void startScan(Intent intentScan) {
        startActivityForResult(intentScan, REQUEST_CODE_SCAN);
    }


    /**
     * 录音按钮触摸事件
     */
    View.OnTouchListener touchLis = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (null == mIat) {
                // 创建单例失败，与 21001 错误为同样原因，参考 http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=9688
                ToastUtil.showBottomToast("创建对象失败，请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化");
            } else {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 移动数据分析，收集开始听写事件
                        FlowerCollector.onEvent(getContext(), "iat_recognize");
                        buffer.setLength(0);
                        //清空内容
                        mResult = "";
                        mIatResults.clear();
                        //设置参数
                        mPresenter.setparam(mIat);
                        ret = mIat.startListening(mRecognizerListener);
                        if (ret != ErrorCode.SUCCESS) {
                            ToastUtil.showBottomToast("听写失败,错误码：" + ret);
                        } else {
                            ToastUtil.showBottomToast("请开始说话…");
                        }
                        //动画
                        downTime = System.currentTimeMillis();
                        animation.setFillAfter(true);
                        firstRecordIconImg.startAnimation(animation);
                        firstRecordIcon.stopImmediately();
                        firstRecordCircle.setVisibility(View.VISIBLE);
                        animationJump.start();
                        break;
                    case MotionEvent.ACTION_UP:
                        firstRecordCircle.setVisibility(View.GONE);
                        animationJump.cancel();
                        firstRecordIconImg.clearAnimation();
                        firstRecordIcon.start();
                        if (System.currentTimeMillis() - downTime > 500) {
                            showDialog();
                            mIat.stopListening();
                        } else {
                            mIat.cancel();
                        }
                        break;
                }
            }
            return true;
        }
    };


    /**
     * 显示识别中弹框
     */
    public void showDialog() {
        if (dialog == null) {
            dialog = new RecognitionDialog(getContext());
        }
        dialog.show();
        dialog.setCloseListener(new RecognitionDialog.CloseListener() {
            @Override
            public void close() {
                dialog.cancel();
                mIat.stopListening();
            }
        });
    }


    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                ToastUtil.showBottomToast("初始化失败，错误码：" + code);
            }
        }
    };

    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
//            ToastUtil.showBottomToast("开始说话");
        }

        @Override
        public void onError(SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            ToastUtil.showBottomToast(error.getPlainDescription(true));
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
//            ToastUtil.showBottomToast("结束说话");
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d(TAG, results.getResultString());
            if (resultType.equals("json")) {
                printResult(results);
            }
            dialog.cancel();
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            Log.d(TAG, "返回音频数据：" + data.length);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };

    /**
     * 识别结果
     *
     * @param results
     */
    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }

        mResult = resultBuffer.toString();
        toSearch(mResult);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String scanContext = data.getStringExtra("codedContent");
                LogUtil.d("二维码扫描结果：", scanContext);
                ToastUtil.showCenterToast(getContext(), scanContext);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
