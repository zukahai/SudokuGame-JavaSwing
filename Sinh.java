package Sudoku;

import java.io.FileWriter;
import java.util.Random;
 
public final class ss
{

    private Random            myRandom;
    private int[][]        valueCell, solution;

    public ss()
    {
        myRandom = new Random();
    }

    public void hien_thi(int a[][])
    {

        System.out.println("-------------------------");
        for(int row=1;row<=9;row++)
        {
            for(int col=1;col<=9;col++)
            {
                System.out.print(a[row][col] +" ");
                if(col==3||col==6)System.out.print(" | ");
            }
            System.out.println();
            if(row==3||row==6)
            {
                System.out.print("-------------------------");
            }
            System.out.println();
        }
        System.out.print("-------------------------");
        System.out.println();
    }

    public void de_bai(int a[][])
    {
        int s= myRandom.nextInt(10)+12;
        int[] temp = new int[s+1];
        int[] temp2 = new int[s+1];
        for (int k = 1; k < s + 1; k++)
        {
            temp[k] = 0;
            temp[2] = 0;
        }

        while(s!=0)
        {
            s--;
            int row, col;
            row=myRandom.nextInt(9)+1;
            col=myRandom.nextInt(9)+1;

            a[row][col]=0;
        }
    }

    public void generate()
    {
    //    srand(time(0));
        int[] b, d;
        int k,z,cout;

        valueCell    = new int[10][10];
        b            = new int[100];
        d            = new int[100];
        cout        = 0;
        k            = 0;
        z            = 0;
 
        //kroi tao
        for(int row=1; row<=9; row++)
        {
            for(int col=1; col<=9; col++)
            {
                valueCell[row][col] = 0;
            }
        }
 
        int x = 0, run = 0;

        //tao mang
        for( int row=1; row<=9; row++ )
        {
//            System.out.println("row = "+row);
            for( int col=1; col<=9; col++ )
            {
                k=0;
                run++;//dem so lan chay vong lap
                System.out.println("run = "+run);
                if (run > 10000)
                	System.exit(0);

                //row
                for( int i=1; i<=9; i++ )
                {
                    if ( valueCell[i][col]!=0 )
                    {
                        k++;b[k] = valueCell[i][col];
                    }
                }

                //col
                for( int j=1; j<=9; j++ )
                {
                    if ( valueCell[row][j]!=0 )
                    {
                        k++;b[k] = valueCell[row][j];
                    }
                }

                //vung 1
                if( row<=3 )
                {
                    if( col<=3 )
                    {
                        for ( int r=1; r<=3; r++ )
                        {
                            for ( int c=1; c<=3; c++ )
                            {
                                if ( valueCell[r][c]!=0 )
                                {
                                    k++; b[k] = valueCell[r][c];
                                }
                            }
                        }
                    }

                    if ( 4<=col && col<=6 )
                    {
                        for( int r=1; r<=3; r++ )
                        {
                            for( int c=4; c<=6; c++ )
                            {
                                if ( valueCell[r][c]!=0 )
                                {
                                    k++;b[k]=valueCell[r][c];
                                }
                            }
                        }
                    }

                    if ( 7<=col )
                    {
                        for( int r=1; r<=3;r++ )
                        {
                            for( int c=7; c<=9; c++ )
                            {
                                if ( valueCell[r][c] != 0 )
                                {
                                    k++;b[k] = valueCell[r][c];
                                }
                            }
                        }
                    }
                }

                //vung 2
                if ( 3<row && row<=6 )
                {
                    if (col<=3)
                    {
                        for(int r=4;r<=6;r++)
                        {
                            for(int c=1;c<=3;c++)
                            {
                                if (valueCell[r][c]!=0)
                                {
                                    k++;b[k]=valueCell[r][c];
                                }
                            }
                        }
                    }

                    if ( 3 < col && col <= 6 )
                    {
                        for( int r = 4; r <= 6; r ++ )
                        {
                            for( int c = 4; c <= 6; c ++ )
                            {
                                if( valueCell[r][c] != 0 )
                                {
                                    k++;b[k] = valueCell[r][c];
                                }
                            }
                        }
                    }
 
                    if ( 7 <= col )
                    {
                        for( int r = 4; r <= 6; r ++ )
                        {
                            for( int c = 7; c <= 9; c ++ )
                            {
                                if ( valueCell[r][c] != 0 )
                                {
                                    k++;b[k] = valueCell[r][c];
                                }
                            }
                        }
                    }
                }

                //vung 3
                if ( 7<=row )
                {
                    if( col<=3 )
                    {
                        for(int r=7; r<=9; r++)
                            for(int c=1; c<=3; c++)
                            {
                                if(valueCell[r][c]!=0)
                                {
                                    k++;b[k]=valueCell[r][c];
                                }
                            }
                    }

                    if( 3<col && col<=6 )
                    {
                        for(int r=7; r<=9; r++)
                            for(int c=4; c<=6; c++)
                            {
                                if(valueCell[r][c]!=0)
                                {
                                    k++;b[k]=valueCell[r][c];
                                }
                            }
                    }

                    if( 7<=col )
                    {
                        for(int r=7;r<=9;r++)
                            for(int c=7;c<=9;c++)
                            {
                                if( valueCell[r][c]!=0 )
                                {
                                    k++;b[k] = valueCell[r][c];
                                }
                            }
                    }
                }

                x = 0;

                for(int g=1;g<=9;g++)
                {
                    cout=0;
                    for(int t=1;t<=k;t++)
                    {
                        if(g==b[t])cout=1;
                    }

                    //so nay crua co duoc prep dien
                    if(cout==0)
                    {
                        x++;
                        d[x]=g;
                    }
                }

                if(x==0)
                {
                    for(int kk=1;kk<=9;kk++)
                    {
                        valueCell[row][kk]=0;
                    }
                    --row;
//                    System.out.println("row2 = "+row);
                    break;
                }
                //ngau nrien cron va dien vao o
 
                z=myRandom.nextInt(x)+1;
                //gan gia tri
                valueCell[row][col]=d[z];

            //ket truc vong for "col"
            }
        }

//        System.out.println("-------------------------");
        String str = "";
        for(int i=1;i<=9;i++)
        {
            for(int j=1;j<=9;j++)
            {
                str = str + valueCell[i][j];
//                if(j==3||j==6)System.out.print(" | ");
            }
//            System.out.println();
            if(i==3||i==6)
            {
//                System.out.print("-------------------------");
            }
//            System.out.println();
        }
//        System.out.print("-------------------------");
//        System.out.println();
        System.out.println(str);
            try {
                FileWriter fw = new FileWriter("./src/Sudoku/Data/data.txt", true);
                fw.write("\"" + str + "\", \n");
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("Success...");
        }

    public void clear()
    {
        for (int i = 1; i < 9; i++)
        {
            for (int j = 1; j < 9; j++)
            {
                valueCell[i][j] = 0;
            }
        }
    }

    public int[][] getValueCell()
    {
        return valueCell;
    }
    public static void main(String[] args) {
    	for (int i = 0; i < 1000; i++) {
    		ss k = new ss();
    		k.generate();
    	}
	}
 
}