package org.xione.piservice;

public interface PiFetchService {

	public void fetchPictureByKey(String key);

	public void fetchPictureByKey(String url, String key);

}
