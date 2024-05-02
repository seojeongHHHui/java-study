package thread;

public class AlphabetThread extends Thread {

	@Override
	public void run() {
		for(char c='a'; c<='j'; c++) {
			System.out.print(c);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
