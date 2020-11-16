package rasoul.khalouie.dogsapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rasoul.khalouie.dogsapp.R;
import rasoul.khalouie.dogsapp.adapter.DogsListAdapter;
import rasoul.khalouie.dogsapp.databinding.FragmentDetailsBinding;
import rasoul.khalouie.dogsapp.databinding.FragmentListBinding;
import rasoul.khalouie.dogsapp.model.DogBreed;
import rasoul.khalouie.dogsapp.viewmodel.ListFragmentViewModel;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;
    private ListFragmentViewModel viewModel;

    private DogsListAdapter adapter = new DogsListAdapter(new ArrayList<DogBreed>());

    private RecyclerView recyclerDogs;
    private TextView txtError;
    private ProgressBar progressLoading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater,container,false);
        View v = binding.getRoot();
        recyclerDogs = binding.recyclerDogs;
        txtError = binding.txtError;
        progressLoading = binding.progressLoading;
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ListFragmentViewModel.class);
        viewModel.refresh();

        recyclerDogs.setHasFixedSize(true);
        recyclerDogs.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerDogs.setAdapter(adapter);


        observerViewModel();
    }

    private void observerViewModel() {

        viewModel.dogsList.observe(getActivity(), dogs -> {
            if(dogs != null && dogs instanceof List){
                recyclerDogs.setVisibility(View.VISIBLE);
                txtError.setVisibility(View.GONE);
                progressLoading.setVisibility(View.GONE);
                adapter.updateDogsList(dogs);
            }
        });

        viewModel.dogLoadError.observe(getActivity(), isError -> {
            if(isError != null && isError instanceof Boolean){
                txtError.setVisibility(isError ? View.VISIBLE : View.GONE);
                if(isError) {
                    recyclerDogs.setVisibility(View.GONE);
                    progressLoading.setVisibility(View.GONE);
                }
            }
        });

        viewModel.isLoading.observe(getActivity() , isLoading ->{
            if(isLoading != null && isLoading instanceof Boolean){
                progressLoading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if(isLoading) {
                    recyclerDogs.setVisibility(View.GONE);
                    txtError.setVisibility(View.GONE);
                }
            }
        });


    }


}