package task.interview.countryinfo.countries;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import task.interview.countryinfo.data.CountryService;
import task.interview.countryinfo.model.Country;
import timber.log.Timber;

/**
 * Created by atiliorrenja on 09/09/16.
 */
public class CountriesPresenter {

    private final CountriesContract.View countryView;
    private final CountryService service;

    public CountriesPresenter(CountriesContract.View countryView, CountryService service) {
        this.countryView = countryView;
        this.service = service;
    }

    public void initDataSet() {
        service.getCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful()) {
                    countryView.showCountries(response.body());
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
