import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class TaskClientWhile {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ポートを入力してください(5000など) → ");
        int port = scanner.nextInt();

        Socket socket = null;
        try {
            while (true) {
                socket = new Socket("localhost", port);
                System.out.println("接続されました");

                System.out.print("計算する数値を入力してください（0を入力すると終了します） → ");
                int number = scanner.nextInt();
                if (number == 0) {
                    break; // 0が入力されたら終了
                }

                TaskObject task = new TaskObject();
                task.setExecNumber(number);

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(task);
                oos.flush();

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                TaskObject resultTask = (TaskObject) ois.readObject();
                int result = resultTask.getResult();
                System.out.println("サーバからの結果は " + result + " です！");

                // ストリームを閉じる
                oos.close();
                ois.close();
                socket.close(); // ここでソケットを閉じる
            }
        } catch (ConnectException e) {
            System.err.println("接続できませんでした。サーバーが実行されていることを確認してください。");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }
}
