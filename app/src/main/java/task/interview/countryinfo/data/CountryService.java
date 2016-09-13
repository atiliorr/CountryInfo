package task.interview.countryinfo.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import task.interview.countryinfo.model.Country;

/**
 * Created by atiliorrenja on 09/09/16.
 */
public interface CountryService {

    @GET("all")
    Call<List<Country>> getCountries();

    @GET("name/{country}")
    Call<List<Country>> getCountry(@Path("country") String country);
}
