package cartaylor;



public  interface Observable<T> 
{
	
	
	public T getValue() ;

	public boolean isRegistered(Observer<T> parameter) ;

	public void register(Observer<T> parameter) ;

	public void unregister(Observer<T> parameter) ;

	public void sendNotification();
}

