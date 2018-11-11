package com.example.shafaet.fragmenttest;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuDetailFragment extends Fragment {

    private long menuId;

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public MenuDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState != null){
            menuId = savedInstanceState.getLong("menuId");
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view != null){
            TextView textViewName = view.findViewById(R.id.id_tv_name);
            TextView textViewDescription = view.findViewById(R.id.id_tv_description);
            Menu menu = Menu.menus[(int) menuId];
            textViewName.setText(menu.getName().toString());
            textViewDescription.setText(menu.getDescription().toString());
        }
    }
}

