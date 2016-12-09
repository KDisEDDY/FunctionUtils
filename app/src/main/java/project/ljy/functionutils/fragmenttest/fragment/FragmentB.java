package project.ljy.functionutils.fragmenttest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import project.ljy.functionutils.fragmenttest.ActivityB;
import project.ljy.functionutils.R;

public class FragmentB extends Fragment implements View.OnClickListener {

    public static final int REQUEST_CODE_TEST = 1;

    private TextView tvShowResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvShowResult = (TextView) view.findViewById(R.id.tv_show_result);
        view.findViewById(R.id.btn_toActivityB).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toActivityB:
                getActivity().startActivityForResult(new Intent(getActivity(), ActivityB.class),REQUEST_CODE_TEST);
                getFragmentManager().beginTransaction().replace(R.id.fl_fragment_container,new FragmentA()).commit();
                break;
        }
    }
}
