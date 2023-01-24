package clientModule;



import loginModule.Login;

public class Client {

    public Client() {
    }

    public static void main(String[] args) {

        //SINGLETON DESIGN PATTERN INSTANTIATION
        Login session1 = Login.getInstance();
        session1.setSize(500,130);
        session1.setVisible(true);

    }
}
