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
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import project.ljy.clicknablespantext.ClicknableSpanTextView;

public class MuiltEdtActivity extends AppCompatActivity {

    private EditText mInputEdt;

    private ClicknableSpanTextView mSpanTextView ;

    //身份证输入inputFilter
    private InputFilter mInputFilter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Log.i("testFilter" , "source : " + source + " dest:" + dest);
            Log.i("testLocation" ,"source:" + start + "," + end + "  dest:" + dstart + "," + dend);
            String str = dest.toString().replace(" " , "");
            if(str.length() == 17){
                //当为数字时直接填充
                Pattern pattern = Pattern.compile("[0-9]");
                Matcher match = pattern.matcher(source);
                if(match.find()){
                    //匹配的字符串是单个字符的，group只返回一个字符
                    return match.group();
                }
                //最后一位且为X时填充
                if(dstart == str.length() && (source.equals("X")||source.equals("x"))){
                    //最后一个字母
                    return null;
                } else {
                    return "";
                }
            } else if(str.length() < 17){
                //取的是包含多个字符的字符串
                Pattern pattern = Pattern.compile("\\d+");
                Matcher match = pattern.matcher(source);
                if(match.find()){
                    //求还剩多少位可填
                    int requireNum = 18 - str.length();
                    int matchNum = match.end() - match.start();
                    if(requireNum >= matchNum){
                        //当剩余位数大于填入的字符串时，直接填入
                        return source.subSequence(match.start(),match.end());
                    } else {
                        return source.subSequence(match.start(),match.start() + requireNum);
                    }
                } else {
                    return "";
                }
            } else if(str.length() >= 18){
                return "";
            }
            return "";
        }
    };

    private class MyTextWatcher implements TextWatcher{
        private static final int TOTAL_SYMBOLS = 19; // size of pattern 0000-0000-0000-0000
        private static final int TOTAL_DIGITS = 16; // max numbers of digits in pattern: 0000 x 4
        private static final int DIVIDER_MODULO = 5; // means divider position is every 5th symbol beginning with 1
        private static final int DIVIDER_POSITION = DIVIDER_MODULO - 1; // means divider position is every 4th symbol beginning with 0
        private static final char DIVIDER = ' ';

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // noop
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // noop
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!isInputCorrect(s, TOTAL_SYMBOLS, DIVIDER_MODULO, DIVIDER)) {
                s.replace(0, s.length(), buildCorrecntString(getDigitArray(s, TOTAL_DIGITS), DIVIDER_POSITION, DIVIDER));
            }
        }

        private boolean isInputCorrect(Editable s, int totalSymbols, int dividerModulo, char divider) {
            boolean isCorrect = s.length() <= totalSymbols; // check size of entered string
            for (int i = 0; i < s.length(); i++) { // chech that every element is right
                if (i > 0 && (i + 1) % dividerModulo == 0) {
                    isCorrect &= divider == s.charAt(i);
                } else {
                    isCorrect &= Character.isDigit(s.charAt(i));
                }
            }
            return isCorrect;
        }

        private String buildCorrecntString(char[] digits, int dividerPosition, char divider) {
            final StringBuilder formatted = new StringBuilder();

            for (int i = 0; i < digits.length; i++) {
                if (digits[i] != 0) {
                    formatted.append(digits[i]);
                    if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {
                        formatted.append(divider);
                    }
                }
            }

            return formatted.toString();
        }

        private char[] getDigitArray(final Editable s, final int size) {
            char[] digits = new char[size];
            int index = 0;
            for (int i = 0; i < s.length() && index < size; i++) {
                char current = s.charAt(i);
                if (Character.isDigit(current)) {
                    digits[index] = current;
                    index++;
                }
            }
            return digits;
        }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MuiltEdtActivity.this,"你点击了" , Toast.LENGTH_SHORT).show();
        }
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muilt_edt);
        TextView mSpanTxt = (TextView) findViewById(R.id.tv_span);
        mInputEdt = (EditText) findViewById(R.id.et_input);
        mSpanTextView = (ClicknableSpanTextView) findViewById(R.id.view_click_link_txt);
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
//        mInputEdt.getEditableText().setFilters(new InputFilter[]{mInputFilter});
        mInputEdt.setInputType(InputType.TYPE_CLASS_NUMBER);
        mInputEdt.addTextChangedListener(new MyTextWatcher());

        mSpanTextView.setSpanClickListener(mOnClickListener);
        mSpanTextView.setSpanText("《400-1201-1221天天》部分可点击"  , 0 , "《400-1201-1221天天》".length());
    }
}
