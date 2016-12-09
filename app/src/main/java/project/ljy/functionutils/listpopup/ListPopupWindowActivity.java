package project.ljy.functionutils.listpopup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import project.ljy.functionutils.R;
import widget.DatePickerPopupWindow;

public class ListPopupWindowActivity extends AppCompatActivity {

    private DatePickerPopupWindow datePickerPopupWindow = null;
    private RelativeLayout rllayoutroot = null;
    private TextView tvlistpopuppoplist = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_popup_window);
        init();
        initListener();
    }

    private void initListener() {
        ClickListener clickListener = new ClickListener();
        tvlistpopuppoplist.setOnClickListener(clickListener);
    }

    private void init() {
        this.rllayoutroot = (RelativeLayout) findViewById(R.id.rl_layout_root);
        this.tvlistpopuppoplist = (TextView) findViewById(R.id.tv_listpopup_poplist);
    }

    class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_listpopup_poplist:
                    if (datePickerPopupWindow != null && datePickerPopupWindow.isShowing()) {
                        datePickerPopupWindow.close();
                    } else {
                        if (datePickerPopupWindow == null) {
                            datePickerPopupWindow = new DatePickerPopupWindow(null,ListPopupWindowActivity.this,720,300);
                        }
                        datePickerPopupWindow.popWindowAtBottom(tvlistpopuppoplist);
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
