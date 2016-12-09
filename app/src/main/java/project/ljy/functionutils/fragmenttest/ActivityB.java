package project.ljy.functionutils.fragmenttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import project.ljy.functionutils.R;

public class ActivityB extends AppCompatActivity implements View.OnClickListener {

    public static final int RESULT_CODE_TEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_b);

        findViewById(R.id.btn_onResult).setOnClickListener(this);
        findViewById(R.id.image1).setOnClickListener(this);
        findViewById(R.id.image2).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_onResult:
                setResult(RESULT_CODE_TEST);
                finish();
                break;
            case R.id.image1:
                Toast.makeText(this,"imageView1 is clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.image2:
                Toast.makeText(this,"imageView2 is clicked",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}

