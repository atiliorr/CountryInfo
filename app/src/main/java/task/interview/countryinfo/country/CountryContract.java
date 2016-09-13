package task.interview.countryinfo.country;

import task.interview.countryinfo.model.Country;

/**
 * Created by atiliorrenja on 09/09/16.
 */
public class CountryContract {

    interface View {
        void showErrorMessage();

        void showCountry(Country country);
    }

}
