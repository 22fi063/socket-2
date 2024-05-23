import java.io.Serializable;

public class TaskObject implements ITask, Serializable {
    int num;
 PrimeChecker checker = new PrimeChecker();

    @Override
    public void setExecNumber(int x) {//クライアントで最初に計算させる数字を入力しておく関数
        this.num = x;
    }

    @Override
    public void exec() {//サーバで計算を実行をさせる関数...計算アルゴリズムが記載される。下記アルゴリズムを参照のこと
        int r = 1;
        for (int i = 1; i <= num; i++) {
            if (PrimeChecker.isPrime(i)) {
                r = i;
                System.out.println(i + " is a prime number.");
            } else {
                System.out.println(i + " is not a prime number.");
            }
        }
        num = r;
    }

    @Override
    public int getResult() {//クライアントで結果を取り出す関数
        return num;
    }
}