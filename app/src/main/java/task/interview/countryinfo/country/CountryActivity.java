package task.interview.countryinfo.country;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import task.interview.countryinfo.R;
import task.interview.countryinfo.di.Injector;
import task.interview.countryinfo.model.Country;

/**
 * Created by atiliorrenja on 09/09/16.
 */
public class CountryActivity extends AppCompatActivity implements CountryContract.View {

    public static final String EXTRA_COUNTRY_NAME = "EXTRA_COUNTRY_NAME";

    @Bind(R.id.country_flag)
    ImageView countryFlag;

    @Bind(R.id.country_region)
    TextView region;

    @Bind(R.id.capital)
    TextView capital;

    @Bind(R.id.population)
    TextView population;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        ButterKnife.bind(this);

        CountryPresenter countryPresenter = new CountryPresenter(this, Injector.provideCountryService());
        countryPresenter.retrieveCountry(getIntent().getStringExtra(EXTRA_COUNTRY_NAME));

        configureLayout();
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCountry(Country country) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String flagUrl = "https://raw.githubusercontent.com/hjnilsson/country-flags/master/png1000px/%s.png";

        region.setText(country.getRegion());
        capital.setText(country.getCapital());
        population.setText(country.getPopulation());
        toolbar.setTitle(country.getName());

        Picasso.with(this)
                .load(String.format(flagUrl, country.getAlpha2Code().toLowerCase()))
                .placeholder(R.drawable.placeholder_flag)
                .fit()
                .centerInside()
                .into(countryFlag);
    }

    private void configureLayout() {
        setSupportActionBar((Toolbar) ButterKnife.findById(this, R.id.toolbar));
    }
}
