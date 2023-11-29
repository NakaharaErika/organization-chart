package service;

public class Tantai {
    
    protected boolean callProtec(String str) {
        if(str != null) {
            System.out.println("protectedメソッドを呼び出し:" + str);
            return true;
        } else {
            return false;
        }
    }
    
    private boolean callPri(String str) {
        if(str != null) {
            System.out.println("privateメソッドを呼び出し:" + str);
            return true;
        } else {
            return false;            
        }
    }
}