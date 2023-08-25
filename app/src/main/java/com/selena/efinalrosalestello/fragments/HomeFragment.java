package com.selena.efinalrosalestello.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.selena.efinalrosalestello.data.response.ShowResponse;
import com.selena.efinalrosalestello.data.retrofit.RetrofitHelper;
import com.selena.efinalrosalestello.databinding.FragmentHomeBinding;
import com.selena.efinalrosalestello.model.Cat;
import com.selena.efinalrosalestello.model.Shows;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())
                .create(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        RVResumeAdapter adapter = new RVResumeAdapter(getData());
        binding.rvCatResume.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvCatResume.setLayoutManager(layoutManager);


        RetrofitHelper.getService().getShow().enqueue(new Callback<ShowResponse>() {
            @Override
            public void onResponse(Call<ShowResponse> call, Response<ShowResponse> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    showMovies(response.body().getShowsList());
                }
            }

            @Override
            public void onFailure(Call<ShowResponse> call, Throwable t) {

            }
        });
        homeViewModel.listLiveData.observe(requireActivity(), showList ->{
            for (int i = 0 ; i < showList.size() ; i++){
                Log.d("Shows", showList.get(i).getName());
            }
        });
        homeViewModel.getShows();
    }


    private void showMovies(List<Shows> showsList) {
        RVShowAdapter adapter = new RVShowAdapter(showsList, show -> {
            homeViewModel.addShow(show);
        });
        binding.rvShows.setAdapter(adapter);
    }

    private List<Shows> getData() {
        List<Shows> shows = new ArrayList<>();
        shows.add(new Cat("Gato siamés", "https://www.barakaldotiendaveterinaria.es/blog/wp-content/uploads/2019/01/siames.jpg","15","Si"));
        shows.add(new Cat("Gato de Angora", "https://www.barakaldotiendaveterinaria.es/blog/wp-content/uploads/2019/01/angora.jpg", "12","Si"));
        shows.add(new Cat("Gato abisinio", "https://www.barakaldotiendaveterinaria.es/blog/wp-content/uploads/2019/01/Gato-abisinio.jpg", "6","Si"));
        shows.add(new Cat("Gato Maine coon", "https://www.barakaldotiendaveterinaria.es/blog/wp-content/uploads/2019/01/gato-maine-koon.jpg", "9","Si"));
        shows.add(new Cat("Gato ragdoll", "https://www.barakaldotiendaveterinaria.es/blog/wp-content/uploads/2019/01/ragdoll.jpg", "2","Si"));
        shows.add(new Cat("Gato siberiano", "https://www.barakaldotiendaveterinaria.es/blog/wp-content/uploads/2019/01/gato-siberiano.jpg", "3","Si"));
        shows.add(new Cat("Gato birmano", "https://www.barakaldotiendaveterinaria.es/blog/wp-content/uploads/2019/01/Gato-birmano.jpg", "6","Si"));
        shows.add(new Cat("Gato mau egipcio", "https://www.barakaldotiendaveterinaria.es/blog/wp-content/uploads/2019/01/gato-mau-egipcio.jpg", "15","Si"));
        return shows;
    }
}


