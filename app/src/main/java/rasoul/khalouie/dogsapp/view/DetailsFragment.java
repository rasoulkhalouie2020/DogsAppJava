package rasoul.khalouie.dogsapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import rasoul.khalouie.dogsapp.R;
import rasoul.khalouie.dogsapp.databinding.FragmentDetailsBinding;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    private int dogUId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.floatingActionButton2.setOnClickListener(bView -> onGoToList());
        dogUId = DetailsFragmentArgs.fromBundle(getArguments()).getDogUId();
        if (dogUId != 0) {
            Toast.makeText(getContext(), dogUId+"", Toast.LENGTH_SHORT).show();
        }
    }

    private void onGoToList() {
        Navigation.findNavController(binding.floatingActionButton2).navigate(R.id.listAction);
    }
}