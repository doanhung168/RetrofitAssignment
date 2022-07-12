package com.example.retrofitassignment.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.retrofitassignment.R;
import com.example.retrofitassignment.databinding.FragmentMarsPropertyListBinding;
import com.example.retrofitassignment.network.data.model.MarsProperty;
import com.example.retrofitassignment.view.adapter.MarsPropertyAdapter;
import com.example.retrofitassignment.viewmodel.MainViewModel;


public class MarsPropertyListFragment extends Fragment implements MarsPropertyAdapter.OnClickItemListener {

    public static final String TAG = "MarsPropertyList";
    private FragmentMarsPropertyListBinding mBinding;
    private MainViewModel mMainViewModel;
    private MarsPropertyAdapter mMarsPropertyAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMarsPropertyListBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(R.string.marsproperty_list);
        setHasOptionsMenu(true);

        mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mBinding.setLifecycleOwner(this);
        mBinding.setViewModel(mMainViewModel);

        setUpRcvMarsProperty();
    }

    private void setUpRcvMarsProperty() {
        mMainViewModel.loadMarsPropertyList();

        mBinding.rcvMarsProperties.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        mMarsPropertyAdapter = new MarsPropertyAdapter(MarsProperty.diffCallback, this);
        mBinding.rcvMarsProperties.setAdapter(mMarsPropertyAdapter);

        mMainViewModel.getMarsPropertyList().observe(
                requireActivity(),
                marsProperties -> mMarsPropertyAdapter.submitList(marsProperties)
        );
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all: {
                mMainViewModel.loadMarsPropertyList();
                break;
            }
            case R.id.buy: {
                mMainViewModel.loadMarsPropertyList("buy");
                break;
            }
            case R.id.rent: {
                mMainViewModel.loadMarsPropertyList("rent");
                break;
            }
            default:
                break;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    @Override
    public void onClickItem(MarsProperty marsProperty) {
        mMainViewModel.setCurrentMarsProperty(marsProperty);
        getParentFragmentManager()
                .beginTransaction()
                .add(R.id.container, new MarsPropertyDetailFragment(), MarsPropertyDetailFragment.TAG)
                .addToBackStack(MarsPropertyDetailFragment.TAG)
                .commit();
    }
}