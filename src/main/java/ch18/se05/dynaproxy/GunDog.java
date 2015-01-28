package ch18.se05.dynaproxy;

public class GunDog implements Dog {

	@Override
	public void info() {
		System.out.println("这是一只猎狗");
	}

	@Override
	public void run() {
		System.out.println("猎狗奔跑迅速！");
	}

}
