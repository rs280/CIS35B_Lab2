package adapter;

public interface CreateAuto {
	public String buildAuto(String filename);
	public boolean printAuto(String automobileKey);
	public boolean serialize(String automobileKey, String fileName);
	public String deserialize(String fileName);
}
