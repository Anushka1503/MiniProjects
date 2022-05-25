package miniproject;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;
import java.security.SecureRandom;
import java.lang.String;

abstract class UpiPayment{
    public UpiPayment(){
        System.out.println();
        System.out.println("\tUPI Payment");
        System.out.println("\tEnter PIN number");

    }
    public abstract String TransactionId();
}


class UpiPaymentChild extends UpiPayment{
    public String TransactionId(){
                final String chars = "0123456789";
                SecureRandom random = new SecureRandom();
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 12; j++) {
                    int randomIndex = random.nextInt(chars.length());
                    sb.append(chars.charAt(randomIndex));
                }
                return sb.toString();
            }
}

abstract class MobileWallet{
   public MobileWallet(){
       System.out.println("\tMobile Wallets !!");
   }
   public abstract String generateCapcha();
}
class MobileWalletChild extends MobileWallet{
public String generateCapcha(){
    final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    SecureRandom random = new SecureRandom();
    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < 4; j++) {
        int randomIndex = random.nextInt(chars.length());
        sb.append(chars.charAt(randomIndex));
    }
    return sb.toString();
}

}
abstract class BankingCards{
    public BankingCards(){
        System.out.println("\tBanking cards !!!");
        System.out.println("\tChoose \n\t1.Debit Card\n\t2.Credit Cards");

    }
}
class BankingCardsChild extends BankingCards{
    public void otpgen(){
        System.out.println("\tGenerating OTP...Please Wait");
        try{Thread.sleep(2000);}catch(InterruptedException e){System.out.println(e);}
    }
    public String debitcard(){
        final String chars = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 4; j++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }
}




abstract class InfoToClient{
     public InfoToClient(){
         System.out.println();
         System.out.println("\tWelcome to our portal");
         System.out.println();
     }
     public abstract void greet();
}


class Greetings extends InfoToClient{
public void greet(){
    System.out.println("\tWe offer Services in \n\t1)UPI\n\t2)internet Banking\n\t3)Banking Cards\n\t4)Mobile Wallets\n");
    }

}


class  RandomPassword{
    public static String getPassword() {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 7; j++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }

}

