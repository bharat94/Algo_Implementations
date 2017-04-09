/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.BitSet;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author vaidhyanathannarayanan
 */
public class Solution {

    static class ANS{
        long a;
        long b;
        
        public ANS(long a, long b){
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
    for (int i = 1; i <= t; ++i) {
      int n = in.nextInt();
      int k = in.nextInt();
      ANS ans = getLastEntry(n,k);
      System.out.println("Case #" + i + ": " + Math.max(ans.a, ans.b) + " " + Math.min(ans.a, ans.b));
    }
  }
    
    public static ANS getLastEntry(int n, int k)
    {
        BitSet bs = new BitSet();
        //setting the bits
        bs.set(0);
        bs.set(n+1);
        
        //simulating the previous k-1 entries
        for(int i=1; i<k; i++) {
            bs.set(getNext(bs));
        }
        
        int i = getNext(bs);
        int a = bs.previousSetBit(i);
        int b = bs.nextSetBit(i);
        
        return new ANS(Math.abs(i-a)-1,Math.abs(i-b)-1);
    }

    
    
    public static int getNext(BitSet bs) {
        Stream<Integer> stream = bs.stream().boxed();
        Comparator<Integer> comp = Comparator.comparingInt(n -> (bs.nextSetBit(n+1) - n));
        int a = stream.max(comp).get();
        int b = bs.nextSetBit(a+1);
        //binary center
        return (a+b)/2;
    }

}

