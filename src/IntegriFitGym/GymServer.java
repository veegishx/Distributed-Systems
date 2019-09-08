package IntegriFitGym;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GymServer implements Runnable {
    public static Socket connetionSocket;
    public static ObjectOutputStream outToClient;
    public static ObjectInputStream inFromClient;
    public static ArrayList<GymData> gymDataArrayList = new ArrayList<GymData>();
    public static ObjectInputStream ois;
    public static FileInputStream fileInputStream;
    public static FileOutputStream fileOutputStream;
    public static ObjectOutputStream oos;
    public static GymData tmp;


    public void run() {

        try {
            ServerSocket listeningSocket = new ServerSocket(59090);
            System.out.println("Server is up & running on port: " + listeningSocket.getLocalPort());

            while (true) {
                try {
                    connetionSocket = listeningSocket.accept();
                    System.out.println("CLIENT CONNECTED");

                    outToClient = new ObjectOutputStream(connetionSocket.getOutputStream()); // get the output stream of client.
                    inFromClient = new ObjectInputStream(connetionSocket.getInputStream());

                    //System.out.print(inFromClient.readUTF());
                    String action = (String) inFromClient.readUTF();
                    switch (action){
                        case "create":

                            GymData gymData = (GymData) inFromClient.readObject();
                            System.out.println("[SERVER] DATA RECEIVED: " + gymData.toString());
                            gymDataArrayList.add(gymData);

                            try{
                                fileOutputStream = new FileOutputStream("GymData.dat");
                                oos = new ObjectOutputStream(fileOutputStream);

                                oos.writeObject(gymDataArrayList);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            break;
                        case "readAll":
                            System.out.println("read");
                            try {
                                fileInputStream = new FileInputStream("GymData.dat");
                                ObjectInputStream ois = new ObjectInputStream(fileInputStream);

                                gymDataArrayList =(ArrayList) ois.readObject();
                                outToClient.writeObject(gymDataArrayList);

                                outToClient.flush();
                                System.out.println(gymDataArrayList);


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "readID":
                            try {
                                fileInputStream = new FileInputStream("GymData.dat");
                                ois = new ObjectInputStream(fileInputStream);

                                gymDataArrayList =(ArrayList) ois.readObject();
                                outToClient.writeObject(gymDataArrayList);
                                outToClient.flush();
                                System.out.println(gymDataArrayList);


                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                        case "update":
                            String nid = inFromClient.readUTF();
                            System.out.println("[SERVER] UPDATED " + nid);

                            GymData updatedGymData = (GymData) inFromClient.readObject();

                            try{
                                fileInputStream = new FileInputStream("GymData.dat");
                                ois = new ObjectInputStream(fileInputStream);

                                gymDataArrayList =(ArrayList) ois.readObject();

                                for (int i = 0; i<gymDataArrayList.size(); i++){
                                    tmp = gymDataArrayList.get(i);
                                    if (tmp.getNationalId().equals(nid)){
                                        gymDataArrayList.set(i,updatedGymData);
                                    }
                                }
                                fileOutputStream = new FileOutputStream("GymData.dat");
                                oos = new ObjectOutputStream(fileOutputStream);

                                oos.writeObject(gymDataArrayList);

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                            break;
                        case "deleteID":
                            String deleteNid = inFromClient.readUTF();
                            System.out.println("[SERVER] DELETED " + deleteNid);

                            try{
                                fileInputStream = new FileInputStream("GymData.dat");
                                ois = new ObjectInputStream(fileInputStream);

                                gymDataArrayList =(ArrayList) ois.readObject();

                                for (int i = 0; i<gymDataArrayList.size(); i++){
                                    tmp = gymDataArrayList.get(i);
                                    if (tmp.getNationalId().equals(deleteNid)){
                                        gymDataArrayList.remove(i);
                                    }
                                }
                                fileOutputStream = new FileOutputStream("GymData.dat");
                                oos = new ObjectOutputStream(fileOutputStream);

                                oos.writeObject(gymDataArrayList);

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                            break;
                        case "deleteAll":
                            fileInputStream = new FileInputStream("GymData.dat");
                            ois = new ObjectInputStream(fileInputStream);

                            gymDataArrayList =(ArrayList) ois.readObject();
                            gymDataArrayList.clear();

                            FileOutputStream fileOutputStream = new FileOutputStream("GymData.dat");
                            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);

                            oos.writeObject(gymDataArrayList);

                            try {
                                outToClient.writeUTF("All members deleted.");
                                outToClient.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                    }

                } catch (IOException i) {
                    i.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Thread GymServerThread = new Thread(new GymServer());
        GymServerThread.start();
        System.out.println("Multi-threaded Server starting...");
    }

}
