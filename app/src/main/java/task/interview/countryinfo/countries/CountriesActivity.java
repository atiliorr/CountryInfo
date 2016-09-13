package task.interview.countryinfo.countries;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import task.interview.countryinfo.R;
import task.interview.countryinfo.country.CountryActivity;
import task.interview.countryinfo.di.Injector;
import task.interview.countryinfo.model.Country;
import timber.log.Timber;

import static task.interview.countryinfo.country.CountryActivity.EXTRA_COUNTRY_NAME;

/**
 * Created by atiliorrenja on 09/09/16.
 */
public class CountriesActivity extends AppCompatActivity implements CountriesContract.View {

    private CountriesAdapter countriesAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        CountriesPresenter countriesPresenter = new CountriesPresenter(this, Injector.provideCountryService());
        countriesAdapter = new CountriesAdapter(this, new ArrayList<Country>(0), itemListener);

        countriesPresenter.initDataSet();

        configureLayout();

    }

    private void configureLayout() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(countriesAdapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCountries(List<Country> countries) {
        countriesAdapter.updateCountries(countries);
    }

    private CountriesAdapter.CountryItemListener itemListener = new CountriesAdapter.CountryItemListener() {
        @Override
        public void onCountryClick(String country) {
            Timber.d("Country clicked id: %d", country);
            Intent intent = new Intent(CountriesActivity.this, CountryActivity.class);
            intent.putExtra(EXTRA_COUNTRY_NAME, country);
            startActivity(intent);
        }
    };
}
