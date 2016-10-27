package cn.thc.anytimeweather.util;

import android.text.TextUtils;
import cn.thc.anytimeweather.model.AnytimeWeatherDB;
import cn.thc.anytimeweather.model.City;
import cn.thc.anytimeweather.model.County;
import cn.thc.anytimeweather.model.Province;

public class Utility {
	public synchronized static boolean handleProvincesResponse(AnytimeWeatherDB anytimeWeatherDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					anytimeWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}

	public synchronized static boolean handleCitiesResponse(AnytimeWeatherDB anytimeWeatherDB, String response,
			int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String c : allCities) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					anytimeWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}

	public synchronized static boolean handleCountiesResponse(AnytimeWeatherDB anytimeWeatherDB, String response,
			int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String c : allCounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					anytimeWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
