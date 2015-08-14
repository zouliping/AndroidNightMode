package example.org.androidnightmode.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.org.androidnightmode.R;

public class NewFragment extends Fragment {

    private TextView mTitleTV;

    public static NewFragment getInstance(String title) {
        NewFragment newFragment = new NewFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        newFragment.setArguments(args);

        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        mTitleTV = (TextView) view.findViewById(R.id.new_text);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        String title = null;
        if (args != null) {
            title = args.getString("title");
        }

        if (!TextUtils.isEmpty(title)) {
            mTitleTV.setText(title);
        }
    }

}
