package project.ljy.functionutils.fragmenttest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import project.ljy.functionutils.fragmenttest.ActivityB;
import project.ljy.functionutils.R;

public class FragmentA extends Fragment implements View.OnClickListener {

    private TextView mShowResultTxt = null;
    private ImageView mImageOne = null;
    private ImageView mImageTwo = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mShowResultTxt = (TextView) view.findViewById(R.id.tv_show_result);
        view.findViewById(R.id.btn_toFragmentB).setOnClickListener(this);
        view.findViewById(R.id.image1).setOnClickListener(this);
        view.findViewById(R.id.image2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toFragmentB:
                getFragmentManager().beginTransaction().replace(R.id.fl_fragment_container,new FragmentB()).commit();
                break;
            case R.id.image1:
                Toast.makeText(getActivity(),"imageView1 is clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.image2:
                Toast.makeText(getActivity(),"imageView2 is clicked",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case FragmentB.REQUEST_CODE_TEST:
                if(resultCode == ActivityB.RESULT_CODE_TEST){
                    mShowResultTxt.setText("the result has change");
                }
                break;
            default:
                break;
        }
    }
}
