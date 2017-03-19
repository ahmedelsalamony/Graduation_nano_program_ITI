 package com.example.itimobiletrack.graduation_nano_program_iti.Charity;

        import android.app.FragmentManager;
        import android.app.FragmentTransaction;
        import android.os.Bundle;
        import android.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.example.itimobiletrack.graduation_nano_program_iti.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        final String[] requests = null;

        final ListView requests_list = (ListView) view.findViewById(R.id.home_requests);


        if(requests == null ){

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.content_main,new DefaultNoRequestsFragment());
            ft.commit();

        }else {
            ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, requests);

            requests_list.setAdapter(listViewAdapter);
        }

        requests_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),""+requests[position],Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    /*this method will be called when deleting an member*/
    public void deleteItem(int memberPosition){

    }

    /*this method will be called when adding new member*/
    public void addMember(){

    }


}
