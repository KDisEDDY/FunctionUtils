package project.ljy.functionutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.SubscriptSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MuiltEdtActivity extends AppCompatActivity {

    private EditText mInputEdt;

    //身份证输入inputFilter
    private InputFilter mInputFilter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Log.i("testFilter" , "source : " + source + " dest:" + dest);
            Log.i("testLocation" ,"source:" + start + "," + end + "  dest:" + dstart + "," + dend);

            if(dest.length() == 17){
                //当为数字时直接填充
                Pattern pattern = Pattern.compile("[0-9]");
                Matcher match = pattern.matcher(source);
                if(match.find()){
                    return null;
                }
                //最后一位且为X时填充
                if(dstart == dest.toString().length() && (source.equals("X")||source.equals("x"))){
                    //最后一个字母
                    return null;
                } else {
                    return "";
                }
            } else if(dest.length() < 17){
                Pattern pattern = Pattern.compile("[^0-9]");
                Matcher match = pattern.matcher(source);
                if(match.find()){
                    return "";
                } else {
                    return null;
                }
            } else if(dest.length() >= 18){
                return "";
            }
            return "";
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muilt_edt);
        TextView mSpanTxt = (TextView) findViewById(R.id.tv_span);
        mInputEdt = (EditText) findViewById(R.id.et_input);
        //创建SpannableString实例，配置要设置spannable的文字，这里SpannableString和SpannableStringBuilder都可以
        SpannableStringBuilder spanBuilder = new SpannableStringBuilder("怒发冲冠，凭阑处、潇潇雨歇。抬望眼、仰天长啸，壮怀激烈。三十功名尘与土，" +
                "八千里路云和月。莫等闲，白了少年头，空悲切。\n");
        //setSpan有四个参数：1.要用到的修饰类；2.开始的字节；3.结束的字节；4.设置修饰的包含特性，Spanned有包含不包含两种特性
        spanBuilder.setSpan(new UnderlineSpan(), 0, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spanBuilder.setSpan(new RelativeSizeSpan(2f), 8, 10, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spanBuilder.append("靖康1之耻，犹未雪；臣子恨，何时灭。驾长车，踏破贺兰山缺。壮志饥餐胡虏肉，笑谈渴饮匈奴血。待从头、收拾旧山河，朝天阙");
        spanBuilder.setSpan(new StrikethroughSpan(), 62, 63, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanBuilder.setSpan(new SubscriptSpan(), 61, 62, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spanBuilder.setSpan(new AbsoluteSizeSpan(8, true), 61, 62, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //setText到需要的TextView里面
        mSpanTxt.setText(spanBuilder);

        mInputEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                s.setFilters(new InputFilter[]{mInputFilter});
            }
        });
    }
}
