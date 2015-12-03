import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.sql.Time;
import java.util.Stack;
//----------------------------------//
//TODO: implement 19 operations
//
// Done: implemented StackAdd, StackState
//----------------------------------//
public class Slang {

    Stack<Value> stack = new Stack<>();

    public void StackAdd(Value elem) {
        this.stack.push(elem);
    }
    public void StackAdd(int elem) {
        Value temp = new ValueInt(elem);
        this.stack.push(temp);
    }
    public void StackAdd(String elem) {
        Value temp = new ValueString(elem);
        this.stack.push(temp);
    }

    //prints the current State of the Stack does not change the stack //Todo: ".s" is command
    public void StackState() {
        Stack<Value> tempStack =(Stack<Value>) stack.clone();
        System.out.println("-- Top of Stack:");
        while (!tempStack.isEmpty()){
            System.out.println(tempStack.pop().toS());
        }
        System.out.println("-- Bottom");
    }
    //removes the first elem from stack and prints it //Todo: command is "prin"
    public void StackPrin() {
        if (!stack.isEmpty()) {
            System.out.print(stack.pop().toS());
        }
        else {
            System.err.print("stack is empty"); //todo: maybe exeption
        }
    }
    //removes the first elem from stack and prints it with a line break//Todo: command is "print"
    public void StackPrint() {
        if (!stack.isEmpty()) {
            System.out.println(stack.pop().toS());
        }
        else {
            System.err.print("stack is empty"); //todo: maybe exeption
        }
    }
    //removes first two elements of stack, then pushes the difference of these two on stack //Todo: command is "sub"
    public void StackSub() {
        int x = stack.pop().toI();
        int y = stack.pop().toI();

        stack.push(new ValueInt(x-y));
    }

    //removes first two elements of stack, then pushes the sum of these two on stack //Todo: command is "add"
    public void StackSum() {
        int x = stack.pop().toI();
        int y = stack.pop().toI();

        stack.push(new ValueInt(x+y));
    }

    //removes first two elements of stack, then pushes the product of these two on stack //Todo: command is "mul"
    public void StackMul() {
        int x = stack.pop().toI();
        int y = stack.pop().toI();

        stack.push(new ValueInt(x*y));
    }

    //removes first two elements of stack, then pushes the product of these two on stack //Todo: command is "div"
    public void StackDiv() {
        int x = stack.pop().toI();
        int y = stack.pop().toI();

        stack.push(new ValueInt(x/y));
    }

    //removes first two elements of stack, then pushes the modulo of these two on stack //Todo: command is "mod"
    public void StackMod() {
        int x = stack.pop().toI();
        int y = stack.pop().toI();

        stack.push(new ValueInt(x%y));
    }
    //removes first element of stack if its a weblink(http/s) then it puts the content as a String on the
    //stack, else it opens the filepath and puts the file content on stack  //Todo: command is "read"
    public void StackRead(){
        String output = "";
        String filePath = stack.pop().toS();
        boolean website = false;
        //check if http:// or https://
        String http = filePath.substring(0,7);
        String https = filePath.substring(0,8);
        if (http.equals("http://") || https.equals("https://")) {
            website = true;
        }
        if (website) {
            try {
                URL url = new URL(filePath);
                URLConnection connection = url.openConnection();

                InputStream is = connection.getInputStream();
                try {
                    BufferedReader in = null;
                    try {
                        in = new BufferedReader(new InputStreamReader(is));
                        String line;
                        while ((line = in.readLine()) != null) {
                            output += line;
                        }
                    }
                    finally {
                        if (in != null) {
                            in.close();
                        }
                    }
                }
                catch (IOException x) {
                    System.err.println("read error");
                    output = null;
                }
            }
            catch (IOException x) {
                System.err.println("url error");
                output = null;
            }
            //TODO Website excessive testing needed!! current version is UNTESTED
        }

        //following implementation of reading file as a String and put it on stack
        else {
            try {
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new FileReader(filePath));
                    String line;
                    while ((line = in.readLine()) != null) {
                            output += line;
                    }
                }
                finally {
                    if (in != null) {
                        in.close();
                    }
                }
            }
            catch (IOException x) {
                System.err.println("read error");
                output = null;
            }
        }


        //push the string on the stack
        stack.push(new ValueString(output));
    }

    //Stackwrite Todo: command "write"
    public void StackWrite(){
        //todo: implement whole method

    }
        //todo: testing needed
        //todo: implement exeptions

    public void ask(){  //todo: test fully (simple test done)
        JFrame frame = new JFrame("InputString");

        // prompt the user to enter a String
        String enteredString = JOptionPane.showInputDialog(frame, "Please enter a valid String?");

        // get the user's input. note that if they press Cancel, 'name' will be null
        stack.push(new ValueString(enteredString));
        System.exit(0);
    }


    public static void main(String[] args) {
        //just for testing
        Slang test = new Slang();
        ValueInt testValue1 = new ValueInt(10);
        ValueInt testValue2 = new ValueInt(5);
        ValueString testValue3 = new ValueString("Hansbert");
        ValueInt testValue4 = new ValueInt(2);
        ValueString testValue5 = new ValueString("Gunter");

        test.StackAdd(testValue1);
        test.StackAdd(testValue2);
        test.StackAdd(testValue3);
        test.StackAdd(testValue4);
        test.StackAdd(testValue5);

        test.StackState();
      //  test.StackState();

        test.ask();

        test.StackState();
        //todo: tested until StackState!!

        //ToDo: implement Atomscanner
        /*AtomScanner sc = null;

        try {
            sc = new AtomScanner(System.in);
        } catch (LexerException ex) {
            System.out.println(ex);
            return;
        }
        while (sc.hasNext()) {
            if (sc.hasNextString()) {
                System.out.println("STRING: " + sc.nextString());
            }
            else if (sc.hasNextInteger()) {
                System.out.println("INTEGER: " + sc.nextInteger());
            }
            else if (sc.hasNextAtom()) {
                System.out.println("ATOM: " + sc.nextAtom());
            }
            else {
                System.out.println("UNKNOWN: " + sc.next());
            }
        }
        */
    }

}
