package test;

public class StackChar
{
    private int        maxSize;    // size of stack array
    private char[]    stackArray;
    private int        top;        // top of stack
    public StackChar( int maxSize )
    {
        this.maxSize = maxSize;// 设置数组大小
        stackArray = new char[maxSize];// 创建数组
        top = -1;// 还没有任何元素
    }
    public void push( char ch )
    {// 入栈
        if ( isFull( ) )
        {
            System.out.println( "Cannot insert item " + ch + "! The stack is full." );
        } else
        {
            top++ ;
            stackArray[top] = ch;
        }
    }
    public char pop()
    {// 出栈
        if ( isEmpty( ) )
        {
            System.out.println( "The stack is empty." );
            return 0;
        } else
        {
            char ch = stackArray[top];
            stackArray[top] = 0;
            top-- ;
            return ch;
        }
    }
    public int size()
    {
        return top + 1;
    }
    public char peek()
    {// 返回栈顶元素
        return stackArray[top];
    }
    public char peekN( int n )
    {// 返回index为n的元素
        return stackArray[n];
    }
    public boolean isEmpty()
    {
        return ( -1 == top );
    }
    public boolean isFull()
    {
        return ( maxSize - 1 == top );
    }
    public void displayStack()
    {
        System.out.print( " Stack: " );
        for ( int i = 0; i < size( ); i++ )
        {
            System.out.print( peekN( i ) );
        }
    }
}
