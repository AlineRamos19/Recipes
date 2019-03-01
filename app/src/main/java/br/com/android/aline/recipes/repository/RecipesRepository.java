package br.com.android.aline.recipes.repository;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import br.com.android.aline.recipes.models.Result;
import br.com.android.aline.recipes.rest.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesRepository {

    private static final String LOG_TAG = RecipesRepository.class.getName();

    private static class SingletonHelper {
        private static final RecipesRepository INSTANCE = new RecipesRepository();
    }

    public static RecipesRepository getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public LiveData<List<Result>>getRecipes() {

        final MutableLiveData<List<Result>> mListResult = new MutableLiveData<>();
        try{
          Call<List<Result>> call = new RetrofitConfig().getApiService().getRecipes();
          call.enqueue(new Callback<List<Result>> () {
                        @Override
                        public void onResponse(Call<List<Result>>  call, Response<List<Result>>  response) {
                            if (response.isSuccessful()) {
                                mListResult.postValue(response.body());
                            }
                        }
                        @Override
                        public void onFailure(Call<List<Result>>  call, Throwable t) {
                            mListResult.postValue(null);
                            Log.e("", t.getMessage());
                        }
                    }
            );

        }catch (Exception e){
            Log.e(LOG_TAG, "Error -- " + e.getMessage());
        }
        return mListResult;
    }

}
