package test;

public class Print99Table {
    private static final int base_left=0xff00;
    private static final int base_rigth=0x00ff;
    
    /**
     * 用一个数保存两个乘数即可
     * 加了格式化的，并把乘数也打印出来了  ^.^~
     */
    public static void print99table(){
            int num=0x0101;
            while( ((num&base_left)>>8) <10 ){
                    num=(num&0xff00)|0x01;
                    while ( ((num&base_left)>>8) >= (num&base_rigth)){
                            System.out.print( ((num&base_left)>>8)+  "x"+(num&base_rigth)  +"=" +(((num&base_left)>>8)*(num&base_rigth) )  +"\t");
                            num+=1;
                    }
                    System.out.println();
                    num+=0x0100;
            }
    }
    public static void main(String a[]){
            print99table();
    }
}
