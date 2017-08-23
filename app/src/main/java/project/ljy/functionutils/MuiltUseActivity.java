package project.ljy.functionutils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MuiltUseActivity extends AppCompatActivity {

    public static final String TYPE = "type";
    public static final int type_MulitUseA = 1;
    public static final int type_MulitUseB = 2;
    private int type = 0;
    private BaseMuiltUseClass mMulitUse;
    private EditText mInputEdt;
    private Button mPrintBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muilt_use);
        mInputEdt = (EditText) findViewById(R.id.et_input);
        mPrintBtn = (Button) findViewById(R.id.btn_print_toast);
        mPrintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMulitUse.clickAction();
            }
        });
        type = getIntent().getIntExtra(TYPE,0);
        if(type == type_MulitUseA){
            mMulitUse = new MuiltUseAImpl(type);
        } else {
            mMulitUse = new MuiltUseBImpl(type);
        }
        initView(mMulitUse);
    }

    private void initView(@NonNull BaseMuiltUseClass mMulitUse){
        mMulitUse.initView();
    }

    private class MuiltUseAImpl extends BaseMuiltUseClass{

        MuiltUseAImpl(int type) {
            super(type);
        }

        @Override
        void initView() {
            mPrintBtn.setText("输出Toast" + type);
            mInputEdt.setHint("mulitUseA 的 hint");
        }

        @Override
        void clickAction() {
            Toast.makeText(MuiltUseActivity.this,"mulitUseA input :" + mInputEdt.getText(),Toast.LENGTH_SHORT).show();
        }
    }

    private class MuiltUseBImpl extends BaseMuiltUseClass{

        MuiltUseBImpl(int type) {
            super(type);
        }

        @Override
        void initView() {
            mPrintBtn.setText("输出Toast" + type);
            mInputEdt.setHint("mulitUseB 的 hint");
        }

        @Override
        void clickAction() {
            Toast.makeText(MuiltUseActivity.this,"mulitUseB input :" + mInputEdt.getText(),Toast.LENGTH_SHORT).show();
        }
    }

    abstract class BaseMuiltUseClass{
        int type = 0;

        BaseMuiltUseClass(int type) {
            this.type = type;
        }

        abstract void initView();

        abstract void clickAction();
    }
}
