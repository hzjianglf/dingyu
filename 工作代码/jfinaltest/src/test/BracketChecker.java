package test;

public class BracketChecker {
    private String inString;
    public BracketChecker(String in) {
        inString = in;
    }
    public void check() {//检查括号是否匹配
        int len = inString.length();
        StackChar chStack = new StackChar(len); 
        char ch;
        char chPop;
        boolean flag = false;
                
        for (int index = 0; index < len; index++) {
            ch = inString.charAt(index);
            switch (ch) {
            case '{':
            case '[':
            case '(':
                chStack.push(ch); //如果是前括号，就入栈
                break;
            case '}':
            case ']':
            case ')':
                if ( !chStack.isEmpty() ) { //是反括号
                    chPop = chStack.peek();
                    if ( ( '{'==chPop && '}' != ch ) //判断栈顶的括号与现在读入的这个反括号是否匹配
                            ||('['==chPop && ']' != ch )
                            || ('('==chPop && ')' != ch ) )
                    {    //不匹配就报错
                        System.out.println("Error : " + ch + " at " + index);
                        flag = true;
                    }else if (( '{'==chPop && '}' == ch ) 
                            ||('['==chPop && ']' == ch )
                            || ('('==chPop && ')' == ch )) { 
                        //匹配就出栈，继续读下一个字符
                        chStack.pop();
                    }
                } else {//如果遇到反括号，但是栈为空，则出错
                    System.out.println("Error : " + ch + " at " + index);
                    flag = true;
                }
            default: 
                break;
            }
        }
        if ( !flag ) {
            System.out.println("The delimiters of the string is matching!");
        }
    }
}