public class MyMainClass {
    public static void main(String[] args)  {
        String username,password;
        System.out.println("\n\t\t\t\t\t\tWelcome to the ONLINE PAYMENT PORTAL\t\t\t\t\t\t");
        System.out.println();


        RandomPassword ran = new RandomPassword();

        Scanner sc = new Scanner(System.in);

        System.out.println("\tPress 1 to Create new Account\n\tPress 2 to Login");
        System.out.println("\tEnter Choice");
        System.out.print("\t");
        int opt ;
        opt=sc.nextInt();
        sc.nextLine();
        if(opt==2) {
            System.out.println("\tEnter username");
            System.out.print("\t");
            username = sc.nextLine();
            System.out.println("\tEnter the Password");
            System.out.print("\t");
            password = sc.nextLine();

            File mYFile = new File("AccountDetails.txt");

            String user="";
            String pass="";

            try {
            Scanner scc = new Scanner(mYFile);
            scc.useDelimiter("[^a-zA-z]+");
            while (scc.hasNext()) {
                user += scc.nextLine();
                pass += scc.nextLine();
                user += scc.nextLine();
            }
            scc.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
//            System.out.println(user+"\t");
//            System.out.println(pass);

            if (user.contains(username) && pass.contains(password)) {
                System.out.println("\tAuthentication successful");
                InfoToClient info = new Greetings();
                info.greet();
                System.out.println("\tEnter your choice");
                System.out.print("\t");
                int choice = sc.nextInt();

                if (choice == 1) {
                    UpiPayment up = new UpiPaymentChild();
                    System.out.print("\t");
                    int no = sc.nextInt();
                    System.out.println("\tEnter Amount");
                    System.out.print("\t");
                    int amt = sc.nextInt();
                    String id = up.TransactionId();

                    System.out.println("\tThe Transaction was successful \n\tThe amount of \u20B9" + amt + " has been transferred via Transaction Id " + id);
                }
                else if(choice==4) {
                    MobileWallet mw = new MobileWalletChild();
                    System.out.println("\tEnter Amount");
                    System.out.print("\t");
                    int amt = sc.nextInt();
                    sc.nextLine();
                    System.out.println("\t");
                    int i = 1;
                    while (i == 1) {
                        System.out.println("\tEnter the capcha shown below");
                        System.out.print("\t");
                        String cap = mw.generateCapcha();
                        System.out.println(cap);
                        System.out.print("\t");
                        String take = sc.nextLine();

                        if (!cap.equals(take)) {
                            System.out.println("\tTry Again?");
                            System.out.print("\t");
                            String ans = sc.nextLine();

                            if (ans.equals("yes")) {
                                i = 1;
                            }
                            else
                                System.exit(0);

                        }
                        else
                            i=0;

                    }
                    System.out.println("\tTransaction Successful");
                }

                else if(choice==3){
                    BankingCardsChild bcc=new BankingCardsChild();
                    System.out.print("\t");
                    int ch=sc.nextInt();
                    if(ch==1 ) {
                        System.out.println("\tEnter Debit Card number");
                        System.out.print("\t");
                        int d = sc.nextInt();
                        bcc.otpgen();
                        int p = 1;
                        int q=0;
                        while (p == 1) {
                            q++;
                            System.out.println("\tenter the OTP");
                            System.out.print("\t");
                            String test = bcc.debitcard();
                            System.out.println(test);
                            System.out.print("\t");
                            String otp = sc.next();

                                if (q < 4) {
                                    if (!Objects.equals(otp, test)) {
                                        System.out.println("\tWrong OTP entered");
                                        System.out.println("\tSend Again ? (yes/no)");
                                        System.out.print("\t");
                                        String a = sc.next();

                                        if (Objects.equals(a, "yes")) {
                                            p = 1;
                                        }

                                    }
                                    else {
                                        p=0;
                                    }
                                } else {
                                    System.out.println("\tYou have exhausted your 3 trials.Try again later");
                                    System.exit(0);
                                }


                        }
                        System.out.println("\tEnter amount");
                        System.out.print("\t");
                        int am=sc.nextInt();
                        System.out.println("\tProcessing Transaction....");
                        try{Thread.sleep(3000);}catch(InterruptedException e){System.out.println(e);}
                        System.out.println("\tYour transaction of "+ am+" was successful");
                        System.exit(0);
                    }
                    if(ch==2){
                        System.out.println("\tEnter Credit Card number");
                        System.out.print("\t");
                        int d = sc.nextInt();
                        bcc.otpgen();
                        int p = 1;
                        int q=0;
                        while (p == 1) {
                            q++;
                            System.out.println("\tenter the OTP");
                            System.out.print("\t");
                            String test = bcc.debitcard();
                            System.out.println(test);
                            System.out.print("\t");
                            String otp = sc.next();

                            if (q < 4) {
                                if (!Objects.equals(otp, test)) {
                                    System.out.println("\tWrong OTP entered");
                                    System.out.println("\tSend Again ? (yes/no)");
                                    System.out.print("\t");
                                    String a = sc.next();

                                    if (Objects.equals(a, "yes")) {
                                        p = 1;
                                    }

                                }
                                else {
                                    p=0;
                                }
                            } else {
                                System.out.println("\tYou have exhausted your 3 trials.Try again later");
                                System.exit(0);
                            }


                        }
                        System.out.println("\tEnter amount");
                        System.out.print("\t");
                        int am=sc.nextInt();
                        System.out.println("\tProcessing Transaction....");
                        try{Thread.sleep(3000);}catch(InterruptedException e){System.out.println(e);}
                        System.out.println("\tYour transaction of "+ am+" was successful");
                        System.exit(0);
                    }
                }


            } else {
                System.out.println("\tAuthentication failed");
            }



        }


        else if(opt==1) {
            System.out.println("\tEnter a new Username");
            System.out.print("\t");
            String newuser = sc.nextLine();
            System.out.println("\tGenerate a strong password ? y/n");
            System.out.print("\t");
            String ans = sc.nextLine();
            if (ans.equals("y")) {
                System.out.println("\tYour password is");
                System.out.print("\t");
                String store = ran.getPassword();
                System.out.println(store);

                try {
                    FileWriter myfilewriter = new FileWriter("AccountDetails.txt", true);
                    BufferedWriter buf = new BufferedWriter(myfilewriter);

                    //StringBuffer header = new StringBuffer();

                    buf.append("Username : " + newuser);
                    buf.append("\nPassword : " + store);
                    buf.newLine();
                    buf.newLine();
                    buf.close();
                } catch (IOException e) {
                    System.out.println("Unable to write to a file");
                    e.printStackTrace();
                }

            }
        }
            else{
                System.out.println("\tWrong Option Chosen");
            }
    }
}
