package task.interview.countryinfo.countries;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import task.interview.countryinfo.R;
import task.interview.countryinfo.model.Country;

/**
 * Created by atiliorrenja on 09/09/16.
 */
public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {

    private WeakReference<Context> context;
    private List<Country> countries;
    private CountryItemListener itemListener;

    public void updateCountries(List<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    public CountriesAdapter(Context context, List<Country> countries, CountryItemListener itemListener) {
        this.context = new WeakReference<>(context);
        this.countries = countries;
        this.itemListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.country_name)
        TextView countryName;
        @Bind(R.id.country_region)
        TextView countryRegion;
        @Bind(R.id.flag_image)
        ImageView flagImage;

        CountryItemListener itemListener;

        public ViewHolder(View itemView, CountryItemListener itemListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.itemListener = itemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Country country = getItem(getAdapterPosition());
            this.itemListener.onCountryClick(country.getName());
        }
    }

    @Override
    public CountriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_row_item, parent, false);

        return new ViewHolder(v, this.itemListener);
    }

    @Override
    public void onBindViewHolder(CountriesAdapter.ViewHolder holder, int position) {
        String flagUrl = "https://raw.githubusercontent.com/hjnilsson/country-flags/master/png1000px/%s.png";

        Country country = countries.get(position);

        holder.countryName.setText(country.getName());
        holder.countryRegion.setText(country.getRegion());
        Context contextLocal = context.get();
        if (contextLocal != null) {
            Picasso.with(contextLocal)
                    .load(String.format(flagUrl, country.getAlpha2Code().toLowerCase()))
                    .fit()
                    .error(R.drawable.placeholder_flag)
                    .centerInside()
                    .into(holder.flagImage);
        }
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    private Country getItem(int adapterPosition) {
        return countries.get(adapterPosition);
    }

    public interface CountryItemListener {
        void onCountryClick(String country);
    }
}
