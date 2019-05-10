import javax.swing.*;
import java.util.Scanner;
import java.util.Stack;

//状态
class State
{
    public int n;
    public String left;
    public String mid;
    public String right;
    public State(int n, String left, String mid, String right)
    {
        this.n = n;
        this.left =left;
        this.mid =mid;
        this.right =right;
    }
}
//栈
class StateStack {
    private State[] stack = new State[1000];
    //栈顶
    private int top = 0;

    //入栈
    public void push(State s) {
        stack[top++] = s;
    }

    //出栈
    public State pop() {
        if (top > 0) {
            return stack[--top];
        }
        return null;
    }
}
public class hannuota2 {

    public static void hannuota2(int n, String left,String mid, String right) {
        //创建一个栈
        StateStack s = new StateStack();
        //将开始状态进栈
        s.push(new State(n,"left", "mid","right"));
        //保存出栈元素
        State state = null;
        //出栈
        while ((state = s.pop()) != null) {
            //如果n为1，直接移动left->right
            if (state.n == 1) {
                move(state.left, state.right);
            }
            //如果n大于1，则按照递归的思路，先处理hanoi(n-1,left,right,mid)，再移动left->right(等价于hanoi(1,left,mid,right) ),然后处理hanoi(n-1,mid,left,right)，因为是栈，所以要逆序添加
            else {
                //栈结构先进后出，所以需要逆序进栈
                s.push(new State(state.n - 1, state.mid, state.left, state.right));
                s.push(new State(1, state.left, state.mid, state.right));
                s.push(new State(state.n - 1, state.left, state.right, state.mid));
            }
        }
    }

    public static void move(String str1, String str2) {
        System.out.println(str1 + "->" + str2);
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("please input the nummber:");
        int n = scan.nextInt();

        hannuota2(n, "left", "mid", "right");
    }
}

