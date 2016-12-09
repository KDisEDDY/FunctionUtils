package project.ljy.functionutils.fragmenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import project.ljy.functionutils.R;
import project.ljy.functionutils.fragmenttest.fragment.FragmentA;

public class MyFragmentActivity extends FragmentActivity {
    private FragmentA mFragmentA = null;

    private FragmentA mFragmentAClone = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);
        mFragmentA = new FragmentA();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_fragment_container,mFragmentA);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(mFragmentA != null){
            mFragmentA.onActivityResult(requestCode,resultCode,data);
        }
    }
}
