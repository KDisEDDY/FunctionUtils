package eddy.bitmapandcamerafunction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_bitmap_function:
                startActivity(new Intent(this,BitmapActivity.class));
                break;
            case R.id.btn_camera_function:
                break;
        }
    }
}
