package cn.thc.anytimeweather.util;

public interface HttpCallbackListener {
	void onFinish(String response);

	void onError(Exception e);
}
