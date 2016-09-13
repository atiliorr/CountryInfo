package task.interview.countryinfo.country;

import task.interview.countryinfo.data.CountryService;
import task.interview.countryinfo.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by atiliorrenja on 09/09/16.
 */
public class CountryPresenter {

    private final CountryContract.View countryView;
    private final CountryService service;

    public CountryPresenter(CountryContract.View countryView, CountryService service) {
        this.countryView = countryView;
        this.service = service;
    }

    public void retrieveCountry(String country) {
        service.getCountry(country).enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful()) {
                    countryView.showCountry(response.body().get(0));
                    Timber.i("Country data was loaded from API.");
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                countryView.showErrorMessage();
                Timber.e(t, "Unable to load the country data from API.");
            }
        });
    }
}
