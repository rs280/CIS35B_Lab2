package adapter;

public interface UpdateAuto {
	public boolean updateOptionSetName(String automobileKey, String optionSetName, String nameNew);
	public boolean updateOptionPrice(String automobileKey, String optionSetName, String optionName, double priceNew);
}
