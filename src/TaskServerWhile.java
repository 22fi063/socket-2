import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TaskServerWhile {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ポートを入力してください(5000など) → ");
        int port = scanner.nextInt();
        System.out.println("localhostの" + port + "番ポートで待機します");

        try {


            ServerSocket server = new ServerSocket(port);

            while (true) {
                Socket socket = server.accept();
                System.out.println("接続されました。相手の入力を待っています......");

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                TaskObject task = (TaskObject) ois.readObject(); // TaskObjectクラスでキャスト。

                System.out.println("タスクを受け取りました。計算を実行します...");
                task.exec(); // サーバで計算を実行

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(task); // 計算結果をクライアントに返送
                oos.flush();

                // close処理
                ois.close();
                oos.close();
                socket.close();
            }

        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            e.printStackTrace();
        }
    }
}
