import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TaskServerWhile {

    public static void main(String[] args) {

        try {

            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            scanner.close();
            System.out.println("localhostの" + port + "番ポートで待機します");
            ServerSocket server = new ServerSocket(port);

            while (true) {
                System.out.println("接続を待っています......");
                Socket socket = null;
                ObjectInputStream ois = null;
                ObjectOutputStream oos = null;

                try {
                    socket = server.accept(); // クライアントからの接続要求を待ち、
                    // 要求があればソケットを取得し接続を行う
                    System.out.println("接続しました。相手の入力を待っています......");

                    ois = new ObjectInputStream(socket.getInputStream());

                    TaskObject task = (TaskObject) ois.readObject();// Integerクラスでキャスト
                    if (task.getResult() <= 1) { // 1以下の値が入力された場合に終了
                        break;
                    }
                    task.exec();
                    oos = new ObjectOutputStream(socket.getOutputStream());

                    oos.writeObject(task);
                    oos.flush();
                    // close処理
                    ois.close();
                    oos.close();
                    socket.close();
                } catch (Exception e) {
                    System.err.println("クライアントとの通信でエラーが発生しました");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            e.printStackTrace();
        }
    }
}