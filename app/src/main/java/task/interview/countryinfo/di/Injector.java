package task.interview.countryinfo.di;

import task.interview.countryinfo.Constants;
import task.interview.countryinfo.data.CountryService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by atiliorrenja on 09/09/16.
 */
public class Injector {

    public static Retrofit provideRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static CountryService provideCountryService() {
        return provideRetrofit(Constants.BASE_URL).create(CountryService.class);
    }

}
