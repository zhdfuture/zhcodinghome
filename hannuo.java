import java.util.Scanner;
public class hannuo {
    public int process(int n,String left,String mid,String right,String from,String to){
       if(n==1){
           if(from.equals(mid) || to.equals(mid)){
               System.out.println("Move 1 from "+ from + " to "+to);
               return 1;
           }
           else{
               System.out.println("Move 1 from "+from+ " to "+mid);
               System.out.println("Move 1 from "+from+ " to "+to);
             return 2;
           }
       }
        int result=0;
       if(from.equals(mid)||to.equals(mid)) {

           String temp = (from.equals(left) || to.equals(right)) ? right : left;
           int step1 = process(n - 1, left, mid, right, from, temp);    //递归
           int step2 = 1;
           System.out.println("Move" + n + "from" + from + " to " + to);
           int step3 = process(n - 1, left, mid, right, temp, to);
           result=step1+step2+step3;
           System.out.println("移动总步数"+ result);
       }
       else{
           int step1 = process(n - 1, left, mid, right, from, to);    //递归
           int step2 = 1;
           System.out.println("Move" + n + "from" + from + " to " + mid);
           int step3=process(n-1,left,mid,right,to,from);
           int step4=1;
           System.out.println("Move"+ n +" from "+ mid +" to "+to);
           int step5=process(n-1,left,mid,right,from,to);
           result=step1+step2+step3+step4+step5;
           System.out.println("移动总步数"+ result);


       }
     return result;
    }
    public int hannuo1(int n,String left,String mid,String right){
        if(n<1){
    return 0;
        }
        return process(n,left,mid,right,left,right);
    }

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.println("please input the nummber:");
        int n=scan.nextInt();
     hannuo han=new hannuo();
       han.hannuo1(n,"left","mid","right");
    }
}
