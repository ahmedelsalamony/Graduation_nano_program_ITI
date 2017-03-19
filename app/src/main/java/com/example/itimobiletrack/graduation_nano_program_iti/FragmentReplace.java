package com.example.itimobiletrack.graduation_nano_program_iti;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by Mahmoud on 19/03/2017.
 */

public class FragmentReplace extends Fragment {



    public  FragmentReplace(){



    }


    public FragmentReplace(int containner , Fragment fragment)
    {

         FragmentManager fm =getFragmentManager();
         FragmentTransaction  ft=fm.beginTransaction();
         ft.replace(containner,fragment);
         ft.addToBackStack(null);
         ft.commit();



    }




}
