package User;

import java.io.*;

public class Data {
    public int number;
    public User[] user;
    public Data() throws IOException {
        read();
    }
    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Data.txt"));
        String num=reader.readLine();
        number = Integer.parseInt(num);
        user = new User[number+10];
        String line;
        for(int i=0;i<number;i++)
        {
            line=reader.readLine();
            String[] p=line.split(" ");
            user[i]=new User(p[0],p[1]);
        }
        reader.close();
    }
    public void recode() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Data.txt"));
        writer.write(number+"\n");
        for(int i=0;i<number;i++)
        {writer.write(user[i].name+" "+user[i].password+"\n");}
        writer.close();
    }
    public void add(String name,String pass) throws IOException {
        if(find(name))return;
        number++;
        user[number-1]=new User(name,pass);
        recode();
    }
    public boolean find(String name) throws IOException {
        for(int i=0;i<number;i++)
        {
            if(user[i].name.equals(name))
                return true;
        }
        return false;
    }
    public boolean register(String name,String pass){
        for (int i = 0; i < number; i++) {
            if(user[i].name.equals(name) && user[i].password.equals(pass))
                return true;
        }
        return false;
    }
    public void resetPassword(String name,String pass) throws IOException {
        for(int i=0;i<number;i++)
        {
            if(user[i].name.equals(name))user[i].password=pass;
        }
        recode();
    }
}
