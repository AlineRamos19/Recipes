package br.com.android.aline.recipes.rest;

import java.util.List;

import br.com.android.aline.recipes.models.Result;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IRetrofitService {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Result>> getRecipes();

}
