package com.example.windows10.ts2;

import android.app.Fragment;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    public static final int PAGE_1 = 1;
    public static final int PAGE_2 = 2;

    private Fragment1 fragment1;
    private Fragment2 fragment2;

    private FragmentManager fragmentManager;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);

        fragment1 = Fragment1.newInstance(presenter, this, "FRAGMENT 1");
        fragment2 = Fragment2.newInstance(presenter, this, "FRAGMENT 2");
        this.fragmentManager = getSupportFragmentManager();
        changePage(PAGE_1);
    }

    public void updateSpinner() {
        fragment1.updateSpinner(presenter.getAgency());
    }

    public void updateRoute() {
        fragment1.updateRoute(presenter.getRoutes());
    }

    public void updateStop() {
        fragment2.updateStop(presenter.getStops());
    }

    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if (page == PAGE_1) {
            if (fragment1.isAdded()) {
                ft.show(fragment1);
            } else {
                ft.add(R.id.container, fragment1).addToBackStack("FRAGMENT1");
                ft.show(fragment1);
            }
            ft.hide(fragment2);
        } else if (page == PAGE_2) {
            if (fragment2.isAdded()) {
                ft.show(fragment2);
            } else {
                ft.add(R.id.container, fragment2).addToBackStack("FRAGMENT2");
                ft.show(fragment2);
            }
            ft.hide(fragment1);
        }
        ft.commit();
    }
}
