public class DinnerFullCourse {

    private Dish[] list = new Dish[5];// [0]-[4]の計5個

	public static void main(String[] args) {

		DinnerFullCourse fullcourse = new DinnerFullCourse();
		fullcourse.eatAll();
	}

	DinnerFullCourse() {

		list[0] = new Dish();
		list[0].setName("サーモン");
		list[0].setValune(10);
		list[1] = new Dish();
		list[1].setName("まぐろ");
		list[1].setValune(20);
		list[2] = new Dish();
		list[2].setName("大トロ");
		list[2].setValune(50);
		list[3] = new Dish();
		list[3].setName("いくら");
		list[3].setValune(100);
		list[4] = new Dish();
		list[4].setName("うに");
		list[4].setValune(200);
	}// DinnerFullCourse()コンストラクターエンド

	void eatAll() {
		String str = "";

		for (Dish element : list) {
			str += element.getName() + "(" + element.getValune() + "円)";
		}

		System.out.println("たかしへ、ママです.今日の晩御飯コースは" + str + "よ");
	}

}