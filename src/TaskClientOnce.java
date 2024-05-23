import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.Socket; // ネットワーク関連のパッケージを利用する
import java.util.Scanner;

public class TaskClientOnce {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            System.out.println("localhostの" + port + "番ポートに接続を要求します");
            Socket socket = new Socket("localhost", port);
            System.out.println("接続されました");

            System.out.print("計算する数値を入力してください → ");
            int number = scanner.nextInt();
            scanner.close();

            TaskObject task = new TaskObject();
            task.setExecNumber(number); // クライアントで最初に計算させる数字を入力

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(task); // タスクをサーバに送信
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            TaskObject resultTask = (TaskObject) ois.readObject(); // サーバからの結果を受け取る

            long result = resultTask.getResult();
            System.out.println("サーバからの結果は " + result + " です！");

            ois.close();
            oos.close();
            socket.close();

        } catch (BindException be) {
            be.printStackTrace();
            System.err.println("ポート番号が不正か、サーバが起動していません");
            System.err.println("サーバが起動しているか確認してください");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}
