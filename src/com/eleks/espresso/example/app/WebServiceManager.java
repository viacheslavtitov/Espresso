package com.eleks.espresso.example.app;

public class WebServiceManager
{

	private static final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

	private static WebServiceManager _serviceManager;
	private Builder mBuilder;

	public WebServiceManager()
	{
		mBuilder = Builder.getInstance();
	}

	public static WebServiceManager getInstance()
	{
		if (null == _serviceManager)
		{
			_serviceManager = new WebServiceManager();
		}
		return _serviceManager;
	}

	public void setBuilder(Builder builder)
	{
		mBuilder = builder;
	}

	public Builder getBuilder()
	{
		return mBuilder;
	}

	public final static class Builder
	{
		private HttpTransport mHttpTransport;

		public static Builder getInstance()
		{
			return new Builder();
		}

		private Builder()
		{
			mHttpTransport = new HttpTransport();
		}

		public HttpTransport getHttpTransport()
		{
			return mHttpTransport;
		}

		public void setHttpTransport(HttpTransport httpTransport)
		{
			this.mHttpTransport = httpTransport;
		}

	}

	public boolean sendMessage(final String email, final String message) throws Exception
	{
		if (!email.matches(emailPattern))
		{
			throw new Exception("Incorrect email");
		}
		Object obj = getBuilder().getHttpTransport().execute("");
		if (obj == null)
		{
			throw new Exception("Data is null");
		}
		return true;
	}

}
