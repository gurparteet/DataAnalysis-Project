package loginModule;

import java.util.HashMap;
// import java.util.concurrent.TimeUnit;

import uiModule.MainUIFacade;

public class ProxyLogin extends UserData {
    String incomingUsername, incomingPassword;
    static boolean loginStatus = false;
    static String loginMessage;


    /**
     * This is a constructor for ProxyLogin with parameters username and password.
     * @param username- String containing username in (Uppercase) from login page by user
     * @param password- String containing password from login page by user
     * This constructor will check if the username and password is correct and update login message.
     * This constuctor calls proxyLoginCheck method to check if the username and password is correct.
     * This constructor will update login message and login status.
     */
    public ProxyLogin(String username, String password) {
        this.incomingUsername = username;
        this.incomingPassword = password;
    }


    /**
     * This is a method for validating username and password and return a boolean value.
     * @param username- String coming from user as a username
     * @param password- String coming from user as a password
     * @return - boolean value if login is successful or not and login message is updated for login class.
     */
    // boolean getLoginStatus() {
        
    //     // loginStatus= doValidate();
    //     return loginStatus;
    // }
    // void setLoginStatus(boolean loginstate){
    //     this.loginStatus = loginstate;
    // }

    // String getLoginMessage(){
    //     return this.loginMessage;
    // }


   /**
        * This method will check if the username and password is correct and update login message.
        * This method will call the method from UserData class to check if the username and password is correct.
        * This method will update login message and login status.
        */
    public void doValidate()   {
        boolean flag=false ;
        UserData uData=new UserData();
        HashMap<String,String> loginData = uData.getUserData();

        
        if(loginData.containsKey(incomingUsername)) {
            if(loginData.get(incomingUsername).equals(incomingPassword)) {
                flag=true;
                loginMessage="Login Successful" ;
            }else {
                loginMessage="Invalid Password- Login Unsuccessful" ;
            }
            
        }else {
            loginMessage="Username does not exist" ;
            
        }
        
        launchUI(flag);
        
    }

    public void launchUI(boolean loginStatus){

        if (loginStatus == true) {
            System.out.println("USER IS LOGGED IN");
            //this is creating an instance of the MainUI and we can access the ui in client or a facade etc
            MainUIFacade facade = MainUIFacade.getInstance();
            facade.startmainUI();

            System.out.println("MainUI launched");
            // try{
            //     TimeUnit.SECONDS.sleep(1);

            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }

            Login.getInstance().dispose();
        }
    }
}