package task.interview.countryinfo.countries;

import java.util.List;

import task.interview.countryinfo.model.Country;

/**
 * Created by atiliorrenja on 09/09/16.
 */
public class CountriesContract {

    interface View {
        void showErrorMessage();

        void showCountries(List<Country> countries);
    }

}